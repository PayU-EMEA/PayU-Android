package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository;

import androidx.lifecycle.LiveData;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

/**
 * The implementation of this Adapter can be used to enable custom PaymentMethods or refresh policy in payment method list
 */
public interface PaymentMethodsAdapter {
    /**
     * This Predicate is used while Payment methods are filtered
     *
     * @return - Predicate which can enable previously disabled payment methods
     */
    Predicate<PaymentMethod> createPredicate();

    /**
     * Indicates whether data should be refreshed
     *
     * @return LiveData, which every new updates causes UI to refresh.
     */
    LiveData<Boolean> refreshData();
}
