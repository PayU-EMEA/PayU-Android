package com.payu.android.front.sdk.payment_library_api_client.internal.rest.model;

import com.google.gson.annotations.SerializedName;

public enum OpenPayuStatusCode {
    @SerializedName("SUCCESS")
    SUCCESS,
    @SerializedName("WARNING")
    WARNING,
    @SerializedName("DATA_NOT_FOUND")
    DATA_NOT_FOUND,
    @SerializedName("SERVICE_NOT_AVAILABLE")
    SERVICE_NOT_AVAILABLE,
    @SerializedName("ERROR_INTERNAL")
    ERROR_INTERNAL,
    @SerializedName("ERROR_VALUE_MISSING")
    ERROR_VALUE_MISSING,
    @SerializedName("ERROR_VALUE_INVALID")
    ERROR_VALUE_INVALID,
    @SerializedName("ERROR_SYNTAX")
    ERROR_SYNTAX,
    @SerializedName("UNKNOWN_MERCHANT_POS")
    ERROR_UNKNOWN_MERCHANT_POS,
    @SerializedName("ERROR_USER_NOT_UNIQUE")
    ERROR_USER_NOT_UNIQUE,
    @SerializedName("SIGNATURE_INVALID")
    SIGNATURE_INVALID,
    @SerializedName("BUSINESS_ERROR")
    BUSINESS_ERROR,
    @SerializedName("GENERAL_ERROR")
    GENERAL_ERROR,
    @SerializedName("TIMEOUT")
    TIMEOUT,
    @SerializedName("UNAUTHORIZED_REQUEST")
    UNAUTHORIZED_REQUEST;

    public boolean isSuccess() {
        return (this == SUCCESS);
    }
}
