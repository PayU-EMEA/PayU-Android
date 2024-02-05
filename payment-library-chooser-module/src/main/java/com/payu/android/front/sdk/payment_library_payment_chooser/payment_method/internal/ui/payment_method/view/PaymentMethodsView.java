package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view;

import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;

import java.util.List;

public interface PaymentMethodsView {
    void bindToPaymentMethods(@NonNull LiveData<List<PaymentMethodModel>> paymentMethods);

    void showBankPaymentsScreen();

    void close();

    void showAddCardScreen();

    void showItemRemovalConfirmation(Runnable onConfirmed);

    void setLoadingVisible(boolean isVisible);
}
