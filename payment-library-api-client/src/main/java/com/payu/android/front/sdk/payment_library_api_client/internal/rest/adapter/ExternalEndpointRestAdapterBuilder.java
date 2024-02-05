package com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import static com.google.common.base.Preconditions.checkNotNull;


public class ExternalEndpointRestAdapterBuilder {

    private RestAdapterConfigurator restAdapterConfigurator;
    private String protocolAndHost;
    private RestEnvironment restEnvironment;

    public ExternalEndpointRestAdapterBuilder(@NonNull RestAdapterConfigurator restAdapterConfigurator, @NonNull RestEnvironment restEnvironment) {
        this.restAdapterConfigurator = restAdapterConfigurator;
        this.restEnvironment = restEnvironment;
    }

    public <T> T build(Class<T> clazz) {
        checkNotNull(protocolAndHost, "ProtocolAndHost must be set");
        return restAdapterConfigurator.createExternalLinkNetworkBasedRestAdapter(restEnvironment, protocolAndHost)
                .create(clazz);
    }

    public ExternalEndpointRestAdapterBuilder withProtocolAndHost(@NonNull String protocolAndHost) {
        this.protocolAndHost = protocolAndHost;
        return this;
    }
}
