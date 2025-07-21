package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import android.content.Context;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import java.security.KeyStore;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;


public class TestRestEnvironment implements RestEnvironment {

    protected Optional<List<String>> getAllowedCertificates() {
        return null;
    }

    @Override
    public KeyStore getAllowedCertificatesKeyStore(Context context) {
        return null;
    }

    @Override
    public String getCardEndpointUrl() {
        return "http://test.payu.com/card";
    }

    @Override
    public String getStaticContentUrl() {
        return "http://server.url";
    }

    @Override
    public boolean isMockingNetwork() {
        return false;
    }

    @Override
    public boolean isPinningEnabled() {
        return false;
    }

    @Override
    public String getStringRepresentation() {
        return "test-key";
    }

    @Override
    public HttpLoggingInterceptor.Level getLogLevel() {
        return null;
    }

    @Override
    public Environment getGooglePayEnvironment() {
        return null;
    }

    @Override
    public String silentAcceptEnvironment() {
        return null;
    }

    @Override
    public String getOrigin() {
        return "test.payu.com";
    }
}
