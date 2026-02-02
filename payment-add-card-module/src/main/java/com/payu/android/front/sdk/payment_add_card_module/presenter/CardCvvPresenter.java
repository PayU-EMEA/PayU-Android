package com.payu.android.front.sdk.payment_add_card_module.presenter;

import static com.google.common.base.Preconditions.checkArgument;

import androidx.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_add_card_module.validation.CvvStringValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.StringValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.message.CvvInvalidMessageProvider;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

public class CardCvvPresenter extends BasePresenter<SelectorCvv> implements CvvPresenter {
    private StringValidator editTextValidator;
    private final ValidableView.ValidateOnFocusChangeListener onFocusChangeListener = this::showHideValidationError;

    public CardCvvPresenter() {
    }

    @Override
    public void takeView(@NonNull SelectorCvv view) {
        this.view = view;
        prepareLogic();
    }

    @Override
    public boolean validate() {
        checkArgument(view != null, "View should be set");
        Optional<String> errorString = editTextValidator.getErrorString(MoreObjects.firstNonNull(view.getCvvCode(), ""));
        view.setCvvError(errorString.orNull());
        return !errorString.isPresent();
    }

    @Override
    public String getCvvCode() {
        checkArgument(view != null, "View should be set");
        return view.getCvvCode();
    }

    private void prepareLogic() {
        editTextValidator = new CvvStringValidator(new CvvInvalidMessageProvider());
        this.view.addValidateOnFocusChangeListener(onFocusChangeListener);
    }

    private void showHideValidationError(boolean hasFocus) {
        if (hasFocus) {
            view.setCvvError(null);
        } else {
            validate();
        }
    }
}
