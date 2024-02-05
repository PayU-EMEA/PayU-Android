package com.payu.android.front.sdk.payment_library_core_android.styles.model;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;

public class LibraryStyleInfo {
    @ColorInt
    private final int backgroundColor;
    @ColorInt
    private final int primaryColor;
    @ColorInt
    private final int accentColor;
    @ColorInt
    private final int textColor;
    @ColorInt
    private final int disabledColor;
    @ColorInt
    private final int separatorColor;
    @ColorInt
    private final int toolbarColor;

    @Dimension
    private final float windowContentPadding;
    private final ButtonStyleInfo textStyleButtonBasic;
    private final ButtonStyleInfo textStyleButtonPrimary;
    private final TextStyleInfo textStyleTitle;
    private final TextStyleInfo textStyleHeader;
    private final TextStyleInfo textStyleHeadline;
    private final TextStyleInfo textStyleText;
    private final TextStyleInfo textStyleInput;
    private final TextStyleInfo textStyleDescription;

    LibraryStyleInfo(
            int backgroundColor, int toolbarColor, int primaryColor, int accentColor, int textColor, int disabledColor, int separatorColor, float windowContentPadding,
            ButtonStyleInfo textStyleButtonBasic, ButtonStyleInfo textStyleButtonPrimary, TextStyleInfo textStyleTitle, TextStyleInfo textStyleHeader,
            TextStyleInfo textStyleHeadline, TextStyleInfo textStyleText, TextStyleInfo textStyleInput, TextStyleInfo textStyleDescription) {
        this.backgroundColor = backgroundColor;
        this.toolbarColor = toolbarColor;
        this.primaryColor = primaryColor;
        this.accentColor = accentColor;
        this.textColor = textColor;
        this.disabledColor = disabledColor;
        this.separatorColor = separatorColor;
        this.windowContentPadding = windowContentPadding;
        this.textStyleButtonBasic = textStyleButtonBasic;
        this.textStyleButtonPrimary = textStyleButtonPrimary;
        this.textStyleTitle = textStyleTitle;
        this.textStyleHeader = textStyleHeader;
        this.textStyleHeadline = textStyleHeadline;
        this.textStyleText = textStyleText;
        this.textStyleInput = textStyleInput;
        this.textStyleDescription = textStyleDescription;
    }

    public TextStyleInfo getTextStyleHeadline() {
        return textStyleHeadline;
    }

    @ColorInt
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @ColorInt
    public int getPrimaryColor() {
        return primaryColor;
    }

    @ColorInt
    public int getAccentColor() {
        return accentColor;
    }

    @ColorInt
    public int getSeparatorColor() {
        return separatorColor;
    }

    @ColorInt
    public int getTextColor() {
        return textColor;
    }

    @ColorInt
    public int getDisabledColor() {
        return disabledColor;
    }

    @ColorInt
    public int getToolbarColor() {
        return toolbarColor;
    }

    public ButtonStyleInfo getTextStyleButtonBasic() {
        return textStyleButtonBasic;
    }

    public ButtonStyleInfo getTextStyleButtonPrimary() {
        return textStyleButtonPrimary;
    }

    public TextStyleInfo getTextStyleTitle() {
        return textStyleTitle;
    }

    public TextStyleInfo getTextStyleHeader() {
        return textStyleHeader;
    }

    public TextStyleInfo getTextStyleText() {
        return textStyleText;
    }

    public TextStyleInfo getTextStyleInput() {
        return textStyleInput;
    }

    public TextStyleInfo getTextStyleDescription() {
        return textStyleDescription;
    }

    @Override
    public String toString() {
        return "LibraryStyleInfo{" + "backgroundColor=" + backgroundColor + ", primaryColor=" + primaryColor + ", accentColor=" + accentColor + ", " +
                "" + "textColor=" + textColor + ", separatorColor=" + separatorColor + ", toolbarColor=" + toolbarColor + ", windowContentPadding="
                + windowContentPadding + ", textStyleButtonBasic=" + textStyleButtonBasic + ", textStyleButtonPrimary=" + textStyleButtonPrimary +
                "," + " textStyleTitle=" + textStyleTitle + ", textStyleHeader=" + textStyleHeader + ", textStyleHeadline=" + textStyleHeadline +
                ", " + "textStyleText=" + textStyleText + ", textStyleInput=" + textStyleInput + ", textStyleDescription=" + textStyleDescription +
                '}';
    }

    public float getWindowContentPadding() {
        return windowContentPadding;
    }

    public static class Builder {
        private int backgroundColor;
        private int primaryColor;
        private int accentColor;
        private int textColor;
        private int disabledColor;
        private int separatorColor;
        private int toolbarColor;
        private float windowContentPadding;

        private ButtonStyleInfo textStyleButtonBasic;
        private ButtonStyleInfo textStyleButtonPrimary;
        private TextStyleInfo textStyleTitle;
        private TextStyleInfo textStyleHeader;
        private TextStyleInfo textStyleHeadline;
        private TextStyleInfo textStyleText;
        private TextStyleInfo textStyleInput;
        private TextStyleInfo textStyleDescription;

        public Builder withBackgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder withPrimaryColor(int color) {
            this.primaryColor = color;
            return this;
        }

        public Builder withAccentColor(int color) {
            this.accentColor = color;
            return this;
        }

        public Builder withSeparatorColor(int color) {
            this.separatorColor = color;
            return this;
        }

        public Builder withTextColor(int color) {
            this.textColor = color;
            return this;
        }

        public Builder withDisabledColor(int color) {
            this.disabledColor = color;
            return this;
        }

        public Builder withTextStyleButtonBasic(ButtonStyleInfo style) {
            this.textStyleButtonBasic = style;
            return this;
        }

        public Builder withTextStyleButtonPrimary(ButtonStyleInfo style) {
            this.textStyleButtonPrimary = style;
            return this;
        }

        public Builder withTextStyleTitle(TextStyleInfo style) {
            this.textStyleTitle = style;
            return this;
        }

        public Builder withTextStyleHeader(TextStyleInfo style) {
            this.textStyleHeader = style;
            return this;
        }

        public Builder withTextStyleHeadline(TextStyleInfo style) {
            this.textStyleHeadline = style;
            return this;
        }

        public Builder withTextStyleText(TextStyleInfo style) {
            this.textStyleText = style;
            return this;
        }

        public Builder withTextStyleInput(TextStyleInfo style) {
            this.textStyleInput = style;
            return this;
        }

        public Builder withTextStyleDescription(TextStyleInfo style) {
            this.textStyleDescription = style;
            return this;
        }

        public Builder withWindowContentPadding(float windowContentPadding) {
            this.windowContentPadding = windowContentPadding;
            return this;
        }

        public Builder withToolbarColor(int toolbarColor) {
            this.toolbarColor = toolbarColor;
            return this;
        }

        public LibraryStyleInfo build() {
            return new LibraryStyleInfo(backgroundColor, toolbarColor, primaryColor, accentColor, textColor, disabledColor, separatorColor, windowContentPadding,
                    textStyleButtonBasic, textStyleButtonPrimary, textStyleTitle, textStyleHeader, textStyleHeadline, textStyleText, textStyleInput,
                    textStyleDescription);
        }
    }
}
