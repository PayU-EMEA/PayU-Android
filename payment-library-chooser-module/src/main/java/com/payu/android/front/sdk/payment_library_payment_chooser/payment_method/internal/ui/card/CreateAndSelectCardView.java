package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card;

import androidx.annotation.NonNull;

public interface CreateAndSelectCardView {
    void closeWithSuccess();

    void addCardWithAgreement(@NonNull String senderId);

    void addCardWithoutAgreement(@NonNull String senderId);

    void showError(@NonNull String error);

    void hideSaveAndUse();

    void showSaveAndUse();

    void showLoading();

    void hideLoading();

    void setCardNumber(@NonNull String cardNumber);

    void setExpirationDate(int month, int year);
}
