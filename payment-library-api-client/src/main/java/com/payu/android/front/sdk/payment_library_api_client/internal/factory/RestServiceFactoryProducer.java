package com.payu.android.front.sdk.payment_library_api_client.internal.factory;


import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter.ExternalEndpointRestAdapterBuilder;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter.RestAdapterConfigurator;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.configurator.NetworkCardServiceConfigurator;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.configurator.NetworkCvvRestServiceConfigurator;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core.util.Provider;

import retrofit2.Retrofit;

public class RestServiceFactoryProducer {

    private RestAdapterConfigurator restAdapterConfigurator;
    private Provider<ExternalEndpointRestAdapterBuilder> externalLinkRestAdapterProvider;

    public RestServiceFactoryProducer(@NonNull RestAdapterConfigurator restAdapterConfigurator, @NonNull Provider<ExternalEndpointRestAdapterBuilder> externalLinkRestAdapterProvider) {
        this.restAdapterConfigurator = restAdapterConfigurator;
        this.externalLinkRestAdapterProvider = externalLinkRestAdapterProvider;
    }

    public NetworkCardServiceConfigurator produceFactory(@NonNull RestEnvironment environment) {
        return createNetworkServiceFactory(environment);
    }

    public NetworkCvvRestServiceConfigurator getCvvRestServiceBuilder() {
        return new NetworkCvvRestServiceConfigurator(externalLinkRestAdapterProvider.get());
    }

    private NetworkCardServiceConfigurator createNetworkServiceFactory(final RestEnvironment environment) {
        Retrofit cardRestAdapter = restAdapterConfigurator.createCardBaseAdapter(environment);
        return new NetworkCardServiceConfigurator(cardRestAdapter);
    }

}
