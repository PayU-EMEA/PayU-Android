package com.payu.android.front.sdk.payment_add_card_module.view;

public interface CardDate extends ValidableView {
    void setExpirationDate(int monthOfYear, int year);

    void setErrorState(boolean isError);

    void setDateError(String error);

    String getDate();
}