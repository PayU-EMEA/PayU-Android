package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.blik.BlikConstants;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.TokenMatcher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodPersistenceStorage;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.GenericBlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//todo write tests for repository
public class PaymentMethodRepository implements PaymentMethodsCallback {
    @NonNull
    private final MutableLiveData<List<PaymentMethod>> paymentMethodsLiveData;
    @NonNull
    private final PaymentMethodActions paymentMethodsActions;
    @NonNull
    private final SelectedPaymentMethodPersistenceStorage selectedPaymentMethodPersistenceStorage;
    @NonNull
    private final TokenHasher tokenHasher;
    @NonNull
    private final MutableLiveData<PaymentMethod> selectedPaymentMethod;
    @NonNull
    private final List<CardPaymentMethod> cardLocalAddedPaymentMethods;
    private List<PaymentMethod> paymentMethodList;

    @Nullable
    private PaymentMethod genericBlikPayment;

    private final boolean isBlikPaymentPossible;

    PaymentMethodRepository(
            @NonNull PaymentMethodClassConfigurationProvider paymentMethodClassConfigurationProvider,
            @NonNull SelectedPaymentMethodPersistenceStorage selectedPaymentMethodPersistenceStorage, @NonNull TokenHasher tokenHasher) {
        this.paymentMethodsActions = paymentMethodClassConfigurationProvider.getPaymentMethodsActions();
        this.selectedPaymentMethodPersistenceStorage = selectedPaymentMethodPersistenceStorage;
        this.tokenHasher = tokenHasher;
        this.paymentMethodsLiveData = new MutableLiveData<>();
        this.selectedPaymentMethod = new MutableLiveData<>();
        this.cardLocalAddedPaymentMethods = new ArrayList<>();
        this.paymentMethodList = new ArrayList<>();
        this.isBlikPaymentPossible = paymentMethodClassConfigurationProvider.isBlikPossible();
        if (isBlikPaymentPossible) {
            genericBlikPayment = new GenericBlikPaymentMethod(BlikConstants.BLIK_GENERIC_VALUE, BlikConstants.BLIK_GENERIC_IMAGE_URL, BlikConstants.BLIK_GENERIC_TYPE);
        }
    }

    public LiveData<List<PaymentMethod>> getPayments() {
        if (paymentMethodList.isEmpty()) {
            paymentMethodsActions.providePaymentMethods(this);
        }
        return paymentMethodsLiveData;
    }

    @NonNull
    public LiveData<PaymentMethod> getSelectedPaymentMethod() {
        if (paymentMethodList.isEmpty()) {
            getPayments();
        }
        return selectedPaymentMethod;
    }

