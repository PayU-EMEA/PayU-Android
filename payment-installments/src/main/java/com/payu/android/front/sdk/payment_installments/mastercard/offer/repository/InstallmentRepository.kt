package com.payu.android.front.sdk.payment_installments.mastercard.offer.repository

import androidx.lifecycle.MutableLiveData
import com.payu.android.front.sdk.payment_library_core.external.listener.InstallmentCallback
import com.payu.android.front.sdk.payment_library_core.external.model.Installment
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions

class InstallmentRepository(private val paymentMethodActions: PaymentMethodActions) : InstallmentCallback {
    private val localInstallment = MutableLiveData<Installment>()
    override fun onFetched(installment: Installment?) {
        localInstallment.postValue(installment)
    }

    fun getInstallment(): MutableLiveData<Installment> {
        if (localInstallment.value == null) {
            paymentMethodActions.provideInstallments(this)
        }
        return localInstallment
    }
}