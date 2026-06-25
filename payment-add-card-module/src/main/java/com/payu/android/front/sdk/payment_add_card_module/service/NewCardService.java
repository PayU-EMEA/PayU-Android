package com.payu.android.front.sdk.payment_add_card_module.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_add_card_module.creator.CardServiceCreator;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.presenter.NewCardPresenter;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_api_client.BuildConfig;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenType;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateRequest;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;

public class NewCardService implements InternalCardServiceTokenizer {
    private static final String QUERY_FROM_VALUE = "mobilesdk";
    private static final String QUERY_SENDER_VALUE = "android";
    private static final String CARD_STATUS = "ACTIVE";

    @NonNull
    private final NewCardPresenter presenter;
    @NonNull
    private final Context context;
    @NonNull
    private final CardService cardService;
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
        this(view, context, new NewCardPresenter(), CardServiceCreator.createCardService(context.getApplicationContext()), new RetrofitNewCardCallback(callback));
    }

    NewCardService(@NonNull NewCardView view, @NonNull Context context, @NonNull NewCardPresenter presenter, @NonNull CardService cardService, @NonNull RetrofitNewCardCallback retrofitNewCardCallback) {
        this.context = context.getApplicationContext();
        this.presenter = presenter;
        this.cardService = cardService;
        this.presenter.takeView(view);
        this.retrofitNewCardCallback = retrofitNewCardCallback;
        this.retrofitNewCardCallback.setCardDataProviderListener(cardDataProvider);
    }

    @Override
    public boolean isCardValid() {
        return presenter.isCardValid();
    }

    @Override
    public void addCard(@NonNull String senderId, @NonNull TokenType type) {
        if (presenter.isCardValid()) {
            makeRequest(type, senderId);
        }
    }

    @Deprecated
    @Override
    public void addCardWithAgreement(@NonNull String senderId) {
        if (presenter.isCardValid()) {
            makeRequest(TokenType.MULTI, senderId);
        }
    }

    @Deprecated
    @Override
    public void addCardWithoutAgreement(@NonNull String senderId) {
        if (presenter.isCardValid()) {
            makeRequest(TokenType.SINGLE, senderId);
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


    private void makeRequest(@NonNull TokenType type, @NonNull String senderId) {
        Card card = presenter.getCardData();
        TokenCreateRequest request = new TokenCreateRequest(senderId, type, card);
        cardService.addCard(
                QUERY_FROM_VALUE,
                QUERY_SENDER_VALUE,
                BuildConfig.LIBRARY_VERSION_NAME,
                request
        ).enqueue(retrofitNewCardCallback);
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
