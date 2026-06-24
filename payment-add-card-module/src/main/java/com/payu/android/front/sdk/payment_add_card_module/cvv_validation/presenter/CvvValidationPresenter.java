package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.presenter;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationView;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_add_card_module.validation.StringValidator;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CvvValidationPresenter extends BasePresenter<CvvValidationView> {
    @NonNull
    private final SelectorCvv selectorCvv;
    @NonNull
    private final RedirectLinkParser validationLinkParser;
    @NonNull
    private final StringValidator editTextValidator;
    @NonNull
    private final CvvRestService cardService;

    public CvvValidationPresenter(
            @NonNull SelectorCvv selectorCvv, @NonNull StringValidator editTextValidator,
            @NonNull RedirectLinkParser validationLinkParser,
            @NonNull CvvRestService cardService) {
        this.selectorCvv = selectorCvv;
        this.editTextValidator = editTextValidator;
        this.validationLinkParser = validationLinkParser;
        this.cardService = cardService;
    }

    public void onCanceled() {
        notifyView(CvvPaymentStatus.CANCEL_PAYMENT);
    }

    public void onAccepted() {
        String cvv = selectorCvv.getCvvCode();
        Optional<String> errorString = editTextValidator.getErrorString(cvv);
        selectorCvv.setCvvError(errorString.orNull());
        if (!errorString.isPresent()) {
            confirmCvv(cvv);
        }
    }

    private void confirmCvv(String cvv) {
        view.setViewLoading(true);
        cardService.sendCvv(validationLinkParser.getRefReqId(), cvv).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.code() == 202) {
                    notifyView(CvvPaymentStatus.SUCCESS);
                } else {
                    notifyView(CvvPaymentStatus.PAYMENT_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                notifyView(CvvPaymentStatus.PAYMENT_ERROR);
            }
        });
    }

    private void notifyView(CvvPaymentStatus status) {
        if (view != null) {
            view.setViewLoading(false);
            view.onValidationCompleted(status);
        }
    }
}
