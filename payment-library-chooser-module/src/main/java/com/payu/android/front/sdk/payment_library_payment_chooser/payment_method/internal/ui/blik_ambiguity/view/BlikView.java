package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.view;

import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;

import java.util.List;

public interface BlikView {
    void closeWithSelectedBlik();

    //TODO change name when we declare if PM is Blik or general
    void bindToModel(LiveData<List<PaymentMethodModel>> modelLiveData);
}
