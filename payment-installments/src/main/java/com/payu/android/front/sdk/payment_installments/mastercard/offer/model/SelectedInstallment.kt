package com.payu.android.front.sdk.payment_installments.mastercard.offer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SelectedInstallment(val id: String, val installmentType: String, val proposalId: String?) : Parcelable