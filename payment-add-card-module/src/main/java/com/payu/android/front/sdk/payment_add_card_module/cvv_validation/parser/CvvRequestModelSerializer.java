package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.parser;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.model.CvvAuthorization;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;

public class CvvRequestModelSerializer {
    @NonNull
    private final Gson gson;
    @NonNull
    private RedirectLinkParser validationLinkParser;
    @NonNull
    private String cvv;

    public CvvRequestModelSerializer(
            @NonNull Gson gson,
            @NonNull RedirectLinkParser validationLinkParser, @NonNull String cvv) {
        this.gson = gson;
        this.validationLinkParser = validationLinkParser;
        this.cvv = cvv;
    }

    public String getFormattedJson() {
        CvvAuthorization cvvAuthorization = new CvvAuthorization.Builder()
                .withCvv(cvv).withRefReqId(validationLinkParser.getRefReqId()).build();
        return gson.toJson(cvvAuthorization);
    }

}
