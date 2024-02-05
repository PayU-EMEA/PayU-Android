package com.payu.android.front.sdk.payment_library_google_pay_module.listener;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_google_pay_module.service.GooglePayService;

/**
 * This listener should be implemented by merchant and should be passed to {@linkplain GooglePayService#isReadyToPay(GooglePayVerificationListener)}
 * For more information check:  <a href=" https://developers.google.com/pay/api/android/guides/tutorial">Google Pay documentation isReadyToPay API</a>
 */
public interface GooglePayVerificationListener {
    /**
     * Method called when Google Pay status has been obtained
     *
     * @param verificationStatus - The status of the verification. Google Pay payments should be performed only on
     * {@linkplain GooglePayVerificationStatus#SUCCESS}
     */
    void onVerificationCompleted(GooglePayVerificationStatus verificationStatus);

    /**
     * There was an exception when trying to connect to Google API
     */
    void onException(@NonNull Exception exception);
}
