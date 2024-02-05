package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.validation.StringValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.cvv.CvvValidatorFactory;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

import static com.google.common.base.Preconditions.checkArgument;

public class CardCvvPresenter extends BasePresenter<SelectorCvv> implements CvvPresenter {
    private CvvValidatorFactory cvvValidatorFactory;
    private CardIssuer cardIssuer;
    private Translation translation;
    private final ValidableView.ValidateOnFocusChangeListener onFocusChangeListener = new ValidableView.ValidateOnFocusChangeListener() {
        @Override
        public void validateOnFocusChange(boolean hasFocus) {
            showHideValidationError(hasFocus);
        }
    };

    public CardCvvPresenter() {
        cvvValidatorFactory = new CvvValidatorFactory();
        translation = TranslationFactory.getInstance();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.view.addValidateOnFocusChangeListener(onFocusChangeListener);
    }

    @Override
    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
    }


    @Override
    public boolean validate() {
        checkArgument(view != null, "View should be set");
        StringValidator stringValidator = cvvValidatorFactory.getValidator(cardIssuer);
        Optional<String> errorString = stringValidator.getErrorString(MoreObjects.firstNonNull(view.getCvvCode(), "").toString());
        view.setCvvError(errorString.orNull());
        return !errorString.isPresent();
    }

    @Override
    public String getCvvCode() {
        checkArgument(view != null, "View should be set");
        return view.getCvvCode();
    }

    private void showHideValidationError(boolean hasFocus) {
        if (hasFocus) {
            view.setCvvError(null);
        } else {
            validate();
        }
    }
}
