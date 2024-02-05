package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory;

public class RetrofitClientFactoryProducer {

    private static final long READ_TIMEOUT_MILLIS = 20000;
    private static final long CONNECT_TIMEOUT_MILLIS = 20000;

    public AbstractRetrofitClientFactory getFactory() {
        return new OkHttpClientFactory(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
    }
}
