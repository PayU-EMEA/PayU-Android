package com.payu.android.front.sdk.payment_library_api_client.internal.rest.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory.AbstractRetrofitClientFactory;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory.RetrofitClientFactoryProducer;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.AndroidTrustManagerProvider;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslCertificate;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslConfiguration;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapterConfigurator {
    private static final String TAG = RestAdapterConfigurator.class.getSimpleName();
    private Context context;
    private AbstractRetrofitClientFactory retrofitClientFactory;

    public RestAdapterConfigurator(@NonNull Context context) {
        this.context = context;
        this.retrofitClientFactory = new RetrofitClientFactoryProducer().getFactory();
    }

    public Retrofit createCardBaseAdapter(@NonNull RestEnvironment restEnvironment) {
        OkHttpClient okHttpClient = getPreConfiguredClient(restEnvironment)
                .newBuilder()
                .addInterceptor(new SdkInfoHeaderInterceptor(context))
                .build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(restEnvironment.getCardEndpointUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit createExternalLinkNetworkBasedRestAdapter(@NonNull RestEnvironment environment, @NonNull String endpoint) {
        return new Retrofit.Builder()
                .client(getPreConfiguredClient(environment))
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getPreConfiguredClient(@NonNull RestEnvironment environment) {
        return retrofitClientFactory
                .create(getSslConfig(environment))
                .newBuilder()
                .addInterceptor(getLoggingInterceptor(environment))
                .build();
    }

    private SslConfiguration getSslConfig(@NonNull RestEnvironment environment) {
        return new SslConfiguration.Builder(createTrustManager())
                .addAcceptedHost(environment.getCardEndpointUrl())
                .withClientCertificate(environment.getClientKeyStore(context), environment.getClientKeyStorePassword())
                .withAllowedCertificates(environment.getAllowedCertificates().or(Collections.<SslCertificate>emptyList()))
                .build();
    }

    private X509TrustManager createTrustManager() {
        return new AndroidTrustManagerProvider().create().orNull();
    }

    @NotNull
    private HttpLoggingInterceptor getLoggingInterceptor(@NonNull RestEnvironment environment) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(environment.getLogLevel());
        return httpLoggingInterceptor;
    }
}
