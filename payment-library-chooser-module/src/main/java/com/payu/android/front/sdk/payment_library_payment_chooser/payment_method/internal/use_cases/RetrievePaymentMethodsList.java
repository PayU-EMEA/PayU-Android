package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.BlikTokenMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.CardPaymentMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.GenericBlikMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.PexPaymentPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.SelectedPaymentMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.TokenMatcher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates.PaymentMethodListComparator;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodsAdapter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodExtractor;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

public class RetrievePaymentMethodsList {
    @NonNull
    private final SelectedPaymentMethodExtractor selectedPaymentMethodExtractor;
    @NonNull
    private PaymentMethodRepository paymentMethodRepository;
    @Nullable
    private PaymentMethodsAdapter paymentMethodsAdapter;

    public RetrievePaymentMethodsList(
            @NonNull PaymentMethodRepository paymentMethodRepository, @Nullable PaymentMethodsAdapter paymentMethodsAdapter,
            @NonNull SelectedPaymentMethodExtractor selectedPaymentMethodExtractor) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodsAdapter = paymentMethodsAdapter;
        this.selectedPaymentMethodExtractor = selectedPaymentMethodExtractor;
    }

    @NonNull
    public LiveData<List<PaymentMethod>> getPaymentMethods() {
        if (paymentMethodsAdapter != null) {
            return Transformations.switchMap(paymentMethodsAdapter.refreshData(), new Function<Boolean, LiveData<List<PaymentMethod>>>() {
                @Override
                public LiveData<List<PaymentMethod>> apply(Boolean input) {
                    return getFilteredPaymentMethods();
                }
            });
        } else {
            return getFilteredPaymentMethods();
        }
    }

    @NonNull
    private LiveData<List<PaymentMethod>> getFilteredPaymentMethods() {
        return Transformations.map(paymentMethodRepository.getPayments(), new Function<List<PaymentMethod>, List<PaymentMethod>>() {
            @Override
            public List<PaymentMethod> apply(@NonNull List<PaymentMethod> input) {
                Optional<PaymentMethod> selectedPaymentMethod = selectedPaymentMethodExtractor.getSelectedPaymentMethod(input);
                return filterAndSort(input, selectedPaymentMethod.orNull());
            }
        });
    }

    @NonNull
    private List<PaymentMethod> filterAndSort(@NonNull List<PaymentMethod> input, @Nullable final PaymentMethod selectedPaymentMethod) {
        List<PaymentMethod> filteredMethods = newArrayList(filter(input, createPredicate(selectedPaymentMethod)));
        Collections.sort(filteredMethods, new PaymentMethodListComparator(selectedPaymentMethod));
        return filteredMethods;
    }

    private Predicate<PaymentMethod> createPredicate(@Nullable PaymentMethod selectedPaymentMethod) {
        TokenHasher tokenHasher = new TokenHasher();
        String hashOrNull = getSelectedMethodHashOrNull(tokenHasher, selectedPaymentMethod);
        SelectedPaymentMethodPredicate selectedPaymentMethodPredicate = new SelectedPaymentMethodPredicate(new TokenMatcher(hashOrNull, tokenHasher));
        Predicate<PaymentMethod> predicates = Predicates.or(new CardPaymentMethodPredicate(), selectedPaymentMethodPredicate, new PexPaymentPredicate());
        if (paymentMethodsAdapter != null) {
            predicates = Predicates.or(predicates, paymentMethodsAdapter.createPredicate());
        }
        if (paymentMethodRepository.isBlikPaymentPossible()) {
            predicates = Predicates.or(predicates, new BlikTokenMethodPredicate(), new GenericBlikMethodPredicate());
        }
        return predicates;
    }

    private String getSelectedMethodHashOrNull(TokenHasher tokenHasher, PaymentMethod
            selectedPaymentMethod) {
        return selectedPaymentMethod != null ? tokenHasher.getHash(selectedPaymentMethod.getValue()) : null;
    }

    public void updateSelectedMethod(@NonNull String id) {
        paymentMethodRepository.updateSelectedMethod(id);
    }

    public void removePaymentMethod(@NonNull String id) {
        paymentMethodRepository.removePaymentMethod(id);
    }
}
