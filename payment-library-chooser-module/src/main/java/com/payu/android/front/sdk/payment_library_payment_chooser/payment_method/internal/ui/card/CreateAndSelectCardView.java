package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenType;

public interface CreateAndSelectCardView {
    void closeWithSuccess();

    void addCard(@NonNull String senderId, @NonNull TokenType type);

    void showError(@NonNull String error);

    void hideSaveAndUse();

    void showSaveAndUse();

    void showLoading();

    void hideLoading();

    void setCardNumber(@NonNull String cardNumber);

    void setExpirationDate(int month, int year);
}
