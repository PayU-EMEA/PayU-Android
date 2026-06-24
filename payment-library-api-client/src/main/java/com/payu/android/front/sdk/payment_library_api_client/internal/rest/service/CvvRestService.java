package com.payu.android.front.sdk.payment_library_api_client.internal.rest.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface CvvRestService {
    @POST("/api/front/card-authorizations/{refReqId}/cvv")
    Call<Void> sendCvv(@Path("refReqId") String refReqId, @Body String cvv);

}
