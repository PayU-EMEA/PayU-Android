package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;


import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;

import java.util.List;

interface AuthorizationRedirectUriMatcherProvider {

    List<UriMatcher> getCommonUriMatcherList();

    UriMatcher getCvvUriMatcher();

    UriMatcher getErrorUriMatcher();

    UriMatcher getSuccessUriMatcher();

    UriMatcher getRedirectMobileAppMatcher();
}
