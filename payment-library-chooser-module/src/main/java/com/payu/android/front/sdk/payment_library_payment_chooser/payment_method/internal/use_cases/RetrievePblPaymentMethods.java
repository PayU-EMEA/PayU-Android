package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.Lists.newArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates.BlackListPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates.PblPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.Collections;
import java.util.List;

public class RetrievePblPaymentMethods {
    private static final List<String> PROHIBITED_METHODS = Collections.singletonList("ai");

    @NonNull
    private final PaymentMethodRepository paymentMethodRepository;

    public RetrievePblPaymentMethods(@NonNull PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @NonNull
    public LiveData<List<PaymentMethod>> getPaymentMethods() {
        return getFilteredPaymentMethods();
    }

    @NonNull
    private LiveData<List<PaymentMethod>> getFilteredPaymentMethods() {
        return Transformations.map(
                paymentMethodRepository.getPayments(),
                input -> newArrayList(Collections2.filter(input, createPredicate()))
        );
    }

    private Predicate<PaymentMethod> createPredicate() {
        return and(new BlackListPredicate(PROHIBITED_METHODS), new PblPredicate());
    }

    public void updateSelectedMethod(@NonNull String id) {
        paymentMethodRepository.updateSelectedMethod(id);
    }
}
