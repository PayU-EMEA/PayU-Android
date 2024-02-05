package com.payu.android.front.sdk.payment_library_core_android.styles;

import android.graphics.Typeface;
import androidx.annotation.NonNull;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;

public class EditTextStyle {
    private final TextStyleInfo styleInfo;
    private final FontProvider fontProvider;

    public EditTextStyle(@NonNull TextStyleInfo textStyleInfo, @NonNull FontProvider fontProvider) {
        this.styleInfo = textStyleInfo;
        this.fontProvider = fontProvider;
    }

    public void applyTo(EditText view) {
        //DON'T set padding on EditText, as it breaks the view.
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, styleInfo.getTextSize());
        view.setTextColor(styleInfo.getTextColor());

        applyTypeface(view);
    }

    private void applyTypeface(TextView textView) {
        Typeface typeface = fontProvider.loadTypeface(styleInfo.getFont(), styleInfo.getFontPath());
        if (typeface != null) {
            textView.setTypeface(typeface);

        }
    }
}
