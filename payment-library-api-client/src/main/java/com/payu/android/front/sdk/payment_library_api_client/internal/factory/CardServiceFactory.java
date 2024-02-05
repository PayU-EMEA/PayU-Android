package com.payu.android.front.sdk.payment_library_api_client.internal.factory;

import android.content.Context;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;


public final class CardServiceFactory {

    private static CardService instance;

    private CardServiceFactory() {
    }

    public static CardService getInstance(Context context, RestEnvironment environment) {
        if (instance == null) {
            instance = NetworkServiceFactory.getInstance(context, environment).produceFactory(environment).getCardService();
        }
        return instance;
    }
}