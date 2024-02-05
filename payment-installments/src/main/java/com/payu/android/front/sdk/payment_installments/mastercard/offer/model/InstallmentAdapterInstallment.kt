package com.payu.android.front.sdk.payment_installments.mastercard.offer.model

import android.text.Html
import android.text.Spanned
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentType

class InstallmentAdapterInstallment(private val interestRate: Float, numberOfInstallments: Int, totalValue: Float, currency: String) :
        InstallmentAdapterCell(InstallmentType.INSTALLMENT, numberOfInstallments, totalValue, currency) {

    override fun formatTopBody(additionalText: String): Spanned {
        val formattedValue = formatValue(interestRate)
        val sourceString = " <b>$formattedValue%</b>"
        return Html.fromHtml(additionalText + sourceString)
    }

    override fun getId(): String {
        return numberOfInstallments.toString()
    }
}