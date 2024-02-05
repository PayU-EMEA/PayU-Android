package com.payu.android.front.sdk.marketplace_terms_condition.marketplace

interface TermsAgreementResponseListener {
    fun onSuccess()
    fun onError(t: Throwable)
}