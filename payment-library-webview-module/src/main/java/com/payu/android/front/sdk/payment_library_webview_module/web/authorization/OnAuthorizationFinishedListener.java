package com.payu.android.front.sdk.payment_library_webview_module.web.authorization;

import androidx.annotation.NonNull;

public interface OnAuthorizationFinishedListener {
    OnAuthorizationFinishedListener EMPTY_LISTENER = new OnAuthorizationFinishedListener() {

        @Override
        public void onAuthorizationFinished(@NonNull WebPaymentWrapper authorizationStatus) {
            // empty listener
        }

    };

    void onAuthorizationFinished(@NonNull WebPaymentWrapper authorizationStatus);

}
