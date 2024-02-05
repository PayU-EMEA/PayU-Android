package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.FixedResponseUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.HostAndPathUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameter;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterValueMatcher;

import java.util.List;

class ThreeDSUrlMatcherProvider implements AuthorizationRedirectUriMatcherProvider {

    private static final String STATUS_CODE_PARAMETER_NAME = "statusCode";
    private final String mBaseUrl;

    ThreeDSUrlMatcherProvider(@NonNull String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public List<UriMatcher> getCommonUriMatcherList() {
        return Lists.<UriMatcher>newArrayList(new HostAndPathUriMatcher(Uri.parse(mBaseUrl)));
    }

    @Override
    public UriParameterValueMatcher getCvvUriMatcher() {
        return new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, "WARNING_CONTINUE_CVV"));
    }

    @Override
    public UriMatcher getErrorUriMatcher() {
        return new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, "ERROR_INTERNAL"));
    }

    @Override
    public UriParameterValueMatcher getSuccessUriMatcher() {
        return new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, "SUCCESS"));
    }

    @Override
    public UriMatcher getRedirectMobileAppMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }
}
