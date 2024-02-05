package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

public class WhiteListPredicate implements Predicate<PaymentMethod> {
    @NonNull
    private List<String> filter;

    public WhiteListPredicate(@NonNull List<String> filter) {
        this.filter = filter;
    }

    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return filter.contains(input.getValue());
    }
}
