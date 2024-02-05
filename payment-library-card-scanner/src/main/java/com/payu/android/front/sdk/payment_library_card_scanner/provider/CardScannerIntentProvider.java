package com.payu.android.front.sdk.payment_library_card_scanner.provider;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import cards.pay.paycardsrecognizer.sdk.ScanCardIntent;

public final class CardScannerIntentProvider {
    private CardScannerIntentProvider() {
    }

    public static Intent getCardScannerIntent(@NonNull Context context, boolean shouldScanDate) {
        return new ScanCardIntent
                .Builder(context)
                .setScanCardHolder(false)
                .setScanExpirationDate(shouldScanDate)
                .build();
    }
}
