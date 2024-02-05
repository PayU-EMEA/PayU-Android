package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;

public class FixedResponseUriMatcher implements UriMatcher {

    private boolean mFixedResponse;

    private FixedResponseUriMatcher(boolean fixedResponse) {
        mFixedResponse = fixedResponse;
    }

    public static FixedResponseUriMatcher alwaysNegative() {
        return new FixedResponseUriMatcher(false);
    }

    public static FixedResponseUriMatcher alwaysPositive() {
        return new FixedResponseUriMatcher(true);
    }

    @VisibleForTesting
    public boolean getFixedResponse() {
        return mFixedResponse;
    }

    @Override
    public boolean matches(Uri uri) {
        return mFixedResponse;
    }
}
