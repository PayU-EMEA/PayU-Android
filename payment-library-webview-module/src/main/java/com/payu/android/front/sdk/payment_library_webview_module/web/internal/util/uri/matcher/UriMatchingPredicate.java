package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import com.google.common.base.Predicate;

public class UriMatchingPredicate implements Predicate<UriMatcher> {

    private final Uri mUri;

    public UriMatchingPredicate(Uri uri) {
        mUri = uri;
    }

    public static UriMatchingPredicate thatMatches(Uri uri) {
        return new UriMatchingPredicate(uri);
    }

    @Override
    public boolean apply(UriMatcher input) {
        return input.matches(mUri);
    }
}
