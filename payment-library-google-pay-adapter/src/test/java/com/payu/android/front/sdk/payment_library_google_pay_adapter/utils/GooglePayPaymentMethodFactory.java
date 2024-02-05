package com.payu.android.front.sdk.payment_library_google_pay_adapter.utils;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodCreator;

public class GooglePayPaymentMethodFactory {
    @NonNull
    public static PaymentMethod createPblPayment(@NonNull String value) {
        return PaymentMethodCreator.pblBuilder()
                                   .withName("Google Pay")
                                   .withValue(value)
                                   .withBrandImageUrl("NONE")
                                   .withStatus("ENABLED")
                                   .build();
    }
}
