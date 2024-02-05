package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;
import android.util.Pair;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view.PaymentMethodsView;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePaymentMethodsList;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePblPaymentMethods;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.util.CombineLiveData;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.List;

public class PaymentMethodsPresenter extends BasePresenter<PaymentMethodsView> {
    @NonNull
    private final RetrievePaymentMethodsList paymentMethodsUseCase;
    @NonNull
    private final RetrievePblPaymentMethods pblPaymentMethodsUseCase;
    @NonNull
    private final PaymentMethodConverter paymentMethodConverter;
    @NonNull
    private final GeneralContentHandler generalContentHandler;

    public PaymentMethodsPresenter(
            @NonNull RetrievePaymentMethodsList paymentMethodsUseCase, @NonNull RetrievePblPaymentMethods pblPaymentMethodsUseCase,
            @NonNull PaymentMethodConverter paymentMethodConverter, @NonNull GeneralContentHandler generalContentHandler) {
        this.paymentMethodsUseCase = paymentMethodsUseCase;
        this.pblPaymentMethodsUseCase = pblPaymentMethodsUseCase;
        this.paymentMethodConverter = paymentMethodConverter;
        this.generalContentHandler = generalContentHandler;
    }

    @Override
    public void onLoad() {
        view.bindToPaymentMethods(getPaymentMethods());
    }

    @NonNull
    private LiveData<List<PaymentMethodModel>> getPaymentMethods() {
        view.setLoadingVisible(true);
        CombineLiveData<List<PaymentMethod>, List<PaymentMethod>> trigger =
                new CombineLiveData<>(paymentMethodsUseCase.getPaymentMethods(), pblPaymentMethodsUseCase.getPaymentMethods());
        return Transformations.map(trigger, new Function<Pair<List<PaymentMethod>, List<PaymentMethod>>, List<PaymentMethodModel>>() {
            @Override
            public List<PaymentMethodModel> apply(Pair<List<PaymentMethod>, List<PaymentMethod>> input) {
                boolean pblPaymentMethodsEnabled = input.second != null && !input.second.isEmpty();
                if (view != null) {
                    view.setLoadingVisible(false);
                }
                return handlePaymentMethods(input.first, pblPaymentMethodsEnabled);
            }
        });
    }

    @NonNull
    private List<PaymentMethodModel> handlePaymentMethods(@NonNull List<PaymentMethod> input, boolean pblPaymentMethodsEnabled) {
        List<PaymentMethodModel> paymentMethodModels = paymentMethodConverter.convert((input));
        generalContentHandler.appendGeneralContent(paymentMethodModels, pblPaymentMethodsEnabled);
        return paymentMethodModels;
    }

    public void onPaymentMethodSelected(@NonNull PaymentMethodModel method) {
        if (generalContentHandler.isGeneralBankPayment(method)) {
            view.showBankPaymentsScreen();
        } else if (generalContentHandler.isGeneralCardPayment(method)) {
            view.showAddCardScreen();
        } else {
            paymentMethodsUseCase.updateSelectedMethod(method.getId());
            view.close();
        }
    }

    public void onPayByLinkSelectionSuccessful() {
        view.close();
    }

    public void onAddCardSuccessful() {
        view.close();
    }

    public void onPaymentMethodRemoved(final PaymentMethodModel element) {
        if (element.getPaymentMethodType() != PaymentMethodType.GENERAL_ICON && element.getPaymentMethodType() != PaymentMethodType.GENERIC_BLIK) {
            view.showItemRemovalConfirmation(new Runnable() {
                @Override
                public void run() {
                    paymentMethodsUseCase.removePaymentMethod(element.getId());
                }
            });
        }
    }
}
