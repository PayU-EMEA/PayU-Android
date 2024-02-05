package com.payu.android.front.sdk.payment_library_core_android.configuration;

/**
 * Exception thrown when provided configuration is invalid
 *
 * @author PayU S.A.
 */
public class IllegalConfigurationException extends RuntimeException {

    public IllegalConfigurationException(String message) {
        super(message);
    }
}
