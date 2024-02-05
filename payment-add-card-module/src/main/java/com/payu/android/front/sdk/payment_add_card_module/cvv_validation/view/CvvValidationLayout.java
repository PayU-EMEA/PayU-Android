package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_add_card_module.creator.CardServiceCreator;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.presenter.CvvValidationPresenter;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_add_card_module.validation.cvv.GenericCvvValidator;
import com.payu.android.front.sdk.payment_add_card_module.view.CardCvvView;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.styles.EditTextStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;

public class CvvValidationLayout extends FrameLayout implements CvvValidationView {
    @NonNull
    private final CardCvvView cardCvvView;
    private final View progressView;
    private CvvValidationListener listener;
    @NonNull
    private CvvValidationPresenter cvvValidationPresenter;

    public CvvValidationLayout(
            @NonNull Context context, @NonNull AuthorizationDetails authorizationDetails) {
        super(context);
        inflate(context, R.layout.payu_view_cvv_calidation, this);
        this.cardCvvView = findViewById(R.id.cvv_validation_view);
        this.progressView = findViewById(R.id.cvv_loading);
        setupPresenter(authorizationDetails);
        setupStyles();
        showKeyboard();
    }

    private void showKeyboard() {
        post(new Runnable() {
            @Override
            public void run() {
                if (cardCvvView.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager)
                            getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(cardCvvView.getEditText(), InputMethodManager.SHOW_IMPLICIT);
                    }
                }
            }
        });
    }

    public void setListener(@NonNull CvvValidationListener listener) {
        this.listener = listener;
    }

    private void setupStyles() {
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());
        createEditTextStaleFromInfo(applicationStyleInfo.getTextStyleInput()).applyTo(cardCvvView.getEditText());
        int windowContentPadding = (int) applicationStyleInfo.getWindowContentPadding();

        cardCvvView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);

    }

    private void setupPresenter(@NonNull AuthorizationDetails authorizationDetails) {
        RedirectLinkParser validationLinkParser = new RedirectLinkParser(Uri.parse(authorizationDetails.getLink().get()));
        cvvValidationPresenter = new CvvValidationPresenter(new Gson(), cardCvvView, new GenericCvvValidator(), validationLinkParser,
                CardServiceCreator.createCvvRestService(getContext(),
                        ConfigurationEnvironmentProvider.provideEnvironment(getContext()).getCardEndpointUrl()
                ));
        cvvValidationPresenter.takeView(this);
    }

    public void onCancelClick() {
        cvvValidationPresenter.onCanceled();
    }

    public void onAcceptClick() {
        cvvValidationPresenter.onAccepted();
    }

    @NonNull
    protected EditTextStyle createEditTextStaleFromInfo(TextStyleInfo inputTextStyleInfo) {
        return new EditTextStyle(inputTextStyleInfo, new FontProvider(getContext()));
    }

    @Override
    public void setViewLoading(boolean isLoading) {
        cardCvvView.setEnabled(!isLoading);
        progressView.setVisibility(isLoading ? VISIBLE : GONE);

    }

    @Override
    public void onValidationCompleted(@NonNull CvvPaymentStatus paymentStatus) {
        if (listener != null) {
            listener.onValidationCompleted(paymentStatus);
        }
    }
}
