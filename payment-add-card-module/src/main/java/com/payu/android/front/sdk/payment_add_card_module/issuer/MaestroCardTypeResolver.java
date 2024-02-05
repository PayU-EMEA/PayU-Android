package com.payu.android.front.sdk.payment_add_card_module.issuer;

import java.util.regex.Pattern;

class MaestroCardTypeResolver extends CardTypeResolver {

    private static final String CARD_PATTERN ="^(06|5[0678]|6)";
    private Pattern mCompiledPattern;

    MaestroCardTypeResolver() {
        mCompiledPattern = Pattern.compile(CARD_PATTERN);
    }

    @Override
    public CardIssuer evaluateCardIssuer(String cardNumber) {
        return (isMatchingPattern(cardNumber)) ? CardIssuer.MAESTRO
                : askSuccessorOrReturnUnknown(cardNumber);
    }

    private boolean isMatchingPattern(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }
        return mCompiledPattern.matcher(cardNumber).find();
    }
}
