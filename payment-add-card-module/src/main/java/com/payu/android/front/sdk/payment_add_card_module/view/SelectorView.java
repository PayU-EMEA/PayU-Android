package com.payu.android.front.sdk.payment_add_card_module.view;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;

public interface SelectorView {

    void deselect(@NonNull CardIssuer issuer);

    void select(@NonNull CardIssuer issuer);
}
