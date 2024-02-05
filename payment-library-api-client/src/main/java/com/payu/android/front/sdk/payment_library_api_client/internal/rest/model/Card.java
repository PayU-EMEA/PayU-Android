package com.payu.android.front.sdk.payment_library_api_client.internal.rest.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Card {
    @SerializedName("number")
    private final String cardNumber;
    @SerializedName("cvv")
    private final String cardCvv;
    @SerializedName("expirationMonth")
    private final String cardExpirationMonth;
    @SerializedName("expirationYear")
    private final String cardExpirationYear;

    public Card(@NonNull String cardNumber, @NonNull String cardCvv, @NonNull String cardExpirationMonth, @NonNull String cardExpirationYear) {
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpirationMonth = cardExpirationMonth;
        this.cardExpirationYear = cardExpirationYear;
    }

    @NonNull
    public String getCardNumber() {
        return cardNumber;
    }

    @NonNull
    public String getCardCvv() {
        return cardCvv;
    }

    @NonNull
    public String getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    @NonNull
    public String getCardExpirationYear() {
        return cardExpirationYear;
    }
}
