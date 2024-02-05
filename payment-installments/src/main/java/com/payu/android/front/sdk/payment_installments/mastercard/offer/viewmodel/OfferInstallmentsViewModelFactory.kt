package com.payu.android.front.sdk.payment_installments.mastercard.offer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.content.StaticContentUrlProvider
import com.payu.android.front.sdk.payment_library_core.translation.Translation
class OfferInstallmentsViewModelFactory(var staticContentProvider: StaticContentUrlProvider, var cardIssuer: CardIssuer,
                                        var translation: Translation) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return OfferInstallmentsViewModel(staticContentProvider, cardIssuer, translation) as T
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OfferInstallmentsViewModel(staticContentProvider, cardIssuer, translation) as T
    }
}