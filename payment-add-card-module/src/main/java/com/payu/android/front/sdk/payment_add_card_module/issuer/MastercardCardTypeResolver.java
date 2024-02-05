package com.payu.android.front.sdk.payment_add_card_module.issuer;

import java.util.regex.Pattern;

class MastercardCardTypeResolver extends CardTypeResolver {

    private static final String CARD_PATTERN ="^(5[1-5]|2[2-7])";
    private Pattern mCompiledPattern;

    MastercardCardTypeResolver() {
        mCompiledPattern = Pattern.compile(CARD_PATTERN);
    }

    @Override
    public CardIssuer evaluateCardIssuer(String cardNumber) {
        return isMatchingPattern(cardNumber) ? CardIssuer.MASTER_CARD : askSuccessorOrReturnUnknown(cardNumber);
    }

    private boolean isMatchingPattern(String cardNumber) {
        return mCompiledPattern.matcher(cardNumber).find();
    }
}
