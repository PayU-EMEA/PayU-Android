package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.NetworkEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import okhttp3.logging.HttpLoggingInterceptor;

public class SandboxPartnersRestEnvironment extends NetworkEnvironment implements RestEnvironment {
    @Override
    public String getCardEndpointUrl() {
        return "https://secure.sndbeta.payu.com";
    }

    @Override
    public String getStaticContentUrl() {
        return "https://static.payu.com";
    }

    @Override
    public boolean isPinningEnabled() {
        return false;
    }

    @Override
    public String getStringRepresentation() {
        return "sandbox-partners";
    }

    @Override
    public HttpLoggingInterceptor.Level getLogLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    @Override
    public Environment getGooglePayEnvironment() {
        return Environment.TEST;
    }

    @Override
    public String silentAcceptEnvironment() {
        return null;
    }
}
