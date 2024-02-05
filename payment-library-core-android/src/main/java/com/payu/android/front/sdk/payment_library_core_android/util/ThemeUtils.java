package com.payu.android.front.sdk.payment_library_core_android.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ThemeUtils {
    public static boolean isInNightMode(@NonNull Context context) {
        Resources resources = checkNotNull(context).getResources();
        int currentNightMode = resources.getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
