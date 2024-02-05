package com.payu.android.front.sdk.payment_library_api_client.internal.rest.service;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface CardService {
    @FormUrlEncoded
    @POST("/api/v2/token/token.json")
    Call<TokenCreateResponse> addCard(@Field("data") String requestModel);
}
