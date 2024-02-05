package com.payu.android.front.sdk.payment_library_core.util;

import javax.annotation.Nullable;

public class StringUtils {
    public static String createCardExpirationString(String month, String year) {
        return String.format("%s / %s", month, year);
    }

    public static boolean notEmpty(@Nullable String string) {
        return string != null && !string.isEmpty();
    }
}
