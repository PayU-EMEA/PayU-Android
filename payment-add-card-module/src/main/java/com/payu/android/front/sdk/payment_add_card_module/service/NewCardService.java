package com.payu.android.front.sdk.payment_add_card_module.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.creator.CardServiceCreator;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.presenter.NewCardPresenter;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.CardInformation;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateRequest;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;

public class NewCardService implements InternalCardServiceTokenizer {

    private static final String CARD_TYPE = "STANDARD";
    private static final String REQUEST_TYPE = "TokenCreateRequest";
    private static final String CARD_STATUS = "ACTIVE";
    @NonNull
    private final NewCardView view;
    @NonNull
    private final NewCardPresenter presenter;
    @NonNull
    private final Context context;
    @NonNull
    private final CardService cardService;
    @NonNull
    private final Gson gson;
    @NonNull
    private final RetrofitNewCardCallback retrofitNewCardCallback;

    /**
     * @param view     {@linkplain NewCardView} customView for adding new card
     * @param context
     * @param callback {@linkplain NewCardCallback} will be invoked when adding a card to PayU backend process was finished
     */
    public static CardServiceTokenizer newInstance(@NonNull NewCardView view, @NonNull Context context, @NonNull NewCardCallback callback) {
        return new NewCardService(view, context, callback);
    }


    private NewCardService(@NonNull NewCardView view, @NonNull Context context, @NonNull NewCardCallback callback) {
        this(view, context, new NewCardPresenter(), CardServiceCreator.createCardService(context.getApplicationContext()), new Gson(), new RetrofitNewCardCallback(callback));
    }

    NewCardService(@NonNull NewCardView view, @NonNull Context context, @NonNull NewCardPresenter presenter, @NonNull CardService cardService, @NonNull Gson gson, @NonNull RetrofitNewCardCallback retrofitNewCardCallback) {
        this.view = view;
        this.context = context.getApplicationContext();
        this.presenter = presenter;
        this.cardService = cardService;
        this.gson = gson;
        this.presenter.takeView(view);
        this.retrofitNewCardCallback = retrofitNewCardCallback;
        this.retrofitNewCardCallback.setCardDataProviderListener(cardDataProvider);
    }

    @Override
    public boolean isCardValid() {
        return presenter.isCardValid();
    }

    @Override
    public void addCardWithAgreement(@NonNull String senderId) {
        if (presenter.isCardValid()) {
            makeRequest(true, senderId);
        }
    }


    @Override
    public void addCardWithoutAgreement(@NonNull String senderId) {
        if (presenter.isCardValid()) {
            makeRequest(false, senderId);
        }
    }

    @Override
    public boolean shouldHidePayUTerms(@NonNull String packageName) {
        return presenter.hidePayUTermsView(packageName);
    }

    @Nullable
    private String getIssuerLogoPath() {
        if (presenter.getCardIssuer() != CardIssuer.UNKNOWN) {
            return getImageUrl(presenter.getCardIssuer().getPath());
        }
        return null;
    }

    @NonNull
    private String getImageUrl(@NonNull String path) {
        return new StaticContentUrlProvider(ConfigurationEnvironmentProvider.provideEnvironment(context)).get(path);
    }


    private void makeRequest(boolean isAgreement, @NonNull String senderId) {
        Card card = presenter.getCardData();
        TokenCreateRequest request = prepareRequest(card, isAgreement, senderId, CARD_TYPE, REQUEST_TYPE);
        String jsonRequest = requestToJson(gson, request);
        cardService.addCard(jsonRequest).enqueue(retrofitNewCardCallback);
    }

    @NonNull
    private String requestToJson(@NonNull Gson gson, @NonNull TokenCreateRequest request) {
        return gson.toJson(request);
    }

    @NonNull
    private TokenCreateRequest prepareRequest(@NonNull Card card, boolean isAgreement, @NonNull String senderId, @NonNull String cardType, @NonNull String requestType) {
        CardInformation cardInformation = new CardInformation(card, cardType, isAgreement);
        TokenCreateRequest tokenCreateRequest = new TokenCreateRequest(senderId, requestType, cardInformation);

        return tokenCreateRequest;
    }


    @NonNull
    private CardDataProvider cardDataProvider = new CardDataProvider() {
        @Override
        public String getCardLogoPath() {
            return getIssuerLogoPath();
        }

        @Override
        public String getCardValidMonth() {
            return presenter.getCardValidMonth();
        }

        @Override
        public String getCardValidYear() {
            return presenter.getCardValidYear();
        }

        @Override
        public String getCardStatus() {
            return CARD_STATUS;
        }

        @Override
        public String getCardProviderName() {
            return presenter.getCardIssuer().getName();
        }

        @Override
        public boolean isPreferred() {
            return true;
        }
    };


    interface CardDataProvider {
        String getCardLogoPath();

        String getCardValidMonth();

        String getCardValidYear();

        String getCardStatus();

        String getCardProviderName();

        boolean isPreferred();

    }

}
