package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;

public class CardScannerApiConfigurationStaticHolder {
    private static CardScannerApiConfigurationStaticHolder instance;

    private final DynamicCardActionDelegate dynamicCardActionDelegate;
    private CardScannerAPI cardScannerAPI;

    private CardScannerApiConfigurationStaticHolder(@NonNull DynamicCardActionDelegate dynamicCardActionDelegate
    ) {
        this.dynamicCardActionDelegate = dynamicCardActionDelegate;
    }

    public synchronized static CardScannerApiConfigurationStaticHolder getInstance(@NonNull DynamicCardActionDelegate dynamicCardActionDelegate) {
        if (instance == null) {
            instance = new CardScannerApiConfigurationStaticHolder(dynamicCardActionDelegate);
        }
        return instance;
    }

    public void setCardScannerAPI(@NonNull CardScannerAPI cardScannerAPI) {
        this.cardScannerAPI = cardScannerAPI;
    }

    @Nullable
    public CardScannerAPI getCardScannerAPI() {
        if (!dynamicCardActionDelegate.scanCardOption() || cardScannerAPI == null) {
            return null;
        }
        return cardScannerAPI;
    }
}
