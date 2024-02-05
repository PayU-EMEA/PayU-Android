package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_google_pay_module.builder.Cart;
import com.payu.android.front.sdk.payment_library_google_pay_module.listener.GooglePayVerificationListener;
import com.payu.android.front.sdk.payment_library_google_pay_module.listener.GooglePayVerificationStatus;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.GooglePayTokenResponse;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.GooglePayTokenResponseException;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.PaymentDataRequestException;

import org.json.JSONException;
import org.json.JSONObject;

import static com.payu.android.front.sdk.payment_library_google_pay_module.service.GooglePayConfiguration.getEnvironment;

class GooglePayHandler {
    private static final String PAYMENT_METHOD_DATA_KEY = "paymentMethodData";
    private static final String TOKENIZATION_DATA_KEY = "tokenizationData";
    private static final String TOKEN_KEY = "token";
    private final PaymentsClient paymentsClient;
    private final Activity activity;

    GooglePayHandler(@NonNull Activity activity) {
        this.activity = activity;
        paymentsClient = Wallet.getPaymentsClient(
                activity.getApplicationContext(),
                new Wallet.WalletOptions.Builder()
                        .setEnvironment(getEnvironment(activity))
                        .build());
    }

    public void isReadyToPay(@NonNull final GooglePayVerificationListener isGooglePayPossibleListener, boolean existingPaymentMethodRequired) {
        if (!isGooglePaySupportedApiVersion()) {
            isGooglePayPossibleListener.onVerificationCompleted(GooglePayVerificationStatus.ERROR_API_VERSION);
            return;
        }

        int googlePlayServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity);
        if (googlePlayServicesStatus != ConnectionResult.SUCCESS) {
            isGooglePayPossibleListener.onVerificationCompleted(parseGooglePlayServicesStatus(googlePlayServicesStatus));
            return;
        }

        final Optional<JSONObject> isReadyToPayJson = GooglePayConfiguration.getIsReadyToPayRequest(existingPaymentMethodRequired);
        if (!isReadyToPayJson.isPresent()) {
            isGooglePayPossibleListener.onVerificationCompleted(GooglePayVerificationStatus.ERROR_UNKNOWN);
            return;
        }

        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
        if (request == null) {
            isGooglePayPossibleListener.onVerificationCompleted(GooglePayVerificationStatus.ERROR_UNKNOWN);
            return;
        }

        Task<Boolean> readyToPay = paymentsClient.isReadyToPay(request);
        readyToPay.addOnCompleteListener(activity, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                try {
                    Boolean result = task.getResult(ApiException.class);
                    if (result != null && result) {
                        isGooglePayPossibleListener.onVerificationCompleted(GooglePayVerificationStatus.SUCCESS);
                    } else {
                        isGooglePayPossibleListener.onVerificationCompleted(GooglePayVerificationStatus.ERROR_UNKNOWN);
                    }
                } catch (ApiException exception) {
                    isGooglePayPossibleListener.onException(exception);
                }
            }
        });
    }

    public void requestGooglePayCard(@NonNull Cart cart, @NonNull String posId, @NonNull String merchantName, int requestCode) throws PaymentDataRequestException {
        Optional<JSONObject> paymentDataRequestJson = GooglePayConfiguration.getPaymentDataRequest(cart, posId, merchantName);
        if (!paymentDataRequestJson.isPresent()) {
            throw new PaymentDataRequestException();
        }
        PaymentDataRequest request = PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());
        if (request != null) {
            AutoResolveHelper.resolveTask(paymentsClient.loadPaymentData(request), activity, requestCode);
        }
    }

    @Nullable
    public GooglePayTokenResponse handleGooglePay(@NonNull Intent data) throws GooglePayTokenResponseException {
        PaymentData paymentData = PaymentData.getFromIntent(data);
        if (paymentData == null) {
            return null;
        }

        String paymentInformation = paymentData.toJson();
        try {
            JSONObject paymentMethodData = new JSONObject(paymentInformation).getJSONObject(PAYMENT_METHOD_DATA_KEY);
            String token = paymentMethodData.getJSONObject(TOKENIZATION_DATA_KEY).getString(TOKEN_KEY);
            return new GooglePayTokenResponse(base64(token));
        } catch (JSONException e) {
            throw new GooglePayTokenResponseException(e);
        }
    }

    private boolean isGooglePaySupportedApiVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    private GooglePayVerificationStatus parseGooglePlayServicesStatus(int googlePlayServicesStatus) {
        switch (googlePlayServicesStatus) {
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                return GooglePayVerificationStatus.ERROR_GOOGLE_PLAY_SERVICES_VERSION;
            case ConnectionResult.SERVICE_DISABLED:
            case ConnectionResult.SERVICE_MISSING:
            default:
                return GooglePayVerificationStatus.ERROR_GOOGLE_PLAY_SERVICES_UNAVAILABLE;
        }
    }

    private String base64(String token) {
        if (isGooglePaySupportedApiVersion()) {
            byte[] data = token.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            return Base64.encodeToString(data, Base64.NO_WRAP);
        }
        throw new IllegalStateException("GooglePay token result parsing cannot be done on pre-kitkat android versions.");
    }
}
