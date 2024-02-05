package com.payu.android.front.sdk.payment_library_api_client.internal.factory;

import android.content.Context;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.configurator.NetworkCvvRestServiceConfigurator;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;

public class CvvServiceFactory {

    private static NetworkCvvRestServiceConfigurator instance;

    private CvvServiceFactory() {
    }


    public static NetworkCvvRestServiceConfigurator getInstance(Context context, RestEnvironment environment) {
        if (instance == null) {
            instance = NetworkServiceFactory.getInstance(context, environment).getCvvRestServiceBuilder();
        }
        return instance;
    }
}

