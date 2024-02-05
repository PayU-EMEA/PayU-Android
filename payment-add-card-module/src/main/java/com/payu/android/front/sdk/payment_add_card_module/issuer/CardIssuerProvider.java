package com.payu.android.front.sdk.payment_add_card_module.issuer;

public class CardIssuerProvider {

    CardTypeResolver mCardTypeResolver;

    private CardIssuerProvider(CardTypeResolver cardTypeResolver) {
        mCardTypeResolver = cardTypeResolver;
    }

    public static CardIssuerProvider getInstance() {
        VisaCardTypeResolver visaCardTypeResolver = new VisaCardTypeResolver();
        MaestroCardTypeResolver maestroCardTypeResolver = new MaestroCardTypeResolver();
        MastercardCardTypeResolver mastercardCardTypeResolver = new MastercardCardTypeResolver();
        UnsupportedCardTypeResolver unsupportedCardTypeResolver = new UnsupportedCardTypeResolver();
        visaCardTypeResolver.setNext(mastercardCardTypeResolver);
        unsupportedCardTypeResolver.setNext(visaCardTypeResolver);
        maestroCardTypeResolver.setNext(unsupportedCardTypeResolver);
        return new CardIssuerProvider(maestroCardTypeResolver);
    }

    public String dropDashAndWhitespaces(String cardNumber) {
        return (cardNumber == null) ? null : cardNumber.replaceAll("\\s", "").replaceAll("-", "");
    }

    public CardIssuer getCardProvider(String cardNumber) {
        String cleanCardNumber = dropDashAndWhitespaces(cardNumber);
        return mCardTypeResolver.evaluateCardIssuer(cleanCardNumber);
    }
}
