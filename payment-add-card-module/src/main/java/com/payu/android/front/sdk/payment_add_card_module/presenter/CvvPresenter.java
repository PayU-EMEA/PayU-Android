package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;

public interface CvvPresenter {

    void takeView(@NonNull SelectorCvv view);

    void setCardIssuer(CardIssuer cardIssuer);

    boolean validate();

    String getCvvCode();
}
