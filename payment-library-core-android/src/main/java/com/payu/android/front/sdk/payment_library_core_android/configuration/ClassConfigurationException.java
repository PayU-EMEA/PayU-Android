package com.payu.android.front.sdk.payment_library_core_android.configuration;

public class ClassConfigurationException extends RuntimeException {
    public ClassConfigurationException(String message) {
        super(message);
    }

    public ClassConfigurationException(String message, Exception e) {
        super(message, e);
    }
}
