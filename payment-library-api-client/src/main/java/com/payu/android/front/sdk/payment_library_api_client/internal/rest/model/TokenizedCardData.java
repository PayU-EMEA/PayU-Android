package com.payu.android.front.sdk.payment_library_api_client.internal.rest.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TokenizedCardData {
    @SerializedName("token")
    private final String cardToken;
    @SerializedName("mask")
    private final String cardMask;
    @SerializedName("type")
    private final String cardType;

    public TokenizedCardData(@NonNull String cardToken, @NonNull String cardMask, @NonNull String cardType) {
        this.cardToken = cardToken;
        this.cardMask = cardMask;
        this.cardType = cardType;
    }

    @NonNull
    public String getCardToken() {
        return cardToken;
    }

    @NonNull
    public String getCardMask() {
        return cardMask;
    }

    @NonNull
    public String getCardType() {
        return cardType;
    }
}
