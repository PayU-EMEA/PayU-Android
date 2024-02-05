package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class MobileAppMatcher implements UriMatcher{
    private static final String REGEX_OPEN_MOBILE_APP = ".+(://).+";

    private boolean startsWithAppPrefix(@NonNull String url) {
        return startsWithIntent(url) || (customSchemeForOpeningApp(url) && !startWithHttp(url));
    }

    private boolean startsWithIntent(@NonNull String url) {
        return url.startsWith("intent://");
    }

    private boolean customSchemeForOpeningApp(@NonNull String url) {
        return Pattern.compile(REGEX_OPEN_MOBILE_APP).matcher(url).matches();
    }

    private boolean startWithHttp(@NonNull String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    @Override
    public boolean matches(Uri uri) {
        return startsWithAppPrefix(uri.toString());
    }
}
