package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory;

import android.os.Build;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslConfiguration;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;


class OkHttpClientFactory extends AbstractRetrofitClientFactory {
    private long connectionTimeout;
    private long readTimeout;

    public OkHttpClientFactory(long connectionTimeout, long readTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public OkHttpClient create(SslConfiguration sslConfiguration) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (sslConfiguration.isPinningEnabled()) {
            builder.hostnameVerifier(sslConfiguration.getHostnameVerifier());
        }
        return builder
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslConfiguration.getSslSocketFactory(), sslConfiguration.getTrustManager())
                .build();
    }
}