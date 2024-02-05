package com.payu.android.front.sdk.payment_add_card_module.status;

public enum CvvPaymentStatus {
    /**
     * Successful transaction
     */
    SUCCESS,
    /**
     * Transaction failed (e.g. timeout)
     */
    PAYMENT_ERROR,
    /**
     * Transaction cancelled by user
     */
    CANCEL_PAYMENT
}
