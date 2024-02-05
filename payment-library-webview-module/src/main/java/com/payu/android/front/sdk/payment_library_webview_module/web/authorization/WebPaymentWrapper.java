package com.payu.android.front.sdk.payment_library_webview_module.web.authorization;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WebPaymentWrapper {
    @NonNull
    private WebPaymentStatus status;
    @Nullable
    private String continueUrl;

    public WebPaymentWrapper(@NonNull WebPaymentStatus status, @Nullable String continueUrl) {
        this.status = status;
        this.continueUrl = continueUrl;
    }

    @NonNull
    public WebPaymentStatus getStatus() {
        return status;
    }

    @Nullable
    public String getContinueUrl() {
        return continueUrl;
    }
}
