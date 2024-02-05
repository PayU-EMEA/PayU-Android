package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_add_card_module.validation.CardDateValidable;
import com.payu.android.front.sdk.payment_add_card_module.view.CardDate;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

import static com.google.common.base.Preconditions.checkArgument;

public class CardDatePresenter extends BasePresenter<CardDate> implements DatePresenter {
    private CardDateValidable cardDateValidable;
    private Translation translation;
    private final ValidableView.ValidateOnFocusChangeListener onFocusChangeListener = new ValidableView.ValidateOnFocusChangeListener() {
        @Override
        public void validateOnFocusChange(boolean hasFocus) {
            showHideValidationError(hasFocus);
        }
    };

    public CardDatePresenter(@NonNull CardDateValidable cardDateValidable, @NonNull Translation translation) {
        this.cardDateValidable = cardDateValidable;
        this.translation = translation;

    }

    @Override
    public void onLoad() {
        super.onLoad();
        view.addValidateOnFocusChangeListener(onFocusChangeListener);
    }

    @Override
    public String getMonth() {
        checkArgument(view != null, "View should be set");
        String[] splitDate = getSplitDate();
        return splitDate[0];
    }

    @Override
    public String getYear() {
        checkArgument(view != null, "View should be set");
        String[] splitDate = getSplitDate();
        return splitDate.length == 2 ? fullyExpandYear(splitDate[1]) : "";
    }

    @Override
    public boolean validate() {
        checkArgument(view != null, "View should be set");

        cardDateValidable.setDate(getMonth(), getYear());
        boolean isDateValid = cardDateValidable.validate();
        view.setErrorState(!isDateValid);
        String error = !isDateValid ? translation.translate(TranslationKey.CARD_EXPIRATION_DATE_IS_INVALID) : "";
        view.setDateError(error);
        return isDateValid;
    }

    @Override
    public void setExpirationDate(int monthOfYear, int year) {
        checkArgument(view != null, "View should be set");
        view.setExpirationDate(monthOfYear, year);
    }

    @NonNull
    private String[] getSplitDate() {
        String date = view.getDate();
        return date.split("/");
    }

    private String fullyExpandYear(@Nullable String year) {
        if (year != null && year.length() == 2) {
            year = "20" + year;
        }
        return year;
    }

    private void showHideValidationError(boolean hasFocus) {
        if (hasFocus) {
            view.setErrorState(false);
            view.setDateError(null);
        } else {
            validate();
        }
    }
}
