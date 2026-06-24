package com.payu.android.front.sdk.payment_library_api_client.internal.rest.service;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateRequest;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface CardService {
    @POST("/api/front/tokens")
    Call<TokenCreateResponse> addCard(@Body TokenCreateRequest requestModel);
}
