package com.payu.android.front.sdk.payment_add_card_module.service;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.cvv.CvvDialogFragment;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.factory.CvvValidationDialogCreator;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationListener;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.events.PaymentAuthorization;

/**
 * Service used as an entry point for CVV validation during payment process in case of receiving WARNING_CONTINUE_CVV response code in
 * order create request
 */
public class CvvValidationService {

    /**
     * Starting point for CVV validation. Response would be provided in the {@linkplain CvvValidationListener#onValidationCompleted(CvvPaymentStatus)}
     *
     * @param fragmentManager       - needed for binding view
     * @param authorizationDetails  - AuthorizationDetails with {@linkplain PaymentAuthorization#CVV} authorizationType
     *                              and link obtained from orderCreateRequest(redirectUri field}.
     *                              Visit <a href=http://developers.payu.com/en/restapi.html#creating_new_order}>Rest Api docs</a> for more
     *                              information
     * @param cvvValidationListener - The Listener, which methods would be called with Cvv Payment status {@linkplain CvvPaymentStatus}
     */
    public static void validateCvv(
            @NonNull FragmentManager fragmentManager, @NonNull AuthorizationDetails authorizationDetails,
            @NonNull final CvvValidationListener cvvValidationListener) {
        DialogFragment materialDialog = CvvValidationDialogCreator.create(authorizationDetails, cvvValidationListener);
        materialDialog.show(fragmentManager, CvvDialogFragment.TAG);
    }
}
