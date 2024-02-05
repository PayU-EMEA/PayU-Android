package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.ActivePaymentMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.SelectedPaymentMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.TokenMatcher;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

import static com.google.common.base.Optional.of;

public class SelectedPaymentMethodExtractor {
    @NonNull
    private final SelectedPaymentMethodPersistenceStorage selectedPaymentMethodPersistenceStorage;
    @NonNull
    private final TokenHasher tokenHasher;

    public SelectedPaymentMethodExtractor(@NonNull SelectedPaymentMethodPersistenceStorage selectedPaymentMethodPersistenceStorage, @NonNull TokenHasher tokenHasher) {
        this.selectedPaymentMethodPersistenceStorage = selectedPaymentMethodPersistenceStorage;
        this.tokenHasher = tokenHasher;
    }

    public Optional<PaymentMethod> getSelectedPaymentMethod(@NonNull List<PaymentMethod> paymentMethods) {
        if (paymentMethods.size() == 0) {
            return Optional.absent();
        }
        Optional<String> storedHash = selectedPaymentMethodPersistenceStorage.getSelectedMethodHash();
        return storedHash.isPresent() ? getSelectedPaymentByHash(paymentMethods, storedHash.get()) : getAutoSelectedPaymentMethod(paymentMethods);
    }

    private Optional<PaymentMethod> getAutoSelectedPaymentMethod(@NonNull List<PaymentMethod> paymentMethods) {
        Optional<PaymentMethod> methodOptional = getFirstIfAvailableAndNoMoreThanOne(paymentMethods);

        if (methodOptional.isPresent()) {
            selectedPaymentMethodPersistenceStorage.saveSelectedPaymentMethodHash(tokenHasher.getHash(methodOptional.get().getValue()));
        }

        return methodOptional;
    }

    private Optional<PaymentMethod> getFirstIfAvailableAndNoMoreThanOne(List<PaymentMethod> paymentMethods) {
        Optional<PaymentMethod> maybeSelectablePaymentMethod = getOnlyMethodOrEmpty(paymentMethods);
        return maybeSelectablePaymentMethod.isPresent() ? maybeSelectablePaymentMethod : Optional.<PaymentMethod>absent();
    }

    private Optional<PaymentMethod> getOnlyMethodOrEmpty(List<PaymentMethod> paymentMethods) {
        return hasSinglePayment(paymentMethods) ? of(paymentMethods.get(0)) : Optional.<PaymentMethod>absent();
    }

    private boolean hasSinglePayment(List<PaymentMethod> paymentMethods) {
        return paymentMethods.size() == 1;
    }

    private Optional<PaymentMethod> getSelectedPaymentByHash(List<PaymentMethod> paymentMethods, final String token) {
        return Iterables.tryFind(paymentMethods,
                Predicates.and(new SelectedPaymentMethodPredicate(new TokenMatcher(token, tokenHasher)), new ActivePaymentMethodPredicate()));
    }
}
