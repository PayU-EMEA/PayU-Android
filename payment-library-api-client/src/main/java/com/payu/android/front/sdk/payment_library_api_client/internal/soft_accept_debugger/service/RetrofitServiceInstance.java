package com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceInstance {
    protected RetrofitServiceInstance() {
    }

    public static Retrofit createRetrofitInstance(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static LoggerService createService(Retrofit retrofit) {
        return retrofit.create(LoggerService.class);
    }

}
