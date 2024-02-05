package com.payu.android.front.sdk.payment_add_card_module.view;

import androidx.annotation.Nullable;

public interface ValidableView {

    interface ValidateOnFocusChangeListener {
        void validateOnFocusChange(boolean hasFocus);
    }

    void addValidateOnFocusChangeListener(@Nullable ValidateOnFocusChangeListener onFocusChangeListener);
}
