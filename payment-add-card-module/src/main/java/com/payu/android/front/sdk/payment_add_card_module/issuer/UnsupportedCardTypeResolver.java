package com.payu.android.front.sdk.payment_add_card_module.issuer;

class UnsupportedCardTypeResolver extends CardTypeResolver {

    private static final int MINIMUM_LENGTH = 13;
    private static final int MAXIMUM_LENGTH = 16;

    @Override
    public CardIssuer evaluateCardIssuer(String cardNumber) {

        if (hasWrongLength(cardNumber)) {
            return CardIssuer.UNKNOWN;
        }

        return askSuccessorOrReturnUnknown(cardNumber);
    }

    private boolean hasWrongLength(String cardNumber) {

        if (cardNumber == null) {
            return true;
        }

        int cardNumberLength = cardNumber.length();
        return cardNumberLength > MAXIMUM_LENGTH || cardNumberLength < MINIMUM_LENGTH;
    }
}
