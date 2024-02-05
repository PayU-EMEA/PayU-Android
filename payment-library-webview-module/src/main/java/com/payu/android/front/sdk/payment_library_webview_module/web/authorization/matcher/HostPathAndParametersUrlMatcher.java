package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import android.net.Uri;

import com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatcher;

import java.util.List;

import static com.google.common.collect.Iterables.all;
import static com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher.UriMatchingPredicate.thatMatches;

class HostPathAndParametersUrlMatcher implements PaymentUrlMatcher {
    private final List<UriMatcher> mHostAndPathMatcherList;
    private final UriMatcher mSuccessMatcher;
    private final UriMatcher mErrorMatcher;
    private final UriMatcher mCvvMatcher;
    private final UriMatcher mOpenMobileApp;

    public HostPathAndParametersUrlMatcher(AuthorizationRedirectUriMatcherProvider dataProvider) {
        mHostAndPathMatcherList = dataProvider.getCommonUriMatcherList();
        mSuccessMatcher = dataProvider.getSuccessUriMatcher();
        mErrorMatcher = dataProvider.getErrorUriMatcher();
        mCvvMatcher = dataProvider.getCvvUriMatcher();
        mOpenMobileApp = dataProvider.getRedirectMobileAppMatcher();
    }

    @Override
    public boolean isPaymentCvvRequiredUrl(String url) {
        return isMatchingParameters(url, mCvvMatcher);
    }

    @Override
    public boolean isPaymentErrorUrl(String url) {
        return isMatchingCommonAndParameters(url, mErrorMatcher);
    }

    @Override
    public boolean isPaymentSuccessUrl(String url) {
        return isMatchingCommonAndParameters(url, mSuccessMatcher);
    }

    @Override
    public boolean isPaymentRequireOpenMobileApp(String url) {
        if (url != null) {
            Uri uri = Uri.parse(url);
            return mOpenMobileApp.matches(uri);
        }
        return false;
    }

    private boolean doesCommonUriPartMatches(Uri uri) {
        return all(mHostAndPathMatcherList, thatMatches(uri));
    }

    private boolean isMatchingParameters(String url, UriMatcher parameterMatcher) {

        if (url != null) {
            Uri uri = Uri.parse(url);
            return parameterMatcher.matches(uri);
        }

        return false;
    }

    private boolean isMatchingCommonAndParameters(String url, UriMatcher parameterMatcher) {

        if (url != null) {
            Uri uri = Uri.parse(url);
            return doesCommonUriPartMatches(uri) && parameterMatcher.matches(uri);
        }

        return false;
    }

}
