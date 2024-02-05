package com.payu.android.front.sdk.payment_add_card_module.presenter;

public interface DatePresenter {

    String getMonth();

    String getYear();

    boolean validate();

    void setExpirationDate(int monthOfYear, int year);
}
