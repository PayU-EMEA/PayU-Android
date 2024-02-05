package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.ScannedCard;

/**
 * This interface should be implement in order to handle Card Scanner actions on
 * {@linkplain com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card.CreateAndSelectCardActivity}
 */
public interface CardScannerAPI {

    /**
     * Create an intent for handling 3rd party card scanner
     *
     * @param context - activity context for opening new Activity
     * @return intent that will open Camera or Card Scanner to scan
     * card number and date
     */
    Intent createScannerIntent(@NonNull Context context);

    /**
     * Create an intent for handling 3rd party card scanner
     *
     * @param context - activity context for opening new Activity
     * @param shouldScanCardDate  - if card Date should be scanned too
     * @return intent that will open Camera or Card Scanner
     */
    Intent createScannerIntent(@NonNull Context context, boolean shouldScanCardDate);

    /**
     * Request Code that identify from which activity intent came back
     * in onActivityResult
     *
     * @return requestCode
     */
    int createCardScannerRequestCode();

    /**
     * Create a model from retrieved information from Card Scanner
     *
     * @param data - received from 3rd party scanner with information about scanned card
     * @return cardModel with base information about card
     */
    ScannedCard createCardModel(@NonNull Intent data);
}
