package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class Buyer(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("delivery")
    val delivery: Delivery
)