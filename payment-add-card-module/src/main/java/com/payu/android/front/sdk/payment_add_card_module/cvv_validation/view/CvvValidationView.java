package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;

public interface CvvValidationView  {
    void onValidationCompleted(@NonNull CvvPaymentStatus paymentStatus);

    void setViewLoading(boolean isLoading);
}
