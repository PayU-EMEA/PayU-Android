package com.payu.android.front.sdk.marketplace_terms_condition.marketplace.network

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TermsAndConditionService {
    @POST("/v1/public/verifications/{VERIFICATION_ID}/regulations/acceptance")
    fun approveRegulationService(
        @Header("payu-verificationid") verificationHead: String,
        @Path("VERIFICATION_ID") verificationId: String
    ): Call<Unit>
}
