package com.payu.android.front.sdk.payment_add_card_module.validation.cvv;


import com.payu.android.front.sdk.payment_add_card_module.validation.StringValidator;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;

public interface CvvValidator extends StringValidator {

    // Marker interface
    String INVALID_CVV_ERROR = TranslationFactory.getInstance().translate(TranslationKey.INVALID_CVV_ERROR);
}