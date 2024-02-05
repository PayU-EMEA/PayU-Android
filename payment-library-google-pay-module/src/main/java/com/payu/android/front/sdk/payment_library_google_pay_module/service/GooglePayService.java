package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.payu.android.front.sdk.payment_library_google_pay_module.builder.Cart;
import com.payu.android.front.sdk.payment_library_google_pay_module.listener.GooglePayVerificationListener;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.GooglePayTokenResponse;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.GooglePayTokenResponseException;
import com.payu.android.front.sdk.payment_library_google_pay_module.model.PaymentDataRequestException;

/**
 * This service is helping with Payment via GooglePay API
 */
public class GooglePayService {
    /**
     * Request code used for requesting tokenized GooglePay card token. This request code should be used to check
     * result data received in method {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}.
     * <p>
     * Obtained result data should be passed for decoding to {@linkplain GooglePayService#handleGooglePayResultData(Intent)}
     */
    public static final int REQUEST_CODE_GOOGLE_PAY_PAYMENT = 1236;
    public static final int RESULT_ERROR = AutoResolveHelper.RESULT_ERROR;
    private final GooglePayHandler googlePayHandler;

    public GooglePayService(Activity activity) {
        googlePayHandler = new GooglePayHandler(activity);
    }

    /**
     * Check if device can handle Payment via Google Pay
     * Requirements:
     * - At least Android KitKat (API 19+)
     * - Google Play services version >= 11.4.x
     * - Logged into Google account on device
     *
     * @param isGooglePayPossibleListener handle verifying device response from Google API
     * @see <a href=https://developers.google.com/pay/api/android/overview>Google requirements for device</a>
     */
    public void isReadyToPay(@NonNull final GooglePayVerificationListener isGooglePayPossibleListener) {
        googlePayHandler.isReadyToPay(isGooglePayPossibleListener, false);
    }

    /**
     * Check if device can handle Payment via Google Pay
     * Requirements:
     * - At least Android KitKat (API 19+)
     * - Google Play services version >= 11.4.x
     * - Logged into Google account on device
     *
     * @param isGooglePayPossibleListener handle verifying device response from Google API
     * @param existingPaymentMethodRequired if set to true, then the isReadyToPay() class method will return true
     *                                      if the current viewer is ready
     *                                      to pay with one or more payment methods specified in allowedPaymentMethods.
     * @see <a href=https://developers.google.com/pay/api/android/overview>Google requirements for device</a>
     */

    public void isReadyToPay(@NonNull final GooglePayVerificationListener isGooglePayPossibleListener, boolean existingPaymentMethodRequired) {
        googlePayHandler.isReadyToPay(isGooglePayPossibleListener, existingPaymentMethodRequired);
    }
    /**
     *
     * Requesting GooglePay Card. UI with selecting card will always be shown.
     * Call this method after {@linkplain GooglePayService#isReadyToPay}
     * Result will be passed to {@linkplain Activity#onActivityResult}
     *
     * @param cart  - Cart object with the information about Currency and payment amount
     * @param merchantName                - Merchant name should match name provided in PayU and payment organizations
     * @param posId - PosId used for this payment. To learn more about POS please check
     *              <a href="http://developers.payu.com/en/overview.html">official documentation</a>.
     */
    public void requestGooglePayCard(@NonNull Cart cart, @NonNull String posId, @NonNull String merchantName) throws PaymentDataRequestException {
        googlePayHandler.requestGooglePayCard(cart, posId, merchantName, REQUEST_CODE_GOOGLE_PAY_PAYMENT);
    }

    /**
     * Call this method in {@linkplain Activity#onActivityResult} result for {@link #REQUEST_CODE_GOOGLE_PAY_PAYMENT} request code.
     * <p>
     * return {@link GooglePayTokenResponse} with payment token or null, if there is no token in response
     */
    @Nullable
    public GooglePayTokenResponse handleGooglePayResultData(@NonNull Intent data) throws GooglePayTokenResponseException {
        return googlePayHandler.handleGooglePay(data);
    }

    /**
     * Handle error response from Google Pay API
     * <br>
     * Errors may come from common Google API or the PaymentsClient itself.
     * <br>
     * Check here: <a href="https://developers.google.com/android/reference/com/google/android/gms/common/api/CommonStatusCodes">CommonStatusCodes</a>
     * if error code is related to Google API, or here:
     * <a href="https://developers.google.com/android/reference/com/google/android/gms/wallet/WalletConstants">Wallet Constants Library</a> if error is related to wallet.
     *
     * @param data - Intent data from {@linkplain Activity#onActivityResult} resultCode {@linkplain #RESULT_ERROR}
     * @return ErrorStatus mapped from Google Pay Status.
     */
    public ErrorStatus handleGooglePayErrorStatus(Intent data) {
        Status statusFromIntent = AutoResolveHelper.getStatusFromIntent(data);
        return ErrorStatus.fromGooglePayStatus(statusFromIntent);
    }
}
