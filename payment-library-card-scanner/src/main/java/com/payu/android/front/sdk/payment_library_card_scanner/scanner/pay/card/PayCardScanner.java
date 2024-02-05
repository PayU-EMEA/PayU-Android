package com.payu.android.front.sdk.payment_library_card_scanner.scanner.pay.card;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_card_scanner.provider.CardScannerIntentProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.ScannedCard;

import cards.pay.paycardsrecognizer.sdk.Card;
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent;

public class PayCardScanner implements CardScannerAPI {
    private static final int CARD_SCAN_REQUEST_CODE = 1236;

    @Override
    public Intent createScannerIntent(@NonNull Context context) {
        return createScannerIntent(context, true);
    }

    @Override
    public Intent createScannerIntent(@NonNull Context context, boolean shouldScanCardDate) {
        return CardScannerIntentProvider.getCardScannerIntent(context, shouldScanCardDate);
    }

    @Override
    public int createCardScannerRequestCode() {
        return CARD_SCAN_REQUEST_CODE;
    }

    @Override
    public ScannedCard createCardModel(@NonNull Intent data) {
        if (!data.getExtras().containsKey(ScanCardIntent.RESULT_PAYCARDS_CARD)) {
            return null;
        }
        Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);
        return new ScannedCard(card.getCardNumber(), card.getExpirationDate());
    }
}
