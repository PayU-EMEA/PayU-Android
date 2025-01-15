package com.payu.android.front.sdk.payment_library_core.translation.dictionary;


import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

import java.util.HashMap;
import java.util.Map;

abstract class StringMapTranslation implements Translation {

    private final Map<TranslationKey, String> mTranslations = new HashMap<TranslationKey, String>();

    @Override
    public String translate(TranslationKey key) {
        return mTranslations.get(key);
    }

    @Override
    public String translate(TranslationKey key, Object... parameters) {
        return String.format(translate(key), parameters);
    }

    void add(TranslationKey key, String value) {
        mTranslations.put(key, value);
    }
}
