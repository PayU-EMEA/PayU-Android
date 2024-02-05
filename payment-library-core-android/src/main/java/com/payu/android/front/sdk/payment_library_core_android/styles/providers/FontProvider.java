package com.payu.android.front.sdk.payment_library_core_android.styles.providers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class FontProvider {
    private Context context;

    public FontProvider(Context context) {
        this.context = context;
    }

    @Nullable
    public Typeface loadTypeface(int resId, @Nullable String fontPath) {
        Typeface typeface;
        try {
            typeface = ResourcesCompat.getFont(context, resId);

        } catch (Resources.NotFoundException exception) {
            typeface = null;
        }
        if (fontPath != null && !fontPath.isEmpty()) {
            typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
        }
        return typeface;
    }
}
