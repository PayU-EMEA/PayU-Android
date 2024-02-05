package com.payu.android.front.sdk.demo.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.payu.android.front.sdk.demo.api.model.UnsafeOkHttpClient
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val SANDBOX = "sandbox"
private const val SANDBOX_PARTNERS = "sandbox-partners"

private const val BASE_URL_PROD = "https://secure.payu.com"
private const val BASE_URL_SANDBOX = "https://secure.snd.payu.com"
private const val BASE_URL_SANDBOX_PARTNERS = "https://secure.sndbeta.payu.com"

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient {
        val environment = ConfigurationDataProviderHolder.getInstance(context).environment

        val client = if (environment == SANDBOX_PARTNERS) {
            UnsafeOkHttpClient.unsafeOkHttpClient
        } else {
            OkHttpClient.Builder()
        }
        return client.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
                .followRedirects(false)
                .followSslRedirects(false)
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(context: Context, client: OkHttpClient, gson: Gson): Retrofit {
        val baseUrl = when (ConfigurationDataProviderHolder.getInstance(context).environment) {
            SANDBOX -> BASE_URL_SANDBOX
            SANDBOX_PARTNERS -> BASE_URL_SANDBOX_PARTNERS
            else -> BASE_URL_PROD
        }

        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providePayUApi(retrofit: Retrofit): PayUApi = retrofit.create(PayUApi::class.java)
}