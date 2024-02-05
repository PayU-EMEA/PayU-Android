package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class SelectedPaymentMethodPredicate implements Predicate<PaymentMethod> {
    private final TokenMatcher tokenMatcher;

    public SelectedPaymentMethodPredicate(@NonNull TokenMatcher tokenMatcher) {
        this.tokenMatcher = tokenMatcher;
    }

    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return tokenMatcher.isMatching(input.getValue());
    }
}
