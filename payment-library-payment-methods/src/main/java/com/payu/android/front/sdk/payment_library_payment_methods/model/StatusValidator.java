package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import java.util.List;

abstract class StatusValidator implements StringValidator {

    @Override
    public boolean validate(@NonNull String status) {
        return getAllowedStatusList().contains(status);
    }

    @NonNull
    abstract List<String> getAllowedStatusList();
}
