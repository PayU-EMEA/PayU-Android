package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import com.google.common.base.Objects;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Iterables.all;
import static java.util.Arrays.asList;

public class AllOfListedUriMatcher implements UriMatcher {
    private List<UriMatcher> mUriMatcherList;

    public AllOfListedUriMatcher(UriMatcher... uriMatcherList) {
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

        AllOfListedUriMatcher that = (AllOfListedUriMatcher) o;
        return Objects.equal(this.mUriMatcherList, that.mUriMatcherList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mUriMatcherList);
    }

    @Override
    public boolean matches(Uri uri) {
        return all(mUriMatcherList, UriMatchingPredicate.thatMatches(uri));
    }
}
