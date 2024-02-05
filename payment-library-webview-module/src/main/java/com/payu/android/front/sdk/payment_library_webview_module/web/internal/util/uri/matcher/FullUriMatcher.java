package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import static com.google.common.base.Preconditions.checkNotNull;

import android.net.Uri;

public class FullUriMatcher implements UriMatcher {

    private Uri mExpectedUri;

    public FullUriMatcher(Uri expectedUri) {
        checkNotNull(expectedUri);
        mExpectedUri = expectedUri;
    }

    @Override
    public boolean matches(Uri uri) {
        return mExpectedUri.equals(uri);
    }
}
