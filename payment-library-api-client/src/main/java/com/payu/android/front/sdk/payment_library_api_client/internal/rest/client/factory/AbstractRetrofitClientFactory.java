package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslConfiguration;

import okhttp3.OkHttpClient;

public abstract class AbstractRetrofitClientFactory {

    public abstract OkHttpClient create(SslConfiguration pinningConfiguration);
}