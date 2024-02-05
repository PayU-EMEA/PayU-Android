package com.payu.android.front.sdk.payment_add_card_module.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.OpenPayuStatusCode;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.RequestStatus;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.TokenizedCardData;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateResponse;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethodBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitNewCardCallback implements Callback<TokenCreateResponse> {
    private static final String TAG = NewCardService.class.getName();
    private final static int GENERAL_ERROR = -1;
    @NonNull
    private final NewCardCallback callback;

    @NonNull
    private NewCardService.CardDataProvider cardDataProvider;

    public RetrofitNewCardCallback(@NonNull NewCardCallback callback) {
        this.callback = callback;
    }

    public void setCardDataProviderListener(NewCardService.CardDataProvider listener) {
        this.cardDataProvider = listener;
    }

    @Override
    public void onResponse(@NotNull Call<TokenCreateResponse> call, Response<TokenCreateResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            success(response.body());
        } else {
            failure(response.code(), response.errorBody() != null ? response.errorBody().toString() : "");
        }
    }

    @Override
    public void onFailure(@NotNull Call<TokenCreateResponse> call, @NotNull Throwable t) {
        failure();
    }

    private void success(TokenCreateResponse tokenCreateResponse) {
        RequestStatus requestStatus = tokenCreateResponse.getRequestStatus();
        if (isRequestStatusEmpty(requestStatus) || isRequestStatusCodeEmpty(requestStatus) || !requestStatus.getOpenPayuStatusCode().isSuccess()) {
            int statusCode = isRequestStatusEmpty(requestStatus) ? GENERAL_ERROR : requestStatus.getStatusCodeNumber();
            String statusLiteral = isRequestStatusCodeEmpty(requestStatus) || isRequestStatusCodeEmpty(requestStatus) ?
                    OpenPayuStatusCode.GENERAL_ERROR.toString() : requestStatus.getStatusLiteral();
            Log.v(TAG, "request failed: " + statusLiteral + " with status code: " + statusCode);
            callback.onError(new Error(statusCode, statusLiteral));
            return;
        }
        TokenizedCardData cardData = tokenCreateResponse.getTokenizedCardData();
        String cardPath = cardDataProvider.getCardLogoPath();

        if (cardPath == null || cardData == null) {
            callback.onError(new CardNotSelectedError());
            return;
        }

        CardPaymentMethodBuilder builder = new CardPaymentMethodBuilder()
                .withValue(cardData.getCardToken())
                .withBrandImageUrl(cardPath)
                .withCardExpirationMonth(cardDataProvider.getCardValidMonth())
                .withCardExpirationYear(cardDataProvider.getCardValidYear())
                .withCardNumberMasked(cardData.getCardMask())
                .withStatus(cardDataProvider.getCardStatus())
                .withCardScheme(cardDataProvider.getCardProviderName())
                .withPreferred(true);

        callback.onSuccess(builder.build());

    }

    private boolean isRequestStatusEmpty(RequestStatus requestStatus) {
        return requestStatus == null;
    }

    private boolean isRequestStatusCodeEmpty(RequestStatus requestStatus) {
        return requestStatus.getOpenPayuStatusCode() == null;
    }

    private void failure() {
        failure(RetrofitNewCardCallback.GENERAL_ERROR, null);
    }

    private void failure(int errorCode, String errorBody) {
        if (errorCode != GENERAL_ERROR) {
            Log.v(TAG, "request failed: " + errorBody + " with status code: " + errorCode);
        } else {
            Log.v(TAG, "general error with backend communication");
        }
        callback.onError(new Error(errorCode));
    }
}
