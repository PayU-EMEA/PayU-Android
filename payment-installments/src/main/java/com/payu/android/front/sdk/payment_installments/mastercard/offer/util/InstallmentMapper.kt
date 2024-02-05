import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.InstallmentAdapterCell
import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.InstallmentAdapterInstallment
import com.payu.android.front.sdk.payment_installments.mastercard.offer.model.InstallmentAdapterOptions
import com.payu.android.front.sdk.payment_library_core.external.model.CurrencyHelper
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentType

//TODO: documentation
fun mapDataFromApp(installment: com.payu.android.front.sdk.payment_library_core.external.model.Installment): List<InstallmentAdapterCell> {
    val installmentList = arrayListOf<InstallmentAdapterCell>()

    installment.installmentOptionList.forEach { item ->
        if (installment.type == InstallmentType.OPTIONS) {
            installmentList.add(InstallmentAdapterOptions(item.id, divideCurrency(item.firstInstallmentValue),
                    item.numberOfInstallments,
                    divideCurrency(item.totalValue),
                    CurrencyHelper.getCurrencySymbol(installment.currency)))
        } else {
            for (numberOfInstallment in installment.minNumberOfInstallments..installment.maxNumberOfInstallments) {
                installmentList.add(InstallmentAdapterInstallment(item.interestRate, numberOfInstallment,
                        divideCurrency(item.totalValue), CurrencyHelper.getCurrencySymbol(installment.currency)))
            }
        }
    }
    return installmentList
}

private fun divideCurrency(value: Float): Float {
    return value / 100.00f;
}