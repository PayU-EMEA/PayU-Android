package com.payu.android.front.sdk.payment_library_core_android.util;

import android.text.InputFilter;
import android.widget.EditText;

import java.util.Arrays;

public final class MaxLengthSetter {

    private MaxLengthSetter() {
        // utility
    }

    public static void setMaxLength(EditText editText, int max) {
        InputFilter[] baseFilters = editText.getFilters();
        InputFilter[] newFilters = Arrays.copyOf(baseFilters, baseFilters.length + 1);
        newFilters[baseFilters.length] = new InputFilter.LengthFilter(max);
        editText.setFilters(newFilters);
    }
}
