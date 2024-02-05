package com.payu.android.front.sdk.payment_add_card_module.issuer;

import java.util.regex.Pattern;

class VisaCardTypeResolver extends CardTypeResolver {

    private static final String CARD_PATTERN = "^4";
    private Pattern mCompiledPattern;

    VisaCardTypeResolver() {
        mCompiledPattern = Pattern.compile(CARD_PATTERN);
    }

    @Override
    public CardIssuer evaluateCardIssuer(String cardNumber) {
        return isMatchingPattern(cardNumber) ? CardIssuer.VISA : askSuccessorOrReturnUnknown(cardNumber);
    }

    private boolean isMatchingPattern(String cardNumber) {
        return mCompiledPattern.matcher(cardNumber).find();
    }
}
