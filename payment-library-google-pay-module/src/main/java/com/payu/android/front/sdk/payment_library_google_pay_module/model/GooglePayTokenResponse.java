package com.payu.android.front.sdk.payment_library_google_pay_module.model;

import androidx.annotation.NonNull;

public class GooglePayTokenResponse {
    @NonNull
    private final String googlePayToken;

    public GooglePayTokenResponse(@NonNull String googlePayToken) {
        this.googlePayToken = googlePayToken;
    }

    @NonNull
    public String getGooglePayToken() {
        return googlePayToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GooglePayTokenResponse)) {
            return false;
        }
        GooglePayTokenResponse that = (GooglePayTokenResponse) o;
        return googlePayToken.equals(that.googlePayToken);
    }

    @Override
    public int hashCode() {
        return googlePayToken.hashCode();
    }
}
