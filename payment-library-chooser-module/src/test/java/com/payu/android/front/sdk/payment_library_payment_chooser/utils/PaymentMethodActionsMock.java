package com.payu.android.front.sdk.payment_library_payment_chooser.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PosIdListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class PaymentMethodActionsMock extends PaymentMethodActions {
    public PaymentMethodActionsMock(Context context) {
        super(context);
    }

    @Override
    public void providePaymentMethods(PaymentMethodsCallback callback) {

    }

    @Override
    public void onPaymentMethodRemoved(@NonNull PaymentMethod paymentMethod) {

    }

    @Override
    public void providePosId(@NonNull PosIdListener posIdListener) {
        posIdListener.onPosId("123456");
    }
}
