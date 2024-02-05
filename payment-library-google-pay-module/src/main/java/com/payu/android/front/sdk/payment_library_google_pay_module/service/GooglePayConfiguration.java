package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.wallet.WalletConstants;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;
import com.payu.android.front.sdk.payment_library_google_pay_module.builder.Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GooglePayConfiguration {
    private static final String API_VERSION_KEY = "apiVersion";
    private static final int API_VERSION_VALUE = 2;
    private static final String API_VERSION_MINOR_KEY = "apiVersionMinor";
    private static final int API_VERSION_MINOR_VALUE = 0;

    private static final String TYPE_KEY = "type";
    private static final String PARAMETERS_KEY = "parameters";

    private static final String GATEWAY_TOKENIZATION_TYPE_VALUE = "PAYMENT_GATEWAY";
    private static final String GATEWAY_TOKENIZATION_PARAM_GATEWAY_KEY = "gateway";
    private static final String GATEWAY_TOKENIZATION_PARAM_GATEWAY_VALUE = "payu";
    private static final String GATEWAY_TOKENIZATION_PARAM_MERCHANT_ID_KEY = "gatewayMerchantId";

    private static final String CARD_NETWORK_MASTERCARD = "MASTERCARD";
    private static final String CARD_NETWORK_VISA = "VISA";

    private static final String PAYMENT_METHOD_CARD = "PAN_ONLY";
    private static final String PAYMENT_METHOD_TOKENIZED_CARD = "CRYPTOGRAM_3DS";

    private static final String ALLOWED_PAYMENT_METHODS_KEY = "allowedPaymentMethods";
    private static final String EXISTING_PAYMENT_METHODS_REQUIRED = "existingPaymentMethodRequired";
    private static final String PAYMENT_METHOD_TYPE_VALUE = "CARD";
    private static final String PAYMENT_METHOD_ALLOWED_AUTH_METHODS_KEY = "allowedAuthMethods";
    private static final String PAYMENT_METHOD_ALLOWED_CARD_NETWORKS_KEY = "allowedCardNetworks";
    private static final String PAYMENT_METHOD_TOKENIZATION_SPECIFICATION_KEY = "tokenizationSpecification";

    private static final String TRANSACTION_INFO_KEY = "transactionInfo";
    private static final String TRANSACTION_INFO_TOTAL_PRICE_KEY = "totalPrice";
    private static final String TRANSACTION_INFO_TOTAL_PRICE_STATUS_KEY = "totalPriceStatus";
    private static final String TRANSACTION_INFO_TOTAL_PRICE_STATUS_VALUE = "FINAL";
    private static final String TRANSACTION_INFO_COUNTRY_CODE_KEY = "countryCode";
    private static final String TRANSACTION_INFO_COUNTRY_CODE_VALUE = "PL";
    private static final String TRANSACTION_INFO_CURRENCY_CODE_KEY = "currencyCode";

    private static final String MERCHANT_INFO_KEY = "merchantInfo";
    private static final String MERCHANT_INFO_MERCHANT_NAME_KEY = "merchantName";


    static int getEnvironment(@NonNull Context context) {
        RestEnvironment environment = ConfigurationEnvironmentProvider.provideEnvironment(context);

        return environment.getGooglePayEnvironment() == Environment.PROD
                ? WalletConstants.ENVIRONMENT_PRODUCTION
                : WalletConstants.ENVIRONMENT_TEST;
    }

    static Optional<JSONObject> getIsReadyToPayRequest(boolean existingPaymentMethodRequired) {
        try {
            JSONObject isReadyToPayRequest = getBaseRequest()
                    .put(ALLOWED_PAYMENT_METHODS_KEY, new JSONArray().put(getBaseCardPaymentMethod()))
                    .put(EXISTING_PAYMENT_METHODS_REQUIRED,existingPaymentMethodRequired);
            return Optional.of(isReadyToPayRequest);
        } catch (JSONException e) {
            System.out.println(e);
            return Optional.absent();
        }
    }

    static Optional<JSONObject> getPaymentDataRequest(@NonNull Cart cart, @NonNull String posId, @NonNull String merchantName) {
        try {
            JSONObject paymentDataRequest = getBaseRequest()
                    .put(ALLOWED_PAYMENT_METHODS_KEY, new JSONArray().put(getCardPaymentMethod(posId)))
                    .put(TRANSACTION_INFO_KEY, getTransactionInfo(cart))
                    .put(MERCHANT_INFO_KEY, getMerchantInfo(merchantName));
            return Optional.of(paymentDataRequest);
        } catch (JSONException e) {
            System.out.println(e);
            return Optional.absent();
        }
    }

    private static JSONObject getBaseRequest() throws JSONException {
        return new JSONObject()
                .put(API_VERSION_KEY, API_VERSION_VALUE)
                .put(API_VERSION_MINOR_KEY, API_VERSION_MINOR_VALUE);
    }

    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put(CARD_NETWORK_MASTERCARD)
                .put(CARD_NETWORK_VISA);
    }

    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put(PAYMENT_METHOD_CARD)
                .put(PAYMENT_METHOD_TOKENIZED_CARD);
    }

    private static JSONObject getGatewayTokenizationSpecification(@NonNull String posId) throws JSONException {
        return new JSONObject()
                .put(TYPE_KEY, GATEWAY_TOKENIZATION_TYPE_VALUE)
                .put(PARAMETERS_KEY, new JSONObject()
                        .put(GATEWAY_TOKENIZATION_PARAM_GATEWAY_KEY, GATEWAY_TOKENIZATION_PARAM_GATEWAY_VALUE)
                        .put(GATEWAY_TOKENIZATION_PARAM_MERCHANT_ID_KEY, posId));
    }

    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
        return new JSONObject()
                .put(TYPE_KEY, PAYMENT_METHOD_TYPE_VALUE)
                .put(PARAMETERS_KEY, new JSONObject()
                        .put(PAYMENT_METHOD_ALLOWED_AUTH_METHODS_KEY, getAllowedCardAuthMethods())
                        .put(PAYMENT_METHOD_ALLOWED_CARD_NETWORKS_KEY, getAllowedCardNetworks()));
    }

    private static JSONObject getCardPaymentMethod(@NonNull String posId) throws JSONException {
        return getBaseCardPaymentMethod()
                .put(PAYMENT_METHOD_TOKENIZATION_SPECIFICATION_KEY, getGatewayTokenizationSpecification(posId));
    }

    private static JSONObject getTransactionInfo(@NonNull Cart cart) throws JSONException {
        String totalPrice = PriceFormatter.formatAsString(cart.getTotalPrice());
        return new JSONObject()
                .put(TRANSACTION_INFO_TOTAL_PRICE_KEY, totalPrice)
                .put(TRANSACTION_INFO_TOTAL_PRICE_STATUS_KEY, TRANSACTION_INFO_TOTAL_PRICE_STATUS_VALUE)
                .put(TRANSACTION_INFO_COUNTRY_CODE_KEY, TRANSACTION_INFO_COUNTRY_CODE_VALUE)
                .put(TRANSACTION_INFO_CURRENCY_CODE_KEY, cart.getCurrency());
    }

    private static JSONObject getMerchantInfo(@NonNull String merchantName) throws JSONException {
        return new JSONObject().put(MERCHANT_INFO_MERCHANT_NAME_KEY, merchantName);
    }
}
