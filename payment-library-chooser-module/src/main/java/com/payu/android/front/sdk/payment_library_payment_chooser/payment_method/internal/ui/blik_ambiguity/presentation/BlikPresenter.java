package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.presentation;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.BlikAmbiguityRepository;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.view.BlikView;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation.PaymentMethodConverter;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

public class BlikPresenter extends BasePresenter<BlikView> {
    private BlikAmbiguityRepository repository;
    private PaymentMethodConverter converter;

    public BlikPresenter(@NonNull BlikAmbiguityRepository repository, @NonNull PaymentMethodConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public void onLoad() {
        view.bindToModel(getBliks());
    }

    public LiveData<List<PaymentMethodModel>> getBliks() {
        return Transformations.map(repository.getBliks(), new Function<List<PaymentMethod>, List<PaymentMethodModel>>() {
            @Override
            public List<PaymentMethodModel> apply(List<PaymentMethod> input) {
                return converter.convert(input);
            }
        });
    }

    public PaymentMethod getSelectedBlik() {
        return repository.getSelectedBlik().getValue();
    }

    public void onSelectedBlik(@NonNull PaymentMethodModel paymentMethod) {
        repository.selectBlik(getSelectedPaymentMethod(paymentMethod.getId()));
        view.closeWithSelectedBlik();
    }

    private PaymentMethod getSelectedPaymentMethod(@NonNull String id) {
        for (PaymentMethod paymentMethod : repository.getBliks().getValue()) {
            if (paymentMethod.getValue().equals(id)) {
                return paymentMethod;
            }

        }
        return null;
    }
}