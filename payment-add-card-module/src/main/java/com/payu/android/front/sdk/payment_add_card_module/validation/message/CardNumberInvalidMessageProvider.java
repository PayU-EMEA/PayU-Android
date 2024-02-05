package com.payu.android.front.sdk.payment_add_card_module.validation.message;


import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public class CardNumberInvalidMessageProvider implements ValidationErrorProvider {

    private final Translation mTranslation = TranslationFactory.getInstance();

    @Override
    public String getEmptyValueString() {
        return mTranslation.translate(TranslationKey.CARD_VALIDATION_EMPTY);
    }

    @Override
    public String getInvalidValueString() {
        return mTranslation.translate(TranslationKey.CARD_VALIDATION_NUMBER_INCORRECT);
    }
}
