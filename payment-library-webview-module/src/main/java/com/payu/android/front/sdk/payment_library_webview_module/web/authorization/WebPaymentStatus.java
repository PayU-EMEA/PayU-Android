package com.payu.android.front.sdk.payment_library_webview_module.web.authorization;


public enum WebPaymentStatus {
    /**
     * Successful transaction
     */
    SUCCESS,
    /**
     * Transaction failed (e.g. timeout)
     */
    PAYMENT_ERROR,
    /**
     * Transaction failed  - SSL Validation failed for given url. Please contact provider of mentioned web page
     */
    SSL_VALIDATION_ERROR,
    /**
     * Transaction cancelled by user
     */
    CANCEL_PAYMENT,
    /**
     * Transaction needs to be confirmed by CVV code
     */
    WARNING_CONTINUE_CVV,
    /**
     * Transaction will be handled in mobile bank app
     */
    WARNING_CONTINUE_REDIRECT_TO_MOBILE_APP
}
