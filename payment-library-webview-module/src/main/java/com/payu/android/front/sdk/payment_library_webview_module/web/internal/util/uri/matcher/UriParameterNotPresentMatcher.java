package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;
import com.google.common.base.Objects;

public class UriParameterNotPresentMatcher implements UriMatcher {

    private final String mParameterName;

    public UriParameterNotPresentMatcher(String parameterName) {
        mParameterName = parameterName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UriParameterNotPresentMatcher that = (UriParameterNotPresentMatcher) o;
        return Objects.equal(this.mParameterName, that.mParameterName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mParameterName);
    }

    @Override
    public boolean matches(Uri uri) {
        return uri.getQueryParameter(mParameterName) == null;
    }
}
