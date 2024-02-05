package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.AnyOfListedUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.FixedResponseUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.HostAndPathUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameter;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterValueMatcher;

import java.util.List;

class ThreeDSUrlMatcherProviderOpu21 implements AuthorizationRedirectUriMatcherProvider {

    private static final String STATUS_CODE_PARAMETER_NAME = "statusCode";
    private static final String STATUS_CODE_PARAMETER_VALUE_SUCCESS = "SUCCESS";
    private static final String STATUS_CODE_PARAMETER_VALUE_CVV = "WARNING_CONTINUE_CVV";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_SYNTAX = "ERROR_SYNTAX";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_VALUE_INVALID = "ERROR_VALUE_INVALID";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_VALUE_MISSING = "ERROR_VALUE_MISSING";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_UNAUTHORIZED = "UNAUTHORIZED";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_UNAUTHORIZED_REQUEST = "UNAUTHORIZED_REQUEST";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_DATA_NOT_FOUND = "DATA_NOT_FOUND";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_TIMEOUT = "TIMEOUT";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_BUSINESS_ERROR = "BUSINESS_ERROR";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_INTERNAL = "ERROR_INTERNAL";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_GENERAL = "GENERAL_ERROR";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_WARNING = "WARNING";
    private static final String STATUS_CODE_PARAMETER_VALUE_ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private final String mBaseUrl;

    ThreeDSUrlMatcherProviderOpu21(@NonNull String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public List<UriMatcher> getCommonUriMatcherList() {
        return Lists.<UriMatcher>newArrayList(new HostAndPathUriMatcher(Uri.parse(mBaseUrl)));
    }

    @Override
    public UriParameterValueMatcher getCvvUriMatcher() {
        return new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_CVV));
    }

    @Override
    public UriMatcher getErrorUriMatcher() {
        return new AnyOfListedUriMatcher(
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_SYNTAX)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_VALUE_INVALID)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_VALUE_MISSING)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_UNAUTHORIZED)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_UNAUTHORIZED_REQUEST)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_DATA_NOT_FOUND)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_TIMEOUT)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_BUSINESS_ERROR)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_INTERNAL)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_GENERAL)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_WARNING)),
                new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_ERROR_SERVICE_NOT_AVAILABLE))
        );
    }

    @Override
    public UriParameterValueMatcher getSuccessUriMatcher() {
        return new UriParameterValueMatcher(new UriParameter(STATUS_CODE_PARAMETER_NAME, STATUS_CODE_PARAMETER_VALUE_SUCCESS));
    }

    @Override
    public UriMatcher getRedirectMobileAppMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }
}
