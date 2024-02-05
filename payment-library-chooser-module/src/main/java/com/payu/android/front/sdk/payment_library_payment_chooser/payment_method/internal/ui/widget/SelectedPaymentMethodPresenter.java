package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import static com.google.common.base.Preconditions.checkNotNull;

class SelectedPaymentMethodPresenter extends BasePresenter<SelectedPaymentMethodView> {
    @NonNull
    private final PaymentMethodRepository paymentMethodRepository;
    @NonNull
    private final SelectedPaymentMethodConverter selectedPaymentMethodConverter;

    SelectedPaymentMethodPresenter(
            @NonNull PaymentMethodRepository paymentMethodRepository, @NonNull SelectedPaymentMethodConverter selectedPaymentMethodConverter) {
        this.paymentMethodRepository = checkNotNull(paymentMethodRepository);
        this.selectedPaymentMethodConverter = checkNotNull(selectedPaymentMethodConverter);
    }

    @NonNull
    LiveData<SelectedPaymentMethodModel> getSelectedPaymentMethodLiveData() {
        return Transformations.map(paymentMethodRepository.getSelectedPaymentMethod(), new Function<PaymentMethod, SelectedPaymentMethodModel>() {
            @Override
            public SelectedPaymentMethodModel apply(@Nullable PaymentMethod input) {
                if (input != null) {
                    return selectedPaymentMethodConverter.convert(input);
                } else {
                    return null;
                }
            }
        });
    }

    @NonNull
    LiveData<PaymentMethod> getPaymentMethod() {
        return paymentMethodRepository.getSelectedPaymentMethod();
    }

    public void onSelectPaymentMethodClick() {
        view.showPaymentSelector();
    }

    public void cleanPaymentMethods() {
        paymentMethodRepository.onClean();
    }

    public void cleanPaymentMethodWithoutRemovingSelectedMethod(){
        paymentMethodRepository.onCleanWithoutRemovingSelectedMethod();
    }

}
