package com.payu.android.front.sdk.payment_add_card_module.validation;

import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.common.base.Preconditions;

public class ValidationErrorCleanerTextWatcher implements TextWatcher {

    private TextInputLayout dependentTextInputLayout;

    public ValidationErrorCleanerTextWatcher(@NonNull TextInputLayout dependentTextInputLayout) {
        Preconditions.checkNotNull(dependentTextInputLayout, "dependentTextInputLayout is not nullable");
        this.dependentTextInputLayout = dependentTextInputLayout;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // unused
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // unused
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        dependentTextInputLayout.setError(null);
    }
}