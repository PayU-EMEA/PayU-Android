package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client.PageLoadingCallback.HOLLOW_IMPLEMENTATION;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.util.PDFUtils;
import com.payu.android.front.sdk.payment_library_core_android.util.RedirectUtils;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcher;

import java.net.URISyntaxException;


public class PaymentWebViewClient extends WebViewClient {

    private static final String BROWSER_FALLBACK_URL = "browser_fallback_url";

    public interface OnPaymentAuthorizedListener {

        OnPaymentAuthorizedListener EMPTY_LISTENER = new OnPaymentAuthorizedListener() {

            @Override
            public void onPaymentError() {
                // empty listener
            }

            @Override
            public void onPaymentSuccess() {
                // empty listener
            }

            @Override
            public void onCvvRequired(@NonNull String continueLink) {
                //empty listener
            }

            @Override
            public void onRedirectToMobileAppCalled() {
                //empty listener
            }
        };


        void onPaymentError();

        void onPaymentSuccess();

        void onCvvRequired(@NonNull String continueLink);

        void onRedirectToMobileAppCalled();
    }

    private static final String TAG = PaymentWebViewClient.class.getSimpleName();
    private final OnPaymentAuthorizedListener mOnFinishListener;
    private final RestEnvironment mRestEnvironment;
    private final String mFallbackUrl;

    private PaymentUrlMatcher mUrlMatcher = PaymentUrlMatcher.EMPTY_MATCHER;
    private PageLoadingCallback mPageLoadingCallback = HOLLOW_IMPLEMENTATION;

    public PaymentWebViewClient(PaymentUrlMatcher urlMatcher, String fallbackUrl, OnPaymentAuthorizedListener onFinishListener,
                                RestEnvironment restEnvironment) {
        mUrlMatcher = urlMatcher;
        mFallbackUrl = fallbackUrl;
        mOnFinishListener = onFinishListener;
        mRestEnvironment = restEnvironment;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mPageLoadingCallback.onPageLoadStarted(url);
    }


    @Override
    public void onLoadResource(WebView view, String url) {
        if (mUrlMatcher.isPaymentRequireOpenMobileApp(url)) {
            HandleAppIntentStatuses status = handleAppIntent(view, url, mFallbackUrl, startsWithIntent(url));
            if (status != HandleAppIntentStatuses.SUCCESS) {
                super.onLoadResource(view, url);
            } else {
                view.clearCache(true);
            }
        } else {
            super.onLoadResource(view, url);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Log.v(TAG, "onReceivedError code: " + errorCode + "/" + description + "/" + failingUrl);
        getOnPaymentAuthorizedListener().onPaymentError();
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (mRestEnvironment.isPinningEnabled()) {
            Log.v(TAG, "ReceivedSslError");
            handler.cancel();
            mPageLoadingCallback.onSslValidationFailed(getUrlFromErrorOrFallback(error));
        } else {
            handler.proceed();
        }
    }

    public void setPageLoadingCallback(PageLoadingCallback pageLoadingCallback) {
        mPageLoadingCallback = pageLoadingCallback == null ? HOLLOW_IMPLEMENTATION : pageLoadingCallback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.v(TAG, "Loading url into component: " + url);

        if (mUrlMatcher.isPaymentSuccessUrl(url)) {
            Log.v(TAG, "Successful authorization matched automatically");
            view.clearCache(true);
            getOnPaymentAuthorizedListener().onPaymentSuccess();
            return true;
        } else if (mUrlMatcher.isPaymentCvvRequiredUrl(url)) {
            Log.v(TAG, "CVV required matched automatically");
            view.clearCache(true);
            getOnPaymentAuthorizedListener().onCvvRequired(url);
            return true;
        } else if (mUrlMatcher.isPaymentErrorUrl(url)) {
            Log.v(TAG, "Payment error matched automatically");
            view.clearCache(true);
            getOnPaymentAuthorizedListener().onPaymentError();
            return true;
        } else if (mUrlMatcher.isPaymentRequireOpenMobileApp(url)) {
            view.clearCache(true);
            HandleAppIntentStatuses status = handleAppIntent(view, url, mFallbackUrl, startsWithIntent(url));

            return true;
        } else if (urlIsPdfFile(url)) {
            Log.v(TAG, "Redirection opening url files");
            try {
                PDFUtils.openPdfFileInExternalSourceWithoutType(view.getContext(), url);
            } catch (ActivityNotFoundException e) {
                //TBD
            }
            return true;
        } else if (isRedirectToMail(url)) {
            RedirectUtils.openMail(view.getContext(), url);
            return true;
        } else if (isRedirectToPhone(url)) {
            RedirectUtils.openPhone(view.getContext(), url);
            return true;
        }
        return false;
    }

    private OnPaymentAuthorizedListener getOnPaymentAuthorizedListener() {
        return firstNonNull(mOnFinishListener, OnPaymentAuthorizedListener.EMPTY_LISTENER);
    }

    private String getUrlFromErrorOrFallback(SslError error) {
        return error.getUrl();
    }

    private HandleAppIntentStatuses openAppIntent(@NonNull WebView view, @NonNull String url, @Nullable String fallbackTransactionUrl, boolean startsWithIntent) throws URISyntaxException {
        Context context = view.getContext().getApplicationContext();
        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (intent != null) {
            PackageManager packageManager = context.getPackageManager();
            ResolveInfo info = packageManager.resolveActivity(
                    intent,
                    PackageManager.MATCH_DEFAULT_ONLY
            );

            if (info != null) {
                context.startActivity(intent);
                return HandleAppIntentStatuses.SUCCESS;
            } else if (startsWithIntent) {
                String fallbackUrl = intent.getStringExtra(BROWSER_FALLBACK_URL);
                view.loadUrl(fallbackUrl);
                return HandleAppIntentStatuses.FALLBACK;
            } else if (fallbackTransactionUrl != null) {
                view.loadUrl(fallbackTransactionUrl);
                return HandleAppIntentStatuses.FALLBACK;
            }
        }
        return HandleAppIntentStatuses.FAILURE;
    }

    private HandleAppIntentStatuses handleAppIntent(@NonNull WebView view, @NonNull String url, @Nullable String fallbackTransactionUrl, boolean startsWithIntent) {
        Log.v(TAG, "trying to open custom schema: " + url);
        try {
            HandleAppIntentStatuses openedAnApp = openAppIntent(view, url, fallbackTransactionUrl, startsWithIntent);
            if (openedAnApp == HandleAppIntentStatuses.SUCCESS) {
                getOnPaymentAuthorizedListener().onRedirectToMobileAppCalled();
            }
            return openedAnApp;

        } catch (URISyntaxException e) {
            Log.v(TAG, "Cannot open custom url");
            getOnPaymentAuthorizedListener().onPaymentError();
            return HandleAppIntentStatuses.ERROR;
        }
    }

    private enum HandleAppIntentStatuses {
        SUCCESS, FAILURE, ERROR, FALLBACK
    }

    private boolean startsWithIntent(@NonNull String url) {
        return url.startsWith("intent://");
    }

    private boolean urlIsPdfFile(@NonNull String url) {
        return url.contains(".pdf");
    }

    private boolean isRedirectToMail(@NonNull String url) {
        return url.startsWith("mailto:");
    }

    private boolean isRedirectToPhone(@NonNull String url) {
        return url.startsWith("tel:");
    }
}
