package com.payu.android.front.sdk.payment_installments.mastercard.offer.model

import android.text.Spanned
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentType
import java.text.DecimalFormat

abstract class InstallmentAdapterCell(val installmentType: InstallmentType, val numberOfInstallments: Int,
                                      val totalValue: Float, val currency: String) {
    private val patternDisplayValue = "#0.00"
    private val singularInstallmentValue = 1
    private val pluralInstallmentValue = 4

    abstract fun formatTopBody(additionalText: String): Spanned
    abstract fun getId(): String

    fun formatSubtitle(singleInstallment: String, pluralInstallment: String, otherInstallment: String): String = when {
        numberOfInstallments == singularInstallmentValue -> "$numberOfInstallments $singleInstallment"
        numberOfInstallments <= pluralInstallmentValue -> "$numberOfInstallments $pluralInstallment"
        else -> "$numberOfInstallments $otherInstallment"

    }

    fun formatLowerBody(leadingText: String): String =
            leadingText + " " + formatValue(totalValue) + " " + currency


    protected fun formatValue(value: Float): String = DecimalFormat(patternDisplayValue).format(value)
}