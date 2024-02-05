package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import com.google.common.base.Objects;

import java.util.List;

public class UriHostMatcher implements UriMatcher {

    private final List<String> mHosts;

    public UriHostMatcher(List<String> acceptedHostList) {
        mHosts = acceptedHostList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UriHostMatcher that = (UriHostMatcher) o;
        return Objects.equal(this.mHosts, that.mHosts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mHosts);
    }

    @Override
    public boolean matches(Uri uri) {
        return mHosts.contains(uri.getHost());
    }
}
