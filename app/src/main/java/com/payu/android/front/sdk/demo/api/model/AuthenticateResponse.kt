package com.payu.android.front.sdk.demo.api.model

data class AuthenticateResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val grant_type: String
)