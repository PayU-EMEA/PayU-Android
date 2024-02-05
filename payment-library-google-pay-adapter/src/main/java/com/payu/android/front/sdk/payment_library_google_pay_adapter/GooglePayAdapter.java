package com.payu.android.front.sdk.payment_library_google_pay_adapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.google.common.base.Predicate;
import com.payu.android.front.sdk.payment_library_google_pay_module.listener.GooglePayVerificationListener;
import com.payu.android.front.sdk.payment_library_google_pay_module.listener.GooglePayVerificationStatus;
import com.payu.android.front.sdk.payment_library_google_pay_module.service.GooglePayService;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodsAdapter;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import static com.google.common.base.Optional.fromNullable;

public class GooglePayAdapter implements PaymentMethodsAdapter {
    private final MutableLiveData<Boolean> liveData;
    private GooglePayService googlePayService;

    public GooglePayAdapter(@NonNull GooglePayService googlePayService) {
        this.googlePayService = googlePayService;
        liveData = new MutableLiveData<>();
        liveData.setValue(false);
    }

    @Override
    public LiveData<Boolean> refreshData() {
        googlePayService.isReadyToPay(new GooglePayVerificationListener() {
               @Override
            public void onVerificationCompleted(GooglePayVerificationStatus verificationStatus) {
                liveData.postValue(verificationStatus == GooglePayVerificationStatus.SUCCESS);
            }

            @Override
            public void onException(@NonNull Exception exception) {
                liveData.postValue(false);
            }
        },true);
        return liveData;
    }

    @Override
    public Predicate<PaymentMethod> createPredicate() {
        return new GooglePayPredicate(fromNullable(liveData.getValue()).or(false));
    }
}
