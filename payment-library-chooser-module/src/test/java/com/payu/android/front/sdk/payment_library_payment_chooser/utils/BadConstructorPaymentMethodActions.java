package com.payu.android.front.sdk.payment_library_payment_chooser.utils;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class BadConstructorPaymentMethodActions extends PaymentMethodActions {
    public BadConstructorPaymentMethodActions() {
        super(null);
    }

    @Override
    public void providePaymentMethods(PaymentMethodsCallback callback) {

    }

    @Override
    public void onPaymentMethodRemoved(@NonNull PaymentMethod paymentMethod) {

    }
}
