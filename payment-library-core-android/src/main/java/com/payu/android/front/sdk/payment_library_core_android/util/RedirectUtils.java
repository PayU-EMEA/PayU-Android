package com.payu.android.front.sdk.payment_library_core_android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

public class RedirectUtils {
    private RedirectUtils() {
    }

    public static void openPhone(@NonNull Context context, @NonNull String url) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void openMail(@NonNull Context context, @NonNull String url) throws ActivityNotFoundException{
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }
}
