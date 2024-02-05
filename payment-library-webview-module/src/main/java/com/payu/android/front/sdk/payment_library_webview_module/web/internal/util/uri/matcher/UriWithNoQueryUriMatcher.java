package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

public class UriWithNoQueryUriMatcher implements UriMatcher {

    @Override
    public boolean matches(Uri uri) {
        return uri.getQuery() == null;
    }
}
