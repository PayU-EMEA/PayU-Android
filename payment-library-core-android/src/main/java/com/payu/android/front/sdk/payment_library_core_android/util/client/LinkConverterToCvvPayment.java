package com.payu.android.front.sdk.payment_library_core_android.util.client;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;


/**
 * Taken from PayTouch
 * This will be used for fixing links obtained from merchant when CVV was selected
 */
public class LinkConverterToCvvPayment {

    private static final String TRANSACTION_ID_PARAMETER = "refReqId";
    private static final String CVV_PATH_FOR_PAYMENT = "/api/v2/token/token.json";
    private RestEnvironment mRestEnvironment;

    public LinkConverterToCvvPayment(@NonNull RestEnvironment restEnvironment) {
        mRestEnvironment = restEnvironment;
    }

    public String convert(@NonNull String linkToConversion) {
        return getCvvRequestUrl(linkToConversion);
    }

    private String extractReferenceRequestId(String url) {
        return Uri.parse(url).getQueryParameter(TRANSACTION_ID_PARAMETER);
    }


    private String getCvvRequestUrl(String cvvUrl) {
        return new Uri.Builder().encodedPath(mRestEnvironment.getCardEndpointUrl() + CVV_PATH_FOR_PAYMENT)
                .appendQueryParameter(TRANSACTION_ID_PARAMETER, extractReferenceRequestId(cvvUrl))
                .build()
                .toString();
    }
}
