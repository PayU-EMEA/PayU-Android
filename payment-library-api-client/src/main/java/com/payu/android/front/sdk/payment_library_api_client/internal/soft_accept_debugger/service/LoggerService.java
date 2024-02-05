package com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.service;

import com.payu.android.front.sdk.payment_library_api_client.internal.soft_accept_debugger.model.LoggerInformation;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoggerService {
    @Headers({"Content-Type:application/vnd.payu+json"})
    @POST("/front/logger")
    Call<Unit> sendInformation(@Body LoggerInformation body);
}
