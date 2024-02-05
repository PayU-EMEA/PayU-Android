package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core

fun retrieveAuthenticationId(url: String): String {
    val authenticationId =
        url.split(" ").first {
            it.contains("authenticationId=")
        }
            .substringAfter("authenticationId=")
            .substringBefore("&refReqId")
    return authenticationId
}