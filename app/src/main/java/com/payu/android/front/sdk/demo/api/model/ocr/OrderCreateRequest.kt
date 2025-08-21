package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class CreateOrderRequest(
    @SerializedName("continueUrl")
    val continueUrl: String,
    @SerializedName("buyer")
    val buyer: Buyer,
    @SerializedName("currencyCode")
    val currencyCode: String,
    @SerializedName("customerIp")
    val customerIp: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("merchantPosId")
    val merchantPosId: String,
    @SerializedName("notifyUrl")
    val notifyUrl: String,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("totalAmount")
    val totalAmount: String,
    @SerializedName("payMethods")
    val payMethods: PayMethods,
)