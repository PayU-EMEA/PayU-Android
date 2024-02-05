package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.factory;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.cvv.CvvDialogFragment;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationListener;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;

public class CvvValidationDialogCreator {
    public static DialogFragment create(
            @NonNull AuthorizationDetails authorizationDetails,
            @NonNull final CvvValidationListener cvvValidationListener) {

        final CvvDialogFragment dialog = CvvDialogFragment.newInstance(authorizationDetails);
        dialog.setValidationListener(cvvValidationListener);
        return dialog;
    }
}
