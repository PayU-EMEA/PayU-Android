package com.payu.android.front.sdk.demo.config;

import android.content.Context;

import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActions;

public class DynamicCardPayment implements DynamicCardActions {
    public DynamicCardPayment(Context context) {
    }

    private static boolean addCard = true;
    private static boolean saveAndUse = false;
    private static boolean scanCardOption = false;

    @Override
    public boolean addCardFlow() {
        //addCard = !addCard;
        return addCard;
    }

    @Override
    public boolean saveAndUseOption() {
        //  saveAndUse = !saveAndUse;
        return saveAndUse;
    }

    @Override
    public boolean scanCardOption() {
        // scanCardOption = !scanCardOption;
        return scanCardOption;
    }
}
