package com.payu.android.front.sdk.payment_installments.mastercard.offer.model

import android.text.Html
import android.text.Spanned
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentType

class InstallmentAdapterOptions(private val optionsId: String,
                                private val installmentValue: Float, numberOfInstallments: Int,
                                totalValue: Float, currency: String) :
        InstallmentAdapterCell(InstallmentType.OPTIONS, numberOfInstallments, totalValue, currency) {

    override fun formatTopBody(additionalText: String): Spanned {
        val formattedValue = formatValue(installmentValue)
        val sourceString = " <b>$formattedValue $currency</b>"
        return Html.fromHtml(additionalText + sourceString)
    }

    override fun getId(): String {
        return optionsId
    }

}