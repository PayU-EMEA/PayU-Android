package com.payu.android.front.sdk.payment_add_card_module.creator;

import android.content.Context;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.factory.CardServiceFactory;
import com.payu.android.front.sdk.payment_library_api_client.internal.factory.CvvServiceFactory;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;


public final class CardServiceCreator {
    private CardServiceCreator() {
    }

    @NonNull
    public static CardService createCardService(@NonNull Context context) {
        return CardServiceFactory.getInstance(context.getApplicationContext(), getCurrentRestEnvironment(context.getApplicationContext()));
    }

    @NonNull
    public static CvvRestService createCvvRestService(@NonNull Context context, @NonNull String cvvProtocolAndHost) {
        return CvvServiceFactory.getInstance(context.getApplicationContext(), getCurrentRestEnvironment(context.getApplicationContext())).withProtocolAndHost(cvvProtocolAndHost);
    }

    private static RestEnvironment getCurrentRestEnvironment(@NonNull Context context) {
        return ConfigurationEnvironmentProvider.provideEnvironment(context);
    }
}
