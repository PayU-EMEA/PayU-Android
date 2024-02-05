package com.payu.android.front.sdk.payment_library_api_client.internal.rest.configurator;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;

import retrofit2.Retrofit;

public class NetworkCardServiceConfigurator {
    @NonNull
    private final Retrofit cardRestAdapter;


    public NetworkCardServiceConfigurator(@NonNull Retrofit cardRestAdapter) {
        this.cardRestAdapter = cardRestAdapter;
    }


    public CardService getCardService() {
        return cardRestAdapter.create(CardService.class);
    }
}
