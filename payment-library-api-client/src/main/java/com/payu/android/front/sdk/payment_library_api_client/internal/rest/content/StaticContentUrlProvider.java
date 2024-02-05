package com.payu.android.front.sdk.payment_library_api_client.internal.rest.content;


import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

public class StaticContentUrlProvider {

    private RestEnvironment mRestEnvironment;

    public StaticContentUrlProvider(@NonNull RestEnvironment restEnvironment) {
        mRestEnvironment = restEnvironment;
    }

    public String get(@NonNull String path) {
        return new StringBuilder(mRestEnvironment.getStaticContentUrl()).append(path).toString();
    }
}
