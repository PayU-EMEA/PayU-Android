package com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment;


import android.content.Context;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import java.security.KeyStore;

import okhttp3.logging.HttpLoggingInterceptor;

public interface RestEnvironment {
    KeyStore getAllowedCertificatesKeyStore(Context context);

    String getCardEndpointUrl();

    String getStaticContentUrl();

    boolean isMockingNetwork();

    boolean isPinningEnabled();

    String getStringRepresentation();

    HttpLoggingInterceptor.Level getLogLevel();

    Environment getGooglePayEnvironment();

    String silentAcceptEnvironment();

    String getOrigin();

}
