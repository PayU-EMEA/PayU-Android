package com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel

import androidx.lifecycle.ViewModel
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer
import com.payu.android.front.sdk.payment_installments.mastercard.offer.event.SingleLiveEvent
import com.payu.android.front.sdk.payment_installments.mastercard.offer.repository.InstallmentRepository
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider
import com.payu.android.front.sdk.payment_library_core.translation.Translation
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey

class ChooseInstallmentsViewModel(var staticContentProvider: StaticContentUrlProvider, var cardIssuer: CardIssuer,
                                  var translation: Translation,
                                  installmentRepository: InstallmentRepository) : ViewModel() {

    val onClickEvent = SingleLiveEvent<Unit>()
    val installment = installmentRepository.getInstallment()

    fun provideTranslationForSubTitle(): String = translation.translate(TranslationKey.CHOOSE_INSTALLMENTS_SUBTITLE)
    fun provideTranslationForButtonNegative(): String = translation.translate(TranslationKey.CHOOSE_INSTALLMENTS_BUTTON_NEGATIVE)
    fun provideTranslationForHeader(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_HEADER)

    fun provideImagePath(): String = staticContentProvider.get(cardIssuer.path)


    fun onDeclineInstallments() {
        onClickEvent.value = Unit
    }

}