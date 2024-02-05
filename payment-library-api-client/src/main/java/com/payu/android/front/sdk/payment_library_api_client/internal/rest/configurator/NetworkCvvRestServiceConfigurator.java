package com.payu.android.front.sdk.payment_library_api_client.internal.rest.configurator;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter.ExternalEndpointRestAdapterBuilder;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;

public class NetworkCvvRestServiceConfigurator {
    private ExternalEndpointRestAdapterBuilder mAdapter;

    public NetworkCvvRestServiceConfigurator(@NonNull ExternalEndpointRestAdapterBuilder adapter) {
        mAdapter = adapter;
    }

    public CvvRestService withProtocolAndHost(@NonNull String cvvProtocolAndHost) {
        return mAdapter
                .withProtocolAndHost(cvvProtocolAndHost)
                .build(CvvRestService.class);
    }
}
