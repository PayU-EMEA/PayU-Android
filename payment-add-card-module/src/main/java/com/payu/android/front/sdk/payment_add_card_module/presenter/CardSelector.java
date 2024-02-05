package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorView;

public interface CardSelector {

    void takeView(@NonNull SelectorView view);

    void selectIssuer(@NonNull CardIssuer currentIssuer);

}
