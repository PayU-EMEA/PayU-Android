package com.payu.android.front.sdk.payment_library_core_android.styles;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

import com.payu.android.front.sdk.payment_library_core_android.styles.model.ButtonStyleInfo;

public class ButtonStyle {
    @ColorInt
    private final int backgroundColor;
    @ColorInt
    private int disabledBackgroundColor;
    private boolean withDisabledState;

    public ButtonStyle(@NonNull ButtonStyleInfo buttonStyleInfo) {
        this(buttonStyleInfo, false);
    }

    public ButtonStyle(@NonNull ButtonStyleInfo buttonStyleInfo, boolean withDisabledState) {
        this.backgroundColor = buttonStyleInfo.getBackgroundColor();
        this.disabledBackgroundColor = buttonStyleInfo.getDisabledBackgroundColor();
        this.withDisabledState = withDisabledState;
    }

    public void applyTo(@NonNull View button) {
        Drawable background = button.getBackground();
        Drawable wrapped = DrawableCompat.wrap(background);
        if (withDisabledState) {
            DrawableCompat.setTintList(wrapped.mutate(), getColorStateList(backgroundColor, disabledBackgroundColor));
        } else {
            DrawableCompat.setTint(wrapped.mutate(), backgroundColor);
        }
    }

    private static ColorStateList getColorStateList(int enabledColor, int disabledColor) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}  // disabled
        };

        int[] colors = new int[] {enabledColor, disabledColor};

        return new ColorStateList(states, colors);
    }

}
