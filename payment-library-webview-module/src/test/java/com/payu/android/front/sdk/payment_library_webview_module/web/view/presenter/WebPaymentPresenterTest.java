package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;


import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.configuration.IllegalConfigurationException;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.OnAuthorizationFinishedListener;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.PostDataEncoder;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentStatus;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentWrapper;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.AddressBarView;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.WebPaymentView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class WebPaymentPresenterTest {

    WebPaymentPresenter objectUnderTest;

    @Mock
    AddressBarPresenter addressBarPresenter;

    @Mock
    CookieManager cookieManager;

    @Mock
    PostDataEncoder postDataEncoder;

    @Mock
    PaymentUrlMatcher paymentUrlMatcher;

    @Mock
    RestEnvironment restEnvironment;

    @Mock
    Map<String, String> parameters;

    @Mock
    Bundle bundle;

    @Mock
    WebView webView;

    @Mock
    ProgressBar progressBar;

    @Mock
    AddressBarView addressBarView;

    @Mock
    View view;

    @Mock
    WebPaymentView webPaymentViews;

    @Mock
    OnAuthorizationFinishedListener onAuthorizationFinishedListener;

    @Mock
    WebPaymentWrapper webPaymentWrapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new WebPaymentPresenter(addressBarPresenter, cookieManager, postDataEncoder, paymentUrlMatcher,"", restEnvironment);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenLoadUrlWasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.loadUrl("http://test.pl");

        //then
        //should throw an error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenLoadUrl2WasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.loadUrl("http://test.pl", parameters);

        //then
        //should throw an error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenClearCacheWasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.clearCache();

        //then
        //should throw an error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenIsWebBackStackEmptyWasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.isWebBackStackEmpty();

        //then
        //should throw an error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenRestoreStateWasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.restoreState(bundle);

        //then
        //should throw an error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCrashWithoutProvidedViewWhenSaveStateWasCalled() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.saveState(bundle);

        //then
        //should throw an error
    }

    @Test(expected = IllegalConfigurationException.class)
    public void shouldCrashWhenProvidedViewIsNotWebPaymentType() {
        //given
        //init constructor in @Before method

        //when
        objectUnderTest.takeView(view);

        //then
        //should throw an error
    }

    @Test
    public void shouldConfiguredProperlyWhenWebPaymentViewIsProvided() {
        //given
        //init constructor in @Before method
        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);

        //when
        objectUnderTest.takeView(webPaymentViews);

        //then
        verify(addressBarView, times(1)).setPresenter(addressBarPresenter);
        verify(webView, times(1)).setWebViewClient(any(WebViewClient.class));
        verify(webView, times(1)).setWebChromeClient(any(WebChromeClient.class));

    }

    @Test
    public void shouldClearCacheWhenClearCacheMethodWasCalled() {
        //given
        //init constructor in @Before method
        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);
        objectUnderTest.takeView(webPaymentViews);

        //when
        objectUnderTest.clearCache();

        //then
        verify(webView, times(1)).clearCache(true);
        verify(webView, times(1)).clearFormData();
        verify(cookieManager, times(1)).removeAllCookie();

    }

    @Test
    public void shouldGoBackWithHistoryWhenGoBackMethodWasCalled() {
        //given
        //init constructor in @Before method
        when(webView.canGoBack()).thenReturn(true);

        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);

        objectUnderTest.takeView(webPaymentViews);

        //when
        boolean statement = objectUnderTest.isWebBackStackEmpty();

        //then
        verify(webView, times(1)).goBack();
        assertTrue(!statement);

    }

    @Test
    public void shouldNotGoBackWithHistoryWhenGoBackMethodWasCalled() {
        //given
        //init constructor in @Before method
        when(webView.canGoBack()).thenReturn(false);

        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);

        objectUnderTest.takeView(webPaymentViews);

        //when
        boolean statement = objectUnderTest.isWebBackStackEmpty();

        //then
        verify(webView, times(0)).goBack();
        assertTrue(statement);

    }

    /* TODO: fix this test
        @Test
        public void shouldCallErrorOnFinishListenerWhenProvidedUrlIsEmpty() {
            //given
            //init constructor in @Before method
            when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
            when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
            when(webPaymentViews.getWebView()).thenReturn(webView);
            objectUnderTest.setOnAuthorizationFinishedListener(onAuthorizationFinishedListener);
            objectUnderTest.takeView(webPaymentViews);

            //when
            objectUnderTest.loadUrl("");

            //then
            verify(onAuthorizationFinishedListener, times(1)).onAuthorizationFinished(check if instance return an ERROR));
        }
        */
    /* TODO: fix this test
        @Test
        public void shouldCallErrorOnFinishListenerWhenProvidedUrl2IsEmpty() {
            //given
            //init constructor in @Before method
            when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
            when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
            when(webPaymentViews.getWebView()).thenReturn(webView);
            objectUnderTest.setOnAuthorizationFinishedListener(onAuthorizationFinishedListener);
            objectUnderTest.takeView(webPaymentViews);

            //when
            objectUnderTest.loadUrl("", parameters);

            //then
            verify(onAuthorizationFinishedListener, times(1)).onAuthorizationFinished(check if instance return an ERROR in wrapper));
        }
    */
    @Test
    public void shouldLoadUrlWhenUrlWasProvided() {
        //given
        //init constructor in @Before method
        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);
        objectUnderTest.setOnAuthorizationFinishedListener(onAuthorizationFinishedListener);
        objectUnderTest.takeView(webPaymentViews);

        String url = "https://test.pl";

        //when
        objectUnderTest.loadUrl(url);

        //then
        verify(webView, times(1)).clearSslPreferences();
        verify(webView, times(1)).loadUrl(url);

    }

    @Test
    public void shouldLoadUrlWhenUrl2WasProvided() {
        //given
        //init constructor in @Before method
        when(webPaymentViews.getAddressBarView()).thenReturn(addressBarView);
        when(webPaymentViews.getProgressBar()).thenReturn(progressBar);
        when(webPaymentViews.getWebView()).thenReturn(webView);
        objectUnderTest.setOnAuthorizationFinishedListener(onAuthorizationFinishedListener);
        objectUnderTest.takeView(webPaymentViews);

        String url = "https://test.pl";

        //when
        objectUnderTest.loadUrl(url, parameters);

        //then
        verify(webView, times(1)).clearSslPreferences();
        verify(webView, times(1)).postUrl(eq(url), any());

    }
}
