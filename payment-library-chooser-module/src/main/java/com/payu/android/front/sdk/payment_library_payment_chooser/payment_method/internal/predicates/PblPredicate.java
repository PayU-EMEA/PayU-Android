package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class PblPredicate implements Predicate<PaymentMethod> {

    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return input instanceof PayByLinkPaymentMethod;
    }
}
