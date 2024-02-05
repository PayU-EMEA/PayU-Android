package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.presenter;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.parser.CvvRequestModelSerializer;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationView;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_add_card_module.validation.cvv.CvvValidator;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.OpenPayuStatusCode;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.OpenPayuResult;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CvvValidationPresenter extends BasePresenter<CvvValidationView> {
    @NonNull
    private final Gson gson;
    @NonNull
    private final SelectorCvv selectorCvv;
    @NonNull
    private final CvvValidator cvvValidator;
    @NonNull
    private final RedirectLinkParser validationLinkParser;
    @NonNull
    private final CvvRestService cardService;

    public CvvValidationPresenter(
            @NonNull Gson gson, @NonNull SelectorCvv selectorCvv, @NonNull CvvValidator cvvValidator,
            @NonNull RedirectLinkParser validationLinkParser,
            @NonNull CvvRestService cardService) {
        this.gson = gson;
        this.selectorCvv = selectorCvv;
        this.cvvValidator = cvvValidator;
        this.validationLinkParser = validationLinkParser;
        this.cardService = cardService;
    }

    public void onCanceled() {
        notifyView(CvvPaymentStatus.CANCEL_PAYMENT);
    }

    public void onAccepted() {
        String cvv = selectorCvv.getCvvCode();
        Optional<String> errorString = cvvValidator.getErrorString(cvv);
        selectorCvv.setCvvError(errorString.orNull());
        if (!errorString.isPresent()) {
            confirmCvv(cvv);
        }
    }

    private void confirmCvv(String cvv) {
        CvvRequestModelSerializer cvvRequestModelSerializer = new CvvRequestModelSerializer(gson, validationLinkParser, cvv);
        view.setViewLoading(true);
        cardService.sendCvv(cvvRequestModelSerializer.getFormattedJson()).enqueue(new Callback<OpenPayuResult>() {
            @Override
            public void onResponse(@NotNull Call<OpenPayuResult> call, @NotNull Response<OpenPayuResult> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getOpenPayuStatusCode() == OpenPayuStatusCode.SUCCESS) {
                    notifyView(CvvPaymentStatus.SUCCESS);
                } else {
                    notifyView(CvvPaymentStatus.PAYMENT_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<OpenPayuResult> call, @NotNull Throwable t) {
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
