package com.payu.android.front.sdk.payment_library_core_android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

/**
 * Utils for opening PDF files in external Application
 */
public class PDFUtils {
    private PDFUtils() {
    }

    public static void openPdfFileInExternalSourceWithoutType(@NonNull Context context, @NonNull String url) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static void openPdfFileInExternalSourceWithType(@NonNull Context context, @NonNull String url) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "text/html");
        context.startActivity(intent);
    }
}
