package com.payu.android.front.sdk.payment_add_card_module.textwatcher;

import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.common.base.Objects;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.validation.LuhnValidator;

public class CardIssuerLogoTextWatcher implements TextWatcher {
    private LuhnValidator luhnValidator;
    private CardIssuer currentCardIssuer;
    private CardIssuerProvider cardIssuerProvider;
    private OnCardIssuerChangedListener onCardIssuerChanged;
    private String previousValue;

    public CardIssuerLogoTextWatcher(@NonNull CardIssuerProvider cardIssuerProvider, @NonNull LuhnValidator luhnValidator, @NonNull OnCardIssuerChangedListener onCardIssuerChanged) {
        this.cardIssuerProvider = cardIssuerProvider;
        this.luhnValidator = luhnValidator;
        this.onCardIssuerChanged = onCardIssuerChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        // Not used.
    }

    @Override
    public void onTextChanged(CharSequence newValue, int start, int before, int count) {
        String cardNumber = cardIssuerProvider.dropDashAndWhitespaces(newValue.toString());

        if (!Objects.equal(previousValue, cardNumber)) {
            previousValue = cardNumber;
            CardIssuer newCardProvider = getCardProvider(cardNumber);

            if (!newCardProvider.equals(currentCardIssuer)) {
                currentCardIssuer = newCardProvider;
                notifyListener(newCardProvider);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // Not used.

    }

    private boolean isPassingLuhnTest(CharSequence cardNumber) {
        return luhnValidator.isValid(cardNumber.toString());
    }

    private void notifyListener(CardIssuer newCardProvider) {

        if (onCardIssuerChanged != null) {
            onCardIssuerChanged.onCardIssuerChanged(newCardProvider);
        }
    }

    private CardIssuer getCardProvider(CharSequence cardNumber) {
        CardIssuer newCardProvider = cardIssuerProvider.getCardProvider(cardNumber.toString());
        return isPassingLuhnTest(cardNumber) ? newCardProvider : CardIssuer.UNKNOWN;
    }
}
