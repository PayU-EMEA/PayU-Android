package com.payu.android.front.sdk.payment_add_card_module.validation;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.validation.message.ValidationErrorProvider;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.payu.android.front.sdk.payment_library_core_android.util.CollectionUtils.isOneOf;
import static java.util.Arrays.asList;


public class CardNumberStringValidator implements StringValidator {

    private final ValidationErrorProvider errorMessageProvider;
    private CardIssuerProvider cardIssuerProvider;
    private LuhnValidator luhnValidator;

    public CardNumberStringValidator(ValidationErrorProvider errorMessageProvider, CardIssuerProvider cardIssuerProvider,
                                     LuhnValidator luhnValidator) {
        this.errorMessageProvider = errorMessageProvider;
        this.cardIssuerProvider = cardIssuerProvider;
        this.luhnValidator = luhnValidator;
    }

    @Override
    public Optional<String> getErrorString(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return fromNullable(errorMessageProvider.getEmptyValueString());
        } else if (!isCardNumberValid(text)) {
            return fromNullable(errorMessageProvider.getInvalidValueString());
        } else {
            return absent();
        }
    }

    private boolean isCardNumberValid(String cardNumber) {

        if (!luhnValidator.isValid(cardIssuerProvider.dropDashAndWhitespaces(cardNumber))) {
            return false;
        }

        CardIssuer cardProvider = cardIssuerProvider.getCardProvider(cardNumber);
        return isOneOf(cardProvider, asList(CardIssuer.VISA, CardIssuer.MASTER_CARD, CardIssuer
                .MAESTRO));
    }
}
