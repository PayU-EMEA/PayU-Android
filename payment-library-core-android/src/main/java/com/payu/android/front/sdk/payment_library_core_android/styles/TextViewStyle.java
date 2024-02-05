package com.payu.android.front.sdk.payment_library_core_android.styles;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;

public class TextViewStyle {
    private TextStyleInfo textStyleInfo;
    private FontProvider fontProvider;

    public TextViewStyle(TextStyleInfo textStyleInfo, FontProvider fontProvider) {
        this.textStyleInfo = textStyleInfo;
        this.fontProvider = fontProvider;
    }

    public void applyTo(TextView textView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textStyleInfo.getTextSize());
        textView.setTextColor(textStyleInfo.getTextColor());
        applyTypeface(textView);
        textView.setPadding((int) textStyleInfo.getPaddingLeft(), (int) textStyleInfo.getPaddingTop(), (int) textStyleInfo.getPaddingRight(),
                            (int) textStyleInfo.getPaddingBottom());
    }

    private void applyTypeface(TextView textView) {
        Typeface typeface = fontProvider.loadTypeface(textStyleInfo.getFont(), textStyleInfo.getFontPath());
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
    }
}
