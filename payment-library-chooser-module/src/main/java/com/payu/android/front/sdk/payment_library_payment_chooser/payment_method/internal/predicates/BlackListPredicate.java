package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

public class BlackListPredicate implements Predicate<PaymentMethod> {
    @NonNull
    private final List<String> filter;

    public BlackListPredicate(@NonNull List<String> filter) {
        this.filter = filter;
    }

    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return !filter.contains(input.getValue());
    }
}
