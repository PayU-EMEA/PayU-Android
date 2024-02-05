package com.payu.android.front.sdk.payment_library_google_pay_module.model;

public class PaymentDataRequestException extends Exception {
    public PaymentDataRequestException() {
        super("PaymentDataRequest not parsed correctly!");
    }
}
