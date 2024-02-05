package com.payu.android.front.sdk.payment_library_api_client.internal.factory;

import android.content.Context;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter.ExternalEndpointRestAdapterBuilder;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter.RestAdapterConfigurator;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import static com.payu.android.front.sdk.payment_library_core.util.ProviderWrapper.wrapInProvider;

public class NetworkServiceFactory {

    private static RestServiceFactoryProducer instance;

    private NetworkServiceFactory() {
    }


    public static RestServiceFactoryProducer getInstance(final Context context, final RestEnvironment environment) {
        if (instance == null) {
            instance = createNetworkServiceFactory(context, environment);
        }
        return instance;
    }

    private static RestServiceFactoryProducer createNetworkServiceFactory(final Context context, final RestEnvironment environment) {
        RestAdapterConfigurator restAdapterConfigurator = new RestAdapterConfigurator(context.getApplicationContext());
        RestServiceFactoryProducer restServiceFactoryProducer = new RestServiceFactoryProducer(restAdapterConfigurator, wrapInProvider(new ExternalEndpointRestAdapterBuilder(restAdapterConfigurator, environment)));

        return restServiceFactoryProducer;
    }
}
