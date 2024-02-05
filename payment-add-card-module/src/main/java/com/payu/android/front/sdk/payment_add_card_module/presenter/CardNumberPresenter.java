package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_add_card_module.formatter.CardNumberFormattingStrategy;
import com.payu.android.front.sdk.payment_add_card_module.formatter.FormattingStrategy;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.OnCardIssuerChangedListener;
import com.payu.android.front.sdk.payment_add_card_module.validation.CardNumberStringValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.LuhnValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.StringValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.message.CardNumberInvalidMessageProvider;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectNumber;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;

import static com.google.common.base.Preconditions.checkArgument;

public class CardNumberPresenter implements NumberPresenter {
    private static final String REG_EXP_REMOVE_EMPTY_SPACE = "\\s+";
    private SelectNumber view;
    private OnCardIssuerChangedListener externalOnCardIssuerChangedListener;
    private StringValidator editTextValidator;
    private FormattingStrategy formattingStrategy;

    private OnCardIssuerChangedListener onCardIssuerChangedListener = new OnCardIssuerChangedListener() {

        @Override
        public void onCardIssuerChanged(CardIssuer newCardProvider) {
            checkArgument(externalOnCardIssuerChangedListener != null, "changedListener should be set");
            externalOnCardIssuerChangedListener.onCardIssuerChanged(newCardProvider);
        }
    };
    private final ValidableView.ValidateOnFocusChangeListener onFocusChangeListener = new ValidableView.ValidateOnFocusChangeListener() {
        @Override
        public void validateOnFocusChange(boolean hasFocus) {
            showHideValidationError(hasFocus);
        }
    };


    public CardNumberPresenter(@NonNull OnCardIssuerChangedListener onCardIssuerChangedListener) {
        externalOnCardIssuerChangedListener = onCardIssuerChangedListener;
        formattingStrategy = new CardNumberFormattingStrategy();
    }

    @Override
    public void takeView(@NonNull SelectNumber view) {
        this.view = view;
        prepareLogic();
    }

    @Override
    public String getCardNumber() {
        checkArgument(view != null, "View should be set");
        return view.getText().replaceAll(REG_EXP_REMOVE_EMPTY_SPACE, "");
    }

    @Override
    public boolean validate() {
        checkArgument(view != null, "View should be set");
        Optional<String> errorString = editTextValidator.getErrorString(MoreObjects.firstNonNull(view.getText(), "").toString());
        view.setNumberError(errorString.orNull());
        return !errorString.isPresent();
    }

    private void prepareLogic() {
        LuhnValidator luhnValidator = new LuhnValidator();
        CardIssuerProvider cardIssuerProvider = CardIssuerProvider.getInstance();
        editTextValidator = new CardNumberStringValidator(new CardNumberInvalidMessageProvider(), cardIssuerProvider, luhnValidator);
        view.addCardIssuerLogoStrategy(cardIssuerProvider, luhnValidator, onCardIssuerChangedListener);
        view.addFormattingStrategy(formattingStrategy);
        view.addValidateOnFocusChangeListener(onFocusChangeListener);
    }

    private void showHideValidationError(boolean hasFocus) {
        if (hasFocus) {
            view.setNumberError(null);
        } else {
            validate();
        }
    }
}
