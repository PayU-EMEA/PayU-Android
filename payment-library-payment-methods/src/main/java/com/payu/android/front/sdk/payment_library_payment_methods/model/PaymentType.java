package com.payu.android.front.sdk.payment_library_payment_methods.model;

public enum PaymentType {
    /**
     * Pay By Link Payments
     */
    PBL,
    /**
     * Default Token-Card Payments
     */
    CARD,
    /**
     * Google Pay Payment (it is saved in PayU backend as PBL)
     */
    GOOGLE_PAY,
    /**
     * Pay By External is a strong authorization  handled in WebView container
     */
    PEX,
    /**
     * BLIK 2.0 Payments
     */
    BLIK_TOKENS,
    /**
     * Dummy-local BLIK created in SDK when user can pay via BLIK but does not payed before using this payment method
     */
    BLIK_GENERIC,
    /**
     * BLIK type when  used had more than one BLIK saved and PayU environment does not know which one should be used
     */
    BLIK_AMBIGUITY
}
