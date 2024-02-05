package com.payu.android.front.sdk.payment_library_api_client.internal.rest.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class RequestStatus {
    @SerializedName("statusCode")
    @NonNull
    private final OpenPayuStatusCode openPayuStatusCode;
    @SerializedName("codeLiteral")
    @NonNull
    private final String statusLiteral;
    @SerializedName("code")
    private final int statusCodeNumber;

    public RequestStatus(@NonNull OpenPayuStatusCode openPayuStatusCode, @NonNull String statusLiteral, int statusCodeNumber) {
        this.openPayuStatusCode = openPayuStatusCode;
        this.statusLiteral = statusLiteral;
        this.statusCodeNumber = statusCodeNumber;
    }

    @NonNull
    public OpenPayuStatusCode getOpenPayuStatusCode() {
        return openPayuStatusCode;
    }

    @NonNull
    public String getStatusLiteral() {
        return statusLiteral;
    }

    public int getStatusCodeNumber() {
        return statusCodeNumber;
    }
}

