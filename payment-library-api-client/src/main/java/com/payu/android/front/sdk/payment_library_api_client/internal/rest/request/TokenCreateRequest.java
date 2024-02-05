package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.CardInformation;

public class TokenCreateRequest {

    @SerializedName("sender")
    private final String senderId;
    @SerializedName("request")
    private final String requestType;
    @SerializedName("data")
    private final CardInformation requestData;

    public TokenCreateRequest(@NonNull String senderId, @NonNull String requestType, @NonNull CardInformation requestData) {
        this.senderId = senderId;
        this.requestType = requestType;
        this.requestData = requestData;
    }

    @NonNull
    public String getSenderId() {
        return senderId;
    }

    @NonNull
    public String getRequestType() {
        return requestType;
    }

    @NonNull
    public CardInformation getRequestData() {
        return requestData;
    }
}
