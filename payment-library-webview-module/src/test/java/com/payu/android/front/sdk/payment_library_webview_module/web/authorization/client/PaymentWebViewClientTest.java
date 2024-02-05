package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import android.webkit.WebView;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class PaymentWebViewClientTest {

    public static final String TEST_URL = "test";
    PaymentWebViewClient objectUnderText;
    @Mock
    PaymentWebViewClient.OnPaymentAuthorizedListener mockPaymentListener;
    @Mock
    WebView webViewMock;
    @Mock
    PaymentUrlMatcher mockUrlMatcher;
    @Mock
    RestEnvironment mockRestEnvironment;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderText = new PaymentWebViewClient(mockUrlMatcher, "", mockPaymentListener, mockRestEnvironment);
    }


    @Test
    public void shouldCallErrorListenerWhenErrorUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentErrorUrl(eq(TEST_URL))).thenReturn(true);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        verify(mockPaymentListener).onPaymentError();
    }

    @Test
    public void shouldCallListenerOnWebViewError() {
        // when
        objectUnderText.onReceivedError(webViewMock, 0, "", null);

        // then
        verify(mockPaymentListener).onPaymentError();
    }

    @Test
    public void shouldCallSuccessListenerWhenSuccessUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentSuccessUrl(eq(TEST_URL))).thenReturn(true);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        verify(mockPaymentListener).onPaymentSuccess();
    }

    @Test
    public void shouldNotCallAnyListeners() {
        // given
        when(mockUrlMatcher.isPaymentErrorUrl(eq(TEST_URL))).thenReturn(false);
        when(mockUrlMatcher.isPaymentSuccessUrl(eq(TEST_URL))).thenReturn(false);
        when(mockUrlMatcher.isPaymentCvvRequiredUrl(eq(TEST_URL))).thenReturn(false);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        verifyNoInteractions(mockPaymentListener);
    }

    @Test
    public void shouldNotOverrideUrlLoading() {
        // given
        when(mockUrlMatcher.isPaymentErrorUrl(eq(TEST_URL))).thenReturn(false);
        when(mockUrlMatcher.isPaymentCvvRequiredUrl(eq(TEST_URL))).thenReturn(false);
        when(mockUrlMatcher.isPaymentSuccessUrl(eq(TEST_URL))).thenReturn(false);

        // when
        boolean shouldOverrideUrlLoading = objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        assertThat(shouldOverrideUrlLoading).isFalse();
    }

    @Test
    public void shouldOverrideUrlLoadingWhenErrorUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentErrorUrl(eq(TEST_URL))).thenReturn(true);

        // when
        boolean shouldOverrideUrlLoading = objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        assertThat(shouldOverrideUrlLoading).isTrue();
    }

    @Test
    public void shouldOverrideUrlLoadingWhenSuccessUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentSuccessUrl(eq(TEST_URL))).thenReturn(true);

        // when
        boolean shouldOverrideUrlLoading = objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        assertThat(shouldOverrideUrlLoading).isTrue();
    }

    @Test
    public void shouldClearCacheWhenSuccessUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentSuccessUrl(eq(TEST_URL))).thenReturn(true);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        verify(webViewMock).clearCache(eq(true));
    }

    @Test
    public void shouldClearCacheWhenErrorUriMatches() {
        // given
        when(mockUrlMatcher.isPaymentErrorUrl(eq(TEST_URL))).thenReturn(true);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, TEST_URL);

        // then
        verify(webViewMock).clearCache(eq(true));
    }

    @Test
    public void shouldClearCacheWhenCvvUriMatches() {
        // given
        String referenceId = "123";
        String urlWithTransactionId = "http://mock.payu.pl/test?refReqId=" + referenceId;
        when(mockUrlMatcher.isPaymentCvvRequiredUrl(eq(urlWithTransactionId))).thenReturn(true);

        // when
        boolean shouldOverride = objectUnderText.shouldOverrideUrlLoading(webViewMock, urlWithTransactionId);

        // then
        assertThat(shouldOverride).isTrue();
    }

    @Test
    public void shouldCallCvvRequiredMethods() {
        // given
        String referenceId = "123";
        String urlWithTransactionId = "http://mock.payu.pl/test?refReqId=" + referenceId;
        when(mockUrlMatcher.isPaymentCvvRequiredUrl(eq(urlWithTransactionId))).thenReturn(true);

        // when
        objectUnderText.shouldOverrideUrlLoading(webViewMock, urlWithTransactionId);

        // then
        verify(mockPaymentListener, times(1)).onCvvRequired(urlWithTransactionId);
    }
}