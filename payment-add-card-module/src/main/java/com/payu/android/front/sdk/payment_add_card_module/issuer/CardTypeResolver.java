package com.payu.android.front.sdk.payment_add_card_module.issuer;

public abstract class CardTypeResolver implements ChainCell<CardTypeResolver> {

    private CardTypeResolver mNext;

    public abstract CardIssuer evaluateCardIssuer(String cardNumber);

    @Override
    public CardTypeResolver getNext() {
        return mNext;
    }

    @Override
    public void setNext(CardTypeResolver next) {
        mNext = next;
    }

    protected CardIssuer askSuccessorOrReturnUnknown(String cardNumber) {
        return (getNext() == null) ? CardIssuer.UNKNOWN : getNext().evaluateCardIssuer(cardNumber);
    }
}
