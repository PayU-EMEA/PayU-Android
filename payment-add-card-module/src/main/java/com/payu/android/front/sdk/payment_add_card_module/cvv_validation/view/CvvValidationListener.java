package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;

/**
 * Listener used for communication of Cvv Payment results
 */
public interface CvvValidationListener {

    /**
     * This method is called when Cvv Payment has ended
     *
     * @param cvvPaymentStatus - Cvv Payment status. For the available statuses, please look at {@linkplain CvvPaymentStatus}
     */
    void onValidationCompleted(@NonNull CvvPaymentStatus cvvPaymentStatus);
}
