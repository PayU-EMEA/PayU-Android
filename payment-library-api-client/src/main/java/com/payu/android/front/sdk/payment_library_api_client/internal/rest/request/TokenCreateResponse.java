package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.RequestStatus;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.TokenizedCardData;

public class TokenCreateResponse {
    @SerializedName("data")
    @Nullable
    private final TokenizedCardData tokenizedCardData;
    @SerializedName("status")
    @NonNull
    private final RequestStatus requestStatus;

    public TokenCreateResponse(@Nullable TokenizedCardData tokenizedCardData, @NonNull RequestStatus requestStatus) {
        this.tokenizedCardData = tokenizedCardData;
        this.requestStatus = requestStatus;
    }

    @Nullable
    public TokenizedCardData getTokenizedCardData() {
        return tokenizedCardData;
    }

    @NonNull
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }
}
