package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public interface OnSelectionPaymentMethodListener {
    void onSelectionChange(@Nullable PaymentMethod paymentMethod);
}
