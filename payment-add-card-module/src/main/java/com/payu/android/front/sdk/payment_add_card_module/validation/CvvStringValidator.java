package com.payu.android.front.sdk.payment_add_card_module.validation;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.payu.android.front.sdk.payment_add_card_module.validation.message.ValidationErrorProvider;

public class CvvStringValidator implements StringValidator {

    private final ValidationErrorProvider errorMessageProvider;

    public CvvStringValidator(ValidationErrorProvider errorMessageProvider) {
        this.errorMessageProvider = errorMessageProvider;
    }

    @Override
    public Optional<String> getErrorString(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return fromNullable(errorMessageProvider.getEmptyValueString());
        } else if (!isCvvValid(text)) {
            return fromNullable(errorMessageProvider.getInvalidValueString());
        } else {
            return absent();
        }
    }

    private boolean isCvvValid(String cvv) {
        return cvv.matches("^\\d{3}$");
    }
}
