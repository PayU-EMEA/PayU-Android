package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view;

import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;

import java.util.List;

public interface PayByLinkView {
    void bindToPayByLinkModel(LiveData<List<PayByLinkModel>> pblModelLiveData);

    void closeWithSuccess();
}
