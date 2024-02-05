package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.payu.android.front.sdk.payment_library_payment_methods.model.StatusConstants.*;

public final class PblStatusValidator extends StatusValidator {

    @NonNull
    @Override
    List<String> getAllowedStatusList() {
        return ImmutableList.of(ENABLED, DISABLED, TEMPORARY_DISABLED);
    }
}
