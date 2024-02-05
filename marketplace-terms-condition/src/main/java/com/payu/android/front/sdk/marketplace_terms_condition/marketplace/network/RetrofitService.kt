package com.payu.android.front.sdk.marketplace_terms_condition.marketplace.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {
    fun createRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(retrofit: Retrofit): TermsAndConditionService {
        return retrofit.create(TermsAndConditionService::class.java)
    }

}