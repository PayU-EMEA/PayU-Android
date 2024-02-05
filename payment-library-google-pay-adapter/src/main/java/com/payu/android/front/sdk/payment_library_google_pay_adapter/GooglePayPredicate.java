package com.payu.android.front.sdk.payment_library_google_pay_adapter;

import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;

public class GooglePayPredicate implements Predicate<PaymentMethod> {
    private boolean isGooglePayEnabled;

    GooglePayPredicate(boolean isGooglePayEnabled) {
        this.isGooglePayEnabled = isGooglePayEnabled;
    }

    @Override
    public boolean apply(@NonNull PaymentMethod input) {
        return isGooglePayEnabled && input.getPaymentType() == PaymentType.GOOGLE_PAY;
    }
}
