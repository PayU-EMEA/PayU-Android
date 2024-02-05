package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

public class BlikAmbiguityPaymentMethodBuilder {
    private String appKey;
    private String appLabel;

    public BlikAmbiguityPaymentMethodBuilder withKey(@NonNull String key) {
        this.appKey = key;
        return this;
    }


    public BlikAmbiguityPaymentMethodBuilder withLabel(@NonNull String label) {
        this.appLabel = label;
        return this;
    }

    public BlikAmbiguityPaymentMethod build() {
        checkArgument(notEmpty(appKey), "key cannot be empty");
        checkArgument(notEmpty(appLabel), "label cannot be empty");
        return new BlikAmbiguityPaymentMethod(appKey, appLabel, null);
    }
}
