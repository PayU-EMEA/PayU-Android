package com.payu.android.front.sdk.payment_add_card_module.view;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.formatter.FormattingStrategy;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.OnCardIssuerChangedListener;
import com.payu.android.front.sdk.payment_add_card_module.validation.LuhnValidator;

public interface SelectNumber extends ValidableView {

    String getText();

    void setNumberError(String error);

    void addFormattingStrategy(@NonNull FormattingStrategy formattingStrategy);

    void addCardIssuerLogoStrategy(@NonNull CardIssuerProvider cardIssuerProvider, @NonNull LuhnValidator luhnValidator, @NonNull OnCardIssuerChangedListener onCardIssuerChanged);

    void setCardNumber(@NonNull String cardNumber);
}
