package com.payu.android.front.sdk.payment_library_core_android.styles.model;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class ButtonStyleInfo {
    private TextStyleInfo textStyleInfo;
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int disabledBackgroundColor;

    public ButtonStyleInfo(@NonNull TextStyleInfo textStyleInfo, @ColorInt int backgroundColor, @ColorInt int disabledBackgroundColor) {
        this.textStyleInfo = textStyleInfo;
        this.backgroundColor = backgroundColor;
        this.disabledBackgroundColor = disabledBackgroundColor;
    }

    public TextStyleInfo getTextStyleInfo() {
        return textStyleInfo;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getDisabledBackgroundColor() {
        return disabledBackgroundColor;
    }
}
