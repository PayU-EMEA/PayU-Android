package com.payu.android.front.sdk.payment_add_card_module.service;


/**
 * Wrapper for communication errors with PayU server
 */
public class Error {
    private static final String COMMUNICATION_GENERAL = "COMMUNICATION_GENERAL";
    private String errorLiteral;
    private int errorCode;

    public Error(int errorCode, String errorLiteral) {
        this.errorCode = errorCode;
        this.errorLiteral = errorLiteral;
    }

    /**
     * For nonstandard error codes please check official documentation
     *
     * @param errorCode
     * @see <a href="http://developers.payu.com/en/restapi.html">
     * documentation</a>
     */
    public Error(int errorCode) {
        this(errorCode, COMMUNICATION_GENERAL);
    }

    /**
     * For nonstandard error codes
     *
     * @return {@linkplain Error#COMMUNICATION_GENERAL} in case of network issue, in other case it will return PayU literal error
     * @see <a href="http://developers.payu.com/en/restapi.html">
     * documentation</a>
     */
    public String getErrorLiteral() {
        return errorLiteral;
    }

    public int getErrorCode() {
        return errorCode;
    }
}