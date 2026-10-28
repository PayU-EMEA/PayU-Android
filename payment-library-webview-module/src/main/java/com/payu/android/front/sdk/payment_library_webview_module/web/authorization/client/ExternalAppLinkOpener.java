package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Opens http(s) urls in an installed application which registered them as a deep link / App Link,
 * instead of loading them in the WebView. Browsers are ignored.
 * Supported from API 30 (FLAG_ACTIVITY_REQUIRE_NON_BROWSER); below that urls are always loaded in the WebView.
 */
class ExternalAppLinkOpener {

    private static final String TAG = ExternalAppLinkOpener.class.getSimpleName();

    /**
     * @return true if an application (other than a browser) was started for the given url
     */
    boolean tryOpen(@NonNull Context context, @NonNull String url) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return false;
        }

        Uri uri = Uri.parse(url);
        if (!isWebScheme(uri.getScheme())) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, uri)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER);

        try {
            context.startActivity(intent);
            Log.v(TAG, "Url opened in external application: " + url);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    private static boolean isWebScheme(String scheme) {
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
}
