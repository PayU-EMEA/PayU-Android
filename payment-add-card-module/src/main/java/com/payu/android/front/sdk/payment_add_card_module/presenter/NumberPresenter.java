package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.view.SelectNumber;
import com.payu.android.front.sdk.payment_library_core.validable.Validable;

public interface NumberPresenter extends Validable {

    void takeView(@NonNull SelectNumber view);

    String getCardNumber();
}
