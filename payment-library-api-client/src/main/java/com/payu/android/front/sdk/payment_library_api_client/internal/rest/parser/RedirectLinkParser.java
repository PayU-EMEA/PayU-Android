package com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser;

import android.net.Uri;
import androidx.annotation.NonNull;

public class RedirectLinkParser {
    private static final String REF_REQ_ID_PARAM = "refReqId";
    @NonNull
    private final Uri uri;

    public RedirectLinkParser(@NonNull Uri uri) {
        this.uri = uri;
    }

    @NonNull
    public String getRefReqId() {
        return uri.getQueryParameter(REF_REQ_ID_PARAM);
    }
}
