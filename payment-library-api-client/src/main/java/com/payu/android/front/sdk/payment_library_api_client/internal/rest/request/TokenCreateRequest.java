package com.payu.android.front.sdk.payment_library_api_client.internal.rest.request;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;

public class TokenCreateRequest {

    @SerializedName("posId")
    private final String posId;
    @SerializedName("type")
    private final TokenType type;
    @SerializedName("card")
    private final Card card;

    public TokenCreateRequest(@NonNull String posId, @NonNull TokenType type, @NonNull Card card) {
        this.posId = posId;
        this.type = type;
        this.card = card;
    }

    @NonNull
    public String getPosId() {
        return posId;
    }

    @NonNull
    public TokenType getType() {
        return type;
    }

    @NonNull
    public Card getCard() {
        return card;
    }
}
