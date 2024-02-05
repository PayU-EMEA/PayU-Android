package com.payu.android.front.sdk.payment_add_card_module.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface SelectorCvv extends ValidableView {

    @NonNull
    String getCvvCode();

    void setCvvError(@Nullable String error);
}
