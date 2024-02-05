package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.AllOfListedUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.AnyOfListedUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.FixedResponseUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.HostAndPathUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterNotPresentMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterPresenceMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriWithNoQueryUriMatcher;

import java.util.List;


class PayByLinkUrlMatcherProvider implements AuthorizationRedirectUriMatcherProvider {

    private static final String ERROR_PARAMETER_NAME = "error";
    private static final String FAILURE_PARAMETER_NAME = "failure";
    private final Uri mBaseUri;

    PayByLinkUrlMatcherProvider(@NonNull String mBaseUrl) {
        mBaseUri = Uri.parse(mBaseUrl);
    }

    @Override
    public List<UriMatcher> getCommonUriMatcherList() {
        return Lists.<UriMatcher>newArrayList(new HostAndPathUriMatcher(mBaseUri));
    }

    @Override
    public UriMatcher getCvvUriMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }

    @Override
    public UriMatcher getErrorUriMatcher() {
        return new AnyOfListedUriMatcher(new UriParameterPresenceMatcher(ERROR_PARAMETER_NAME), new UriParameterPresenceMatcher(FAILURE_PARAMETER_NAME));

    }

    @Override
    public UriMatcher getSuccessUriMatcher() {
        return new AnyOfListedUriMatcher(new UriWithNoQueryUriMatcher(), new AllOfListedUriMatcher(new UriParameterNotPresentMatcher(ERROR_PARAMETER_NAME), new UriParameterNotPresentMatcher(FAILURE_PARAMETER_NAME)));
    }

    @Override
    public UriMatcher getRedirectMobileAppMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }
}
