package com.payu.android.front.sdk.payment_add_card_module.service;

/**
 * Local Error when card is not selected properly
 */
public class CardNotSelectedError extends Error {
    private static final String CARD_ERROR_NAME = "COMMUNICATION_GENERAL";
    private static final int CARD_ERROR_CODE = -1000;

    public CardNotSelectedError() {
        super(CARD_ERROR_CODE, CARD_ERROR_NAME);
    }
}
