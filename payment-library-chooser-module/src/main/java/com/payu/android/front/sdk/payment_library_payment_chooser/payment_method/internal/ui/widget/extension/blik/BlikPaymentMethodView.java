package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.extension.blik;

import androidx.annotation.Nullable;

public interface BlikPaymentMethodView {
    void showInput();

    void hideInput();

    void showCodeAction();

    void hideCodeAction();

    boolean isInputDisplayed();

    @Nullable
    String getBlikCode();

}
