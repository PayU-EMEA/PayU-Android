package com.payu.android.front.sdk.payment_library_core_android.styles.model;

import android.content.res.ColorStateList;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.FontRes;

public class TextStyleInfo {
    private final ColorStateList textColor;
    @Dimension
    private final float textSize;
    @Dimension
    private final float paddingBottom;
    @Dimension
    private final float paddingTop;
    @Dimension
    private final float paddingLeft;
    @Dimension
    private final float paddingRight;
    private final String fontPath;
    @FontRes
    private final int font;

    TextStyleInfo(
            ColorStateList textColor, float textSize, float paddingBottom, float paddingTop, float paddingLeft, float paddingRight, String fontPath, int font) {
        this.textColor = textColor;
        this.textSize = textSize;
        this.paddingBottom = paddingBottom;
        this.paddingTop = paddingTop;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.fontPath = fontPath;
        this.font = font;
    }

    public ColorStateList getTextColor() {
        return textColor;
    }

    @Dimension
    public float getTextSize() {
        return textSize;
    }

    @Dimension
    public float getPaddingBottom() {
        return paddingBottom;
    }

    @Dimension
    public float getPaddingTop() {
        return paddingTop;
    }

    @Dimension
    public float getPaddingLeft() {
        return paddingLeft;
    }

    @Dimension
    public float getPaddingRight() {
        return paddingRight;
    }

    public String getFontPath() {
        return fontPath;
    }

    @FontRes
    public int getFont() {
        return font;
    }

    @Override
    public String toString() {
        return "TextStyleInfo{" + "textColor=[enableColor=" + textColor.getColorForState(new int[]{android.R.attr.state_enabled}, -1) +
                " disableColor=" + textColor.getColorForState(new int[]{-android.R.attr.state_enabled}, -1) + "], textSize=" + textSize +
                ", paddingBottom=" + paddingBottom + ", paddingTop=" + paddingTop + ", paddingLeft=" + paddingLeft + ", paddingRight=" + paddingRight +
                ", fontPath='" + fontPath + '\'' + ", font=" + font + '}';
    }

    public static class Builder {
        private ColorStateList textColor;
        private float textSize;
        private float paddingBottom;
        private float paddingTop;
        private float paddingLeft;
        private float paddingRight;
        private String fontPath;
        private int font;

        public Builder withTextColor(ColorStateList textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder withTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder withPaddingBottom(float paddingBottom) {
            this.paddingBottom = paddingBottom;
            return this;
        }

        public Builder withPaddingTop(float paddingTop) {
            this.paddingTop = paddingTop;
            return this;
        }

        public Builder withPaddingLeft(float paddingLeft) {
            this.paddingLeft = paddingLeft;
            return this;
        }

        public Builder withPaddingRight(float paddingRight) {
            this.paddingRight = paddingRight;
            return this;
        }

        public Builder withFontPath(String fontPath) {
            this.fontPath = fontPath;
            return this;
        }

        public Builder withFont(int font) {
            this.font = font;
            return this;
        }

        public TextStyleInfo build() {
            return new TextStyleInfo(textColor, textSize, paddingBottom, paddingTop, paddingLeft, paddingRight, fontPath, font);
        }
    }
}
