package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TokenCreateResponse {
    @SerializedName("value")
    @NonNull
    private final String value;
    @SerializedName("maskedCard")
    @NonNull
    private final String maskedCard;

    public TokenCreateResponse(@NonNull String value, @NonNull String maskedCard) {
        this.value = value;
        this.maskedCard = maskedCard;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    @NonNull
    public String getMaskedCard() {
        return maskedCard;
    }
}
