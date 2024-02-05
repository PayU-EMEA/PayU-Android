package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.cvv;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationLayout;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationListener;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.styles.PayUDefaultDialogBuilder;

public class CvvDialogFragment extends DialogFragment {
    public static final String TAG = CvvDialogFragment.class.toString();

    public static final String KEY_AUTHORIZATION_DETAILS = "CvvDialogFragment.AuthorizationDetails";
    private AuthorizationDetails authorizationDetails;
    private CvvValidationListener cvvValidationListener;

    public static CvvDialogFragment newInstance(@NonNull AuthorizationDetails authorizationDetails) {
        CvvDialogFragment cvvDialogFragment = new CvvDialogFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_AUTHORIZATION_DETAILS, authorizationDetails);

        cvvDialogFragment.setArguments(args);

        return cvvDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.authorizationDetails = getArguments().getParcelable(KEY_AUTHORIZATION_DETAILS);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final CvvValidationLayout cvvValidationLayout = new CvvValidationLayout(getContext(), authorizationDetails);
        final Translation translation = TranslationFactory.getInstance();

        final AlertDialog dialog = createCvvDialog(getActivity(), cvvValidationLayout, translation);
        cvvValidationLayout.setListener(new CvvValidationListener() {
            @Override
            public void onValidationCompleted(@NonNull CvvPaymentStatus cvvPaymentStatus) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (cvvValidationListener != null) {
                    cvvValidationListener.onValidationCompleted(cvvPaymentStatus);
                }
            }
        });
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void setValidationListener(@NonNull final CvvValidationListener cvvValidationListener) {
        this.cvvValidationListener = cvvValidationListener;
    }

    private AlertDialog createCvvDialog(@NonNull Activity activity, final CvvValidationLayout cvvValidationLayout, Translation translation) {
        final AlertDialog alertDialog = new PayUDefaultDialogBuilder(activity)
                .setTitle(translation.translate(TranslationKey.ENTER_CVV2))
                .setView(cvvValidationLayout)
                .setCancelable(false)
                .setPositiveButton(translation.translate(TranslationKey.OK), null)
                .setNegativeButton(translation.translate(TranslationKey.CANCEL), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cvvValidationLayout.onCancelClick();
                    }
                })
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        cvvValidationLayout.onAcceptClick();
                    }
                });
            }
        });
        return alertDialog;
    }
}
