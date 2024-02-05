package com.payu.android.front.sdk.payment_add_card_module.validation.cvv;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class MaestroCvvValidator implements CvvValidator {

    @Override
    public Optional<String> getErrorString(String text) {
        String trimmedText = Strings.nullToEmpty(text).trim();
        return validateCvv(trimmedText) ? Optional.<String>absent() : Optional.of(INVALID_CVV_ERROR);
    }

    private boolean validateCvv(String text) {
        return text.matches("(^\\d{3}$)|(^$)");
    }
}
