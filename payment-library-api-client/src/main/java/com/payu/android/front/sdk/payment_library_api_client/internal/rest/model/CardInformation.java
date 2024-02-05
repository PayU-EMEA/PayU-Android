package com.payu.android.front.sdk.payment_library_api_client.internal.rest.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CardInformation {
    @SerializedName("card")
    private final Card card;
    @SerializedName("type")
    private final String cardType;
    @SerializedName("agreement")
    private final boolean agreement;

    public CardInformation(@NonNull Card card, @NonNull String cardType, boolean agreement) {
        this.card = card;
        this.cardType = cardType;
        this.agreement = agreement;
    }

    @NonNull
    public Card getCard() {
        return card;
    }

    public boolean isAgreement() {
        return agreement;
    }

    @NonNull
    public String getCardType() {
        return cardType;
    }
}
