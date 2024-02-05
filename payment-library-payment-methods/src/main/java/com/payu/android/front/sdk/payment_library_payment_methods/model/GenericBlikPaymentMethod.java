package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import androidx.annotation.NonNull;

public final class GenericBlikPaymentMethod extends BlikPaymentMethod {
    public GenericBlikPaymentMethod(@NonNull String value, @NonNull String brandImageUrl, @NonNull String type) {
        super(value, brandImageUrl, type);
    }

    GenericBlikPaymentMethod(Parcel in) {
        super(in);
    }

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.BLIK_GENERIC;
    }
}
