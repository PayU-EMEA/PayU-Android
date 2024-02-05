package com.payu.android.front.sdk.payment_library_google_pay_module.model;

public class GooglePayTokenResponseException extends Exception {
    public GooglePayTokenResponseException(Throwable cause) {
        super("Problem with parsing Google Pay Card Token", cause);
    }
}
