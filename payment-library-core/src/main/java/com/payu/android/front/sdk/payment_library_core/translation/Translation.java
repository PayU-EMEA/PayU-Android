package com.payu.android.front.sdk.payment_library_core.translation;


import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;

public interface Translation {
    Locale getLanguage();

    String translate(TranslationKey key);

    String translate(TranslationKey key, Object... parameters);
}
