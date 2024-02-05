package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import com.google.common.base.Objects;

public class UriParameterValueMatcher implements UriMatcher {

    private final UriParameter mUriParameter;

    public UriParameterValueMatcher(UriParameter uriParameter) {
        mUriParameter = uriParameter;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UriParameterValueMatcher that = (UriParameterValueMatcher) o;
        return Objects.equal(this.mUriParameter, that.mUriParameter);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mUriParameter);
    }

    @Override
    public boolean matches(Uri uri) {
        String queryParameter = uri.getQueryParameter(mUriParameter.getName());
        return mUriParameter.getValue().equals(queryParameter);
    }
}
