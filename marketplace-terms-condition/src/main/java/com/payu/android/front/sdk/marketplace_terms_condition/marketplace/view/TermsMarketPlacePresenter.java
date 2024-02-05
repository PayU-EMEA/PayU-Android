package com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

public class TermsMarketPlacePresenter extends BasePresenter<TermsMarketPlaceView> implements SelectTermsPresenter {
    private SelectTerms selectTermsView;

    @Override
    public void takeView(@NonNull TermsMarketPlaceView view) {
        super.takeView(view);
        selectTermsView = view;
    }

    @Override
    public void setInformation(@NonNull String title, @NonNull String checkboxDescription, @NonNull String linkWithText) {
        selectTermsView.setInformation(title, checkboxDescription, linkWithText);
    }

    @Override
    public boolean isAgreementConfirmed() {
        return selectTermsView.isCheckBoxSet();
    }

    @Override
    public LiveData<Boolean> isAgreementSelected() {
        return selectTermsView.checkboxState();
    }
}
