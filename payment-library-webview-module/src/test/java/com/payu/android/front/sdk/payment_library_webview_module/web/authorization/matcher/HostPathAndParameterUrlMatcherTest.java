package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;


import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class HostPathAndParameterUrlMatcherTest {

    HostPathAndParametersUrlMatcher objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProvider(
                "https://secure.payu.com.te4.local/pl/standard/co/summary"));
    }

    @Test
    public void shouldMatchCvvUrl() {
        // given
        String successUrl =
                "https://secure.payu.com.te4.local/pl/standard/co/summary?statusCode=WARNING_CONTINUE_CVV&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentCvvRequiredUrl(successUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldMatchErrorUrlWithErrorStatus() {
        // given
        String successUrl = "https://secure.payu.com.te4.local/pl/standard/co/summary?statusCode=ERROR_INTERNAL&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentErrorUrl(successUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldMatchSuccessUrl() {
        // given
        String successUrl = "https://secure.payu.com.te4.local/pl/standard/co/summary?statusCode=SUCCESS&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(successUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldMatchCvvUrlForDifferentHost() {
        // given
        String successUrl = "https://secure.payu.com.ru"
                + ".te4.local/pl/standard/co/summary2?statusCode=WARNING_CONTINUE_CVV&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentCvvRequiredUrl(successUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldNotMatchCvvUrlForDifferentParameter() {
        // given
        String successUrl =
                "https://secure.payu.com.te4" + ".local/pl/standard/co/summary?statusCode=WARNING_CONTINUE_CV&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentCvvRequiredUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldMatchCvvUrlForDifferentPath() {
        // given
        String successUrl =
                "https://secure.payu.com" + ".te4.local/pl/standard/co/summary2?statusCode=WARNING_CONTINUE_CVV&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentCvvRequiredUrl(successUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldNotMatchErrorUrlWithSuccessStatus() {
        // given
        String successUrl = "https://secure.payu.com.te4.local/pl/standard/co/summary?statusCode=SUCCESS&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentErrorUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlWithDifferentHost() {
        // given
        String successUrl = "https://secure.payu.ru/pl/standard/co/summary?statusCode=ERROR_INTERNAL&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentErrorUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchFailureUrlWithDifferentParameters() {
        // given
        String successUrl = "https://secure.payu.com/pl/standard/co/summary2?otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentErrorUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchFailureUrlWithDifferentPath() {
        // given
        String successUrl = "https://secure.payu.com/pl/standard/co/summary2?statusCode=ERROR_INTERNAL&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentErrorUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchFailureUrlWithDifferentHost() {
        // given
        String successUrl = "https://secure.payu.ru/pl/standard/co/summary?statusCode=SUCCESS&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlWithDifferentParameters() {
        // given
        String successUrl = "https://secure.payu.com/pl/standard/co/summary2?otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlWithDifferentPath() {
        // given
        String successUrl = "https://secure.payu.com/pl/standard/co/summary2?statusCode=SUCCESS&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlWithErrorStatus() {
        // given
        String successUrl = "https://secure.payu.com.te4.local/pl/standard/co/summary?statusCode=ERROR_INTERNAL&otherParam=false";

        // when
        boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(successUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldMatchErrorUrlForErrorStatusAndSameForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("error", "error_code").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isError = objectUnderTest.isPaymentErrorUrl(continueUrl);

        // then
        assertThat(isError).isTrue();
    }

    @Test
    public void shouldNotMatchErrorUrlForErrorStatusAndDifferentForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.other.url.com").appendQueryParameter("error", "error_code").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isError = objectUnderTest.isPaymentErrorUrl(continueUrl);

        // then
        assertThat(isError).isFalse();
    }

    @Test
    public void shouldNotMatchCvvUrlForErrorStatusForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("error", "error_code").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isCvvRequired = objectUnderTest.isPaymentCvvRequiredUrl(continueUrl);

        // then
        assertThat(isCvvRequired).isFalse();
    }

    @Test
    public void shouldNotMatchCvvUrlForSuccessStatusForOpu21PexMatcher() {
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isCvvRequired = objectUnderTest.isPaymentCvvRequiredUrl(continueUrl);

        // then
        assertThat(isCvvRequired).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlForErrorStatusAndSameHostForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("error", "error_value").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchSuccessUrlForSuccessStatusAndDifferentHostForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.other.url.com").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldMatchSuccessUrlForSuccessStatusAndSameHostForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldNotMatchSuccessUrlForUnknownStatusAndSameHostForOpu21PexMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("randomParam", "randomValue").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new PexUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldNotMatchSuccessUrlForErrorStatusAndSameHostForOpu213DsMatcher() {

        final String[] errors = new String[]{
                "ERROR_SYNTAX",
                "ERROR_VALUE_INVALID",
                "ERROR_VALUE_MISSING",
                "UNAUTHORIZED",
                "UNAUTHORIZED_REQUEST",
                "DATA_NOT_FOUND",
                "TIMEOUT",
                "BUSINESS_ERROR",
                "ERROR_INTERNAL",
                "GENERAL_ERROR",
                "WARNING",
                "SERVICE_NOT_AVAILABLE"
        };
        for (String error : errors) {
            // given
            final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
            final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("statusCode", error).build().toString();
            final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

            // when
            final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

            // then
            assertThat(isSuccess).isFalse();
        }
    }

    @Test
    public void shouldNotMatchSuccessUrlForSuccessStatusAndDifferentHostForOpu213DsMatcher() {
        final String[] errors = new String[]{
                "ERROR_SYNTAX",
                "ERROR_VALUE_INVALID",
                "ERROR_VALUE_MISSING",
                "UNAUTHORIZED",
                "UNAUTHORIZED_REQUEST",
                "DATA_NOT_FOUND",
                "TIMEOUT",
                "BUSINESS_ERROR",
                "ERROR_INTERNAL",
                "GENERAL_ERROR",
                "WARNING",
                "SERVICE_NOT_AVAILABLE"
        };
        for (String error : errors) {
            // given
            final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
            final String continueUrl = new Uri.Builder().scheme("http").authority("some.other.url.com").appendQueryParameter("statusCode", error).build().toString();
            final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

            // when
            final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

            // then
            assertThat(isSuccess).isFalse();
        }
    }

    @Test
    public void shouldNotMatchSuccessUrlForContinueCvvStatusAndDifferentHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.other.url.com").appendQueryParameter("statusCode", "WARNING_CONTINUE_CVV").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldMatchSuccessUrlForSuccessStatusAndSameHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("statusCode", "SUCCESS").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isTrue();
    }

    @Test
    public void shouldNotMatchSuccessUrlForDifferentStatusAndSameHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("someParam", "someValue").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isSuccess = objectUnderTest.isPaymentSuccessUrl(continueUrl);

        // then
        assertThat(isSuccess).isFalse();
    }

    @Test
    public void shouldNotMatchErrorUrlForDifferentStatusAndSameHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("someParam", "someValue").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isError = objectUnderTest.isPaymentErrorUrl(continueUrl);

        // then
        assertThat(isError).isFalse();
    }

    @Test
    public void shouldNotMatchCvvUrlForDifferentStatusAndSameHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("someParam", "someValue").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isCvvRequired = objectUnderTest.isPaymentCvvRequiredUrl(continueUrl);

        // then
        assertThat(isCvvRequired).isFalse();
    }

    @Test
    public void shouldMatchCvvUrlForCvvRequiredStatusAndSameHostForOpu213DsMatcher() {
        // given
        final String baseUrl = new Uri.Builder().scheme("http").authority("some.url.com").build().toString();
        final String continueUrl = new Uri.Builder().scheme("http").authority("some.url.com").appendQueryParameter("statusCode", "WARNING_CONTINUE_CVV").build().toString();
        final HostPathAndParametersUrlMatcher objectUnderTest = new HostPathAndParametersUrlMatcher(new ThreeDSUrlMatcherProviderOpu21(baseUrl));

        // when
        final boolean isCvvRequired = objectUnderTest.isPaymentCvvRequiredUrl(continueUrl);

        // then
        assertThat(isCvvRequired).isTrue();
    }
}