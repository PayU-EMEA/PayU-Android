package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import static com.google.common.collect.Iterables.any;

import static java.util.Arrays.asList;

import android.net.Uri;

import com.google.common.base.Objects;

import java.util.Collections;
import java.util.List;

public class AnyOfListedUriMatcher implements UriMatcher {

    private List<UriMatcher> mUriMatcherList;

    public AnyOfListedUriMatcher(UriMatcher... uriMatcherList) {
        mUriMatcherList = uriMatcherList == null ? Collections.<UriMatcher>emptyList() : asList(uriMatcherList);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnyOfListedUriMatcher that = (AnyOfListedUriMatcher) o;
        return Objects.equal(this.mUriMatcherList, that.mUriMatcherList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mUriMatcherList);
    }

    @Override
    public boolean matches(Uri uri) {
        return any(mUriMatcherList, UriMatchingPredicate.thatMatches(uri));
    }
}
