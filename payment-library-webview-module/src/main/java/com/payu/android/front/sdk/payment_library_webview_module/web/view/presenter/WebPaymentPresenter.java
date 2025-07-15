package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebBackForwardList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.configuration.IllegalConfigurationException;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.OnAuthorizationFinishedListener;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.PostDataEncoder;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentStatus;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentWrapper;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client.PageLoadingCallback;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client.PaymentWebViewClient;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client.WebAuthorizationViewChromeClient;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.WebPayment;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.WebPaymentActivity;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.payu.android.front.sdk.payment_library_webview_module.web.authorization.OnAuthorizationFinishedListener.EMPTY_LISTENER;

public class WebPaymentPresenter extends WebPaymentAction {
    private enum urlLoadType {GET, POST}

    private WebPayment webPayment;
    private CookieManager cookieManager;
    private PostDataEncoder dataEncoder;
    private AddressBarPresenter addressBarPresenter;
    private PaymentWebViewClient client;
    private Optional<OnAuthorizationFinishedListener> onAuthorizationFinishedListenerOptional;

    private WebAuthorizationViewChromeClient chromeClient;

    private final WebPaymentActivity webPaymentActivity;

    public WebPaymentPresenter(
            AddressBarPresenter addressBarPresenter, CookieManager cookieManager,
            PostDataEncoder dataEncoder, PaymentUrlMatcher paymentUrlMatcher,
            String fallbackUrl, RestEnvironment restEnvironment, WebPaymentActivity webPaymentActivity) {
        this.cookieManager = cookieManager;
        this.dataEncoder = dataEncoder;
        this.addressBarPresenter = addressBarPresenter;
        this.client = new PaymentWebViewClient(paymentUrlMatcher, fallbackUrl, onAutomaticAuthorizationListener, restEnvironment);
        this.client.setPageLoadingCallback(pageLoadingCallback);
        this.onAuthorizationFinishedListenerOptional = Optional.absent();
        this.webPaymentActivity = webPaymentActivity;
    }

    @Override
    public void clearCache() {
        checkArgument(webPayment != null, "View should be set");

        webPayment.getWebView().clearCache(true);
        webPayment.getWebView().clearFormData();
        cookieManager.removeAllCookie();
    }

    @Override
    public boolean isWebBackStackEmpty() {
        checkArgument(webPayment != null, "View should be set");

        boolean canGoBack = webPayment.getWebView().canGoBack();
        if (canGoBack) {
            webPayment.getWebView().goBack();
        }
        return !canGoBack;
    }

    @Override
    public void loadUrl(String url, Map<String, String> parameters) {
        loadUrl(url, parameters, urlLoadType.POST);
    }

    @Override
    public void loadUrl(String url) {
        loadUrl(url, null, urlLoadType.GET);
    }

    private void loadUrl(String url, Map<String, String> parameters, urlLoadType loadType) {
        checkArgument(webPayment != null, "View should be set");

        if (isNullOrEmpty(url)) {
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.PAYMENT_ERROR, url));
        } else {
            webPayment.getWebView().clearSslPreferences();
            if (loadType == urlLoadType.POST) {
                webPayment.getWebView().postUrl(url, dataEncoder.encode(parameters));
            } else {
                webPayment.getWebView().loadUrl(url);

            }
        }
    }

    @Override
    public WebBackForwardList restoreState(Bundle inState) {
        checkArgument(webPayment != null, "View should be set");
        return webPayment.getWebView().restoreState(inState);
    }

    @Override
    public WebBackForwardList saveState(Bundle outState) {
        checkArgument(webPayment != null, "View should be set");

        return webPayment.getWebView().saveState(outState);
    }

    public void takeView(@NonNull Object view) {
        super.takeView(view);
        if (!(view instanceof WebPayment)) {
            throw new IllegalConfigurationException(String.format("Wrong view type, it should extend WebPayment interface"));
        }
        this.webPayment = (WebPayment) view;
        this.webPayment.getAddressBarView().setPresenter(addressBarPresenter);
        this.webPayment.getWebView().setWebViewClient(client);
        this.chromeClient = new WebAuthorizationViewChromeClient(this.webPayment.getProgressBar(), this.webPaymentActivity);
        this.webPayment.getWebView().setWebChromeClient(chromeClient);

    }

    public void setOnAuthorizationFinishedListener(@Nullable OnAuthorizationFinishedListener onAuthorizationFinishedListener) {
        this.onAuthorizationFinishedListenerOptional = Optional.fromNullable(onAuthorizationFinishedListener);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        chromeClient.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @NonNull
    private PaymentWebViewClient.OnPaymentAuthorizedListener onAutomaticAuthorizationListener = new PaymentWebViewClient
            .OnPaymentAuthorizedListener() {

        @Override
        public void onPaymentError() {
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.PAYMENT_ERROR, null));
        }

        @Override
        public void onPaymentSuccess() {
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.SUCCESS, null));
        }

        @Override
        public void onCvvRequired(@NonNull String continueUrl) {
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.WARNING_CONTINUE_CVV, continueUrl));
        }

        @Override
        public void onRedirectToMobileAppCalled() {
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.WARNING_CONTINUE_REDIRECT_TO_MOBILE_APP, null));
        }
    };

    private void invokeAuthorizationFinishedListener(WebPaymentWrapper webPaymentWrapper) {
        onAuthorizationFinishedListenerOptional.or(EMPTY_LISTENER).onAuthorizationFinished(webPaymentWrapper);
    }

    private PageLoadingCallback pageLoadingCallback = new PageLoadingCallback() {

        @Override
        public void onPageLoadStarted(String url) {
            checkArgument(webPayment != null, "View should be set");
            webPayment.getAddressBarView().setAddress(url);
        }

        @Override
        public void onSslValidationFailed(String url) {
            checkArgument(webPayment != null, "View should be set");
            webPayment.getAddressBarView().setAddressVerified(false);
            invokeAuthorizationFinishedListener(new WebPaymentWrapper(WebPaymentStatus.SSL_VALIDATION_ERROR, url));
        }
    };
}
