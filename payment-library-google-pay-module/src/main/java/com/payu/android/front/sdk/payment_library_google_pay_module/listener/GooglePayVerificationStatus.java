package com.payu.android.front.sdk.payment_library_google_pay_module.listener;

/**
 * Enum with Google Pay verification statuses. Only {@linkplain #SUCCESS} should enable Google Pay payment
 */
public enum GooglePayVerificationStatus {
    /**
     * Verification of Google Pay availability was successful. GooglePay can be used as Payment method
     */
    SUCCESS,

    /**
     * Android version is to low. Google Pay is available from Android 4.4+
     */
    ERROR_API_VERSION,

    /**
     * Google Play services version is to low. User need to upgrade it before continuing
     */
    ERROR_GOOGLE_PLAY_SERVICES_VERSION,

    /**
     * Google Play services are not available. User need to install them before continuing
     */
    ERROR_GOOGLE_PLAY_SERVICES_UNAVAILABLE,

    /**
     * Unknown error is preventing user from making Google Pay payment
     */
    ERROR_UNKNOWN
}
