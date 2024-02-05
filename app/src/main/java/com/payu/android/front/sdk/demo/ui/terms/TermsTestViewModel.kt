package com.payu.android.front.sdk.demo.ui.terms

import com.payu.android.front.sdk.demo.ui.base.BaseViewModel
import com.payu.android.front.sdk.demo.ui.base.SingleLiveEvent
import com.payu.android.front.sdk.demo.ui.base.binding.ObservableString

class TermsTestViewModel : BaseViewModel() {
    val verificationId = ObservableString("5002500000EcJHdAAN")
    val baseUrl = ObservableString("https://secure.snd.payu.com")
    val callEvent = SingleLiveEvent<Boolean>()
    fun makeTermsAndConditionCall() {
        callEvent.value = true
    }
}