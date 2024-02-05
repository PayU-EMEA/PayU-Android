package com.payu.android.front.sdk.payment_add_card_module.textwatcher;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;

public interface OnCardIssuerChangedListener {
    void onCardIssuerChanged(CardIssuer newCardProvider);

}
