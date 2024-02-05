package com.payu.android.front.sdk.payment_add_card_module.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.extraction.CardDateExtractor;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.OnCardIssuerChangedListener;
import com.payu.android.front.sdk.payment_add_card_module.validation.CardDateValidable;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.util.time.ActualTimeProvider;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_core_android.util.PackageName;

public class NewCardPresenter extends BasePresenter<NewCardView> {
    //MasterPresenter for Adding new Card
    private CardSelector cardIssuerPresenter;
    private CardNumberPresenter numberPresenter;
    private CvvPresenter cvvPresenter;
    private CardDatePresenter datePresenter;

    private CardIssuer cardIssuer;

    public NewCardPresenter() {
        this.cardIssuerPresenter = new CardSelectorPresenter();
        this.numberPresenter = new CardNumberPresenter(onCardIssuerChangedListener);
        this.cvvPresenter = new CardCvvPresenter();
        this.datePresenter = new CardDatePresenter(new CardDateValidable(new CardDateExtractor(), ActualTimeProvider.getInstance()), TranslationFactory.getInstance());
        this.cardIssuer = CardIssuer.UNKNOWN;
    }


    @Override
    public void takeView(@NonNull NewCardView view) {
        super.takeView(view);
        cardIssuerPresenter.takeView(view.getSelectorView());
        numberPresenter.takeView(view.getCardNumberView());
        cvvPresenter.takeView(view.getCardCvvView());
        datePresenter.takeView(view.getCardDateView());
    }

    private OnCardIssuerChangedListener onCardIssuerChangedListener = new OnCardIssuerChangedListener() {
        @Override
        public void onCardIssuerChanged(CardIssuer newCardProvider) {
            cardIssuerPresenter.selectIssuer(newCardProvider);
            cardIssuer = newCardProvider;
        }
    };

    public boolean isCardValid() {
        boolean isValidNumber = numberPresenter.validate();
        boolean isValidDate = datePresenter.validate();
        boolean isValidCvv = cvvPresenter.validate();
        return isValidNumber && isValidDate && isValidCvv;
    }

    public String getCardValidMonth() {
        return datePresenter.getMonth();
    }

    public String getCardValidYear() {
        return datePresenter.getYear();
    }

    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    public Card getCardData() {
        String cardNumber = numberPresenter.getCardNumber();
        String cardMonth = datePresenter.getMonth();
        String cardYear = datePresenter.getYear();
        String cardCvv = cvvPresenter.getCvvCode();
        return new Card(cardNumber, cardCvv, cardMonth, cardYear);
    }

    public boolean hidePayUTermsView(String classPackage) {
        if (classPackage.contains(PackageName.LIBRARY_PACKAGE_NAME)) {
            view.hidePayUTermsView();
            return true;
        }
        return false;
    }
}
