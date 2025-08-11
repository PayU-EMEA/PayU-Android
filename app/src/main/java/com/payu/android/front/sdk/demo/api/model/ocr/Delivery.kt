package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class Delivery (
    @SerializedName("recipientName")
    val recipientName: String,
    @SerializedName("recipientEmail")
    val recipientEmail: String,
    @SerializedName("recipientPhone")
    val recipientPhone: String,
    @SerializedName("addressId")
    val addressId: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("postalBox")
    val postalBox: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("name")
    val name: String
)