package com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public interface SelectTermsPresenter {
    void setInformation(@NonNull String title, @NonNull String checkboxDescription, @NonNull String linkWithText);

    boolean isAgreementConfirmed();

    LiveData<Boolean> isAgreementSelected();
}
