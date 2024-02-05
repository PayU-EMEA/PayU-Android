package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ScannedCard {

    @NonNull
    private final String cardNumber;
    @Nullable
    private final String expirationDate;

    public ScannedCard(@NonNull String cardNumber, @Nullable String expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    @NonNull
    public String getCardNumber() {
        return cardNumber;
    }

    @Nullable
    public String getExpirationDate() {
        return expirationDate;
    }
}
