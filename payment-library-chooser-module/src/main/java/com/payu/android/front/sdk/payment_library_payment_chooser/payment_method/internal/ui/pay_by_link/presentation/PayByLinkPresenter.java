package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view.PayByLinkView;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePblPaymentMethods;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

public class PayByLinkPresenter extends BasePresenter<PayByLinkView> {
    @NonNull
    private final RetrievePblPaymentMethods paymentMethodRepository;
    @NonNull
    private final PblModelConverter modelConverter;

    public PayByLinkPresenter(@NonNull RetrievePblPaymentMethods paymentMethodRepository, @NonNull PblModelConverter modelConverter) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public void onLoad() {
        view.bindToPayByLinkModel(getPblModelLiveData());
    }

    @NonNull
    private LiveData<List<PayByLinkModel>> getPblModelLiveData() {
        return Transformations.map(paymentMethodRepository.getPaymentMethods(), new Function<List<PaymentMethod>, List<PayByLinkModel>>() {
            @Override
            public List<PayByLinkModel> apply(List<PaymentMethod> input) {
                return modelConverter.convert(input);
            }
        });
    }

    public void onPblSelected(PayByLinkModel payByLinkModel) {
        paymentMethodRepository.updateSelectedMethod(payByLinkModel.getId());
        view.closeWithSuccess();
    }
}
