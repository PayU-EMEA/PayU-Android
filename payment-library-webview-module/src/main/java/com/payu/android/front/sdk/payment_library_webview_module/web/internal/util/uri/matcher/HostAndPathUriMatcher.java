package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;
import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class HostAndPathUriMatcher implements UriMatcher {

    private Uri mHostAndPathUri;

    public HostAndPathUriMatcher(@NonNull Uri expectedUri) {
        checkNotNull(expectedUri);
        mHostAndPathUri = expectedUri;
    }

    @Override
    public boolean matches(Uri uri) {
        return mHostAndPathUri.getHost().equals(uri.getHost()) && mHostAndPathUri.getPath().equals(uri.getPath())
                && mHostAndPathUri.getScheme().equals(uri.getScheme());
    }
}
