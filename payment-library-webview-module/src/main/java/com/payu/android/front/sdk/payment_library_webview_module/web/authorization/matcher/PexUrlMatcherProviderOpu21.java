package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.common.collect.Lists;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.FixedResponseUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.HostAndPathUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.MobileAppMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterNotPresentMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriParameterPresenceMatcher;

import java.util.List;


class PexUrlMatcherProviderOpu21 implements AuthorizationRedirectUriMatcherProvider {

    private static final String ERROR_PARAMETER_NAME = "error";
    private final Uri mBaseUri;

    PexUrlMatcherProviderOpu21(@NonNull String mBaseUrl) {
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
        return new UriParameterPresenceMatcher(ERROR_PARAMETER_NAME);
    }

    @Override
    public UriMatcher getSuccessUriMatcher() {
        return new UriParameterNotPresentMatcher(ERROR_PARAMETER_NAME);
    }

    @Override
    public UriMatcher getRedirectMobileAppMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }
}
