package com.payu.android.front.sdk.payment_add_card_module.status;

public class CvvResponseDetails {
    private CvvPaymentStatus paymentStatus;

    public CvvResponseDetails(CvvPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public CvvPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
