package com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel

import androidx.lifecycle.ViewModel
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer
import com.payu.android.front.sdk.payment_installments.mastercard.offer.event.SingleLiveEvent
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider
import com.payu.android.front.sdk.payment_library_core.translation.Translation
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey

class OfferInstallmentsViewModel(var staticContentProvider: StaticContentUrlProvider,
                                 var cardIssuer: CardIssuer,
                                 var translation: Translation) : ViewModel() {

    val onClickEvent = SingleLiveEvent<Boolean>()

    fun provideTranslationForTitle(): String =
            translation.translate(TranslationKey.OFFER_INSTALLMENTS_TITLE)


    fun provideTranslationForSubTitle(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_SUBTITLE)
    fun provideTranslationForBody(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_BODY)
    fun provideTranslationForButtonPositive(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_BUTTON_ACCEPT)
    fun provideTranslationForButtonNegative(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_BUTTON_NEGATIVE)
    fun provideTranslationForHeader(): String = translation.translate(TranslationKey.OFFER_INSTALLMENTS_HEADER)

    fun provideImagePath(): String = staticContentProvider.get(cardIssuer.path)


    fun onAcceptInstallments() {
        onClickEvent.value = true
    }

    fun onDeclineInstallments() {
        onClickEvent.value = false
    }

}