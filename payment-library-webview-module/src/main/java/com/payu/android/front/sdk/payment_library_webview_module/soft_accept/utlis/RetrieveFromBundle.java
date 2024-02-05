package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.utlis;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RetrieveFromBundle {
    private RetrieveFromBundle() {
    }

    public static Long getLongFromBundle(@Nullable Bundle savedInstanceState, @Nullable Bundle args, @NonNull String key) {
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            return savedInstanceState.getLong(key);
        } else if (args != null && args.containsKey(key)) {
            return args.getLong(key);
        }
        return 0L;
    }

    public static Boolean getBooleanFromBundle(@Nullable Bundle savedInstanceState, @Nullable Bundle args, @NonNull String key) {
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            return savedInstanceState.getBoolean(key);
        } else if (args != null && args.containsKey(key)) {
            return args.getBoolean(key);
        }
        return false;
    }

    public static <T extends Parcelable> T getParcelableFromBundle(@Nullable Bundle savedInstanceState, @Nullable Bundle args, @NonNull String key) {
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            return savedInstanceState.getParcelable(key);
        } else if (args != null && args.containsKey(key)) {
            return args.getParcelable(key);
        }
        return null;
    }

}