    public void updateSelectedMethod(@NonNull String paymentMethodId) {
        List<PaymentMethod> paymentMethods = paymentMethodList;
        if (paymentMethods == null) {
            return;
        }
        selectedPaymentMethodPersistenceStorage.saveSelectedPaymentMethodHash(tokenHasher.getHash(paymentMethodId));
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getValue().equals(paymentMethodId)) {
                selectedPaymentMethod.postValue(paymentMethod);
                return;
            }
        }
    }

    @Override
    public void onFetched(@NonNull List<PaymentMethod> paymentMethods) {
        paymentMethodList.clear();
        paymentMethodList.addAll(paymentMethods);
        if (shouldAddGenericBlikPaymentMethod(paymentMethods)) {
            paymentMethodList.add(genericBlikPayment);
        }
        handlePaymentMethods(paymentMethodList);
    }

    private boolean shouldAddGenericBlikPaymentMethod(@NonNull List<PaymentMethod> paymentMethods) {
        return genericBlikPayment != null && !containsBlikPaymentMethod(paymentMethods) && !paymentMethods.contains(genericBlikPayment);
    }

    private boolean containsBlikPaymentMethod(@NonNull List<PaymentMethod> paymentMethods) {
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getPaymentType().equals(PaymentType.BLIK_TOKENS)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClean() {
        clearSelectedPaymentMethod();
        clearPaymentMethodList();
    }

    @Override
    public void onCleanWithoutRemovingSelectedMethod() {
        clearSelectedPaymentMethodWithoutPersistenceStorage();
        clearPaymentMethodList();
    }

    private void clearPaymentMethodList() {
        paymentMethodList.clear();
        paymentMethodsLiveData.setValue(new ArrayList<PaymentMethod>());
    }

    private void handlePaymentMethods(List<PaymentMethod> paymentMethods) {
        paymentMethodsLiveData.postValue(new ArrayList<>(paymentMethods));
        resolveSelectedMethod(new ArrayList<>(paymentMethodList));
    }

    private void resolveSelectedMethod(List<PaymentMethod> internalList) {
        Optional<String> selectedMethodHash = selectedPaymentMethodPersistenceStorage.getSelectedMethodHash();
        if (!selectedMethodHash.isPresent()) {
            return;
        }
        TokenMatcher tokenMatcher = new TokenMatcher(selectedMethodHash.get(), new TokenHasher());
        for (PaymentMethod paymentMethod : internalList) {
            if (tokenMatcher.isMatching(paymentMethod.getValue())) {
                selectedPaymentMethod.postValue(paymentMethod);
            }
        }
    }

    public void addLocalCardPaymentMethod(@NonNull CardPaymentMethod cardPaymentMethod) {
        paymentMethodList.add(cardPaymentMethod);
        cardLocalAddedPaymentMethods.add(cardPaymentMethod);
        paymentMethodsLiveData.postValue(new ArrayList<>(paymentMethodList));
    }

    public void removePaymentMethod(@NonNull String elementId) {
        List<PaymentMethod> paymentMethods = paymentMethodList;
        removePaymentMethodById(elementId, paymentMethods);
        paymentMethodsLiveData.postValue(new ArrayList<>(paymentMethods));
    }

    private void removePaymentMethodById(@NonNull String elementId, List<PaymentMethod> value) {
        ListIterator<PaymentMethod> paymentMethodListIterator = value.listIterator();
        while (paymentMethodListIterator.hasNext()) {
            PaymentMethod paymentMethod = paymentMethodListIterator.next();
            if (paymentMethod.getValue().equals(elementId)) {
                handlePaymentMethodRemoval(paymentMethodListIterator, paymentMethod);
                break;
            }
        }
    }

    private void handlePaymentMethodRemoval(
            @NonNull ListIterator<PaymentMethod> paymentMethodListIterator,
            @NonNull PaymentMethod paymentMethod) {
        if (isPaymentMethodSelected(paymentMethod)) {
            clearSelectedPaymentMethod();
        }
        if (paymentMethod instanceof CardPaymentMethod) {
            paymentMethodListIterator.remove();
            removeStoredCardPaymentMethod(paymentMethod);
        }
    }

    private void removeStoredCardPaymentMethod(@NonNull PaymentMethod paymentMethod) {
        if (!cardLocalAddedPaymentMethods.contains(paymentMethod)) {
            paymentMethodsActions.onPaymentMethodRemoved(paymentMethod);
        } else {
            cardLocalAddedPaymentMethods.remove(paymentMethod);
        }
    }

    private void clearSelectedPaymentMethod() {
        clearSelectedPaymentMethodWithoutPersistenceStorage();
        selectedPaymentMethodPersistenceStorage.clear();
    }

    private void clearSelectedPaymentMethodWithoutPersistenceStorage() {
        selectedPaymentMethod.postValue(null);
    }

    private boolean isPaymentMethodSelected(@NonNull PaymentMethod paymentMethod) {
        return selectedPaymentMethod.getValue() != null && selectedPaymentMethod.getValue().equals(paymentMethod);
    }

    public boolean isBlikPaymentPossible() {
        return isBlikPaymentPossible;
    }
}
