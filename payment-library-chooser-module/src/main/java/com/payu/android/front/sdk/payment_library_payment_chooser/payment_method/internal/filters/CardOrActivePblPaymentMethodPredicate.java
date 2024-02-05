package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class CardOrActivePblPaymentMethodPredicate implements Predicate<PaymentMethod> {
    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return input.getPaymentType() == PaymentType.CARD || new ActivePblPaymentPredicate().apply(input);
    }
}
