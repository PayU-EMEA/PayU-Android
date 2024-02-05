package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.FixedResponseUriMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.MobileAppMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;

import java.util.Collections;
import java.util.List;

public class MobileMatcherProvider implements AuthorizationRedirectUriMatcherProvider {
    @Override
    public List<UriMatcher> getCommonUriMatcherList() {
        return Collections.emptyList();
    }

    @Override
    public UriMatcher getCvvUriMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }

    @Override
    public UriMatcher getErrorUriMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }

    @Override
    public UriMatcher getSuccessUriMatcher() {
        return FixedResponseUriMatcher.alwaysNegative();
    }

    @Override
    public UriMatcher getRedirectMobileAppMatcher() {
        return new MobileAppMatcher();
    }
}
