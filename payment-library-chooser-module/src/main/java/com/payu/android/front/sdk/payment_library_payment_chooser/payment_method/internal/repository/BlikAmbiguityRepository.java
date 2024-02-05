package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters.BlikAmbiguityMethodPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

public class BlikAmbiguityRepository implements PaymentMethodsCallback {

    @NonNull
    private final MutableLiveData<List<PaymentMethod>> paymentMethodsLiveData;
    @NonNull
    private final PaymentMethodActions paymentMethodsActions;
    @NonNull
    private final MutableLiveData<PaymentMethod> selectedPaymentMethod;

    private List<PaymentMethod> paymentMethodList;

    BlikAmbiguityRepository(@NonNull PaymentMethodClassConfigurationProvider paymentMethodClassConfigurationProvider) {
        this.paymentMethodsActions = paymentMethodClassConfigurationProvider.getPaymentMethodsActions();
        this.paymentMethodsLiveData = new MutableLiveData<>();
        this.selectedPaymentMethod = new MutableLiveData<>();
    }

    @Override
    public void onFetched(List<PaymentMethod> paymentMethods) {
        paymentMethodList = new ArrayList<>(filter(paymentMethods, new BlikAmbiguityMethodPredicate()));
        paymentMethodsLiveData.postValue(paymentMethodList);
    }

    @Override
    public void onClean() {
        clearSelectedPaymentMethod();
        paymentMethodList = null;
        paymentMethodsLiveData.postValue(new ArrayList<PaymentMethod>());
    }

    @Override
    public void onCleanWithoutRemovingSelectedMethod() {
        onClean();
    }

    private void clearSelectedPaymentMethod() {
        selectedPaymentMethod.postValue(null);
    }

    public LiveData<List<PaymentMethod>> getBliks() {
        if (paymentMethodList == null) {
            paymentMethodsActions.provideBlikPaymentMethods(this);
        }
        return paymentMethodsLiveData;
    }

    public LiveData<PaymentMethod> getSelectedBlik() {
        if (paymentMethodList == null) {
            getBliks();
        }
        return selectedPaymentMethod;
    }

    public void selectBlik(@NonNull PaymentMethod blik) {
        selectedPaymentMethod.setValue(blik);
    }
}
