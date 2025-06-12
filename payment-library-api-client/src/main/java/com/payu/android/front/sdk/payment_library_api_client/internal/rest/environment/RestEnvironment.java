package com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment;


import android.content.Context;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslCertificate;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import java.security.KeyStore;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

public interface RestEnvironment {
    Optional<List<SslCertificate>> getAllowedCertificates();

    KeyStore getClientKeyStore(Context context);

    String getCardEndpointUrl();

    String getClientKeyStorePassword();

    String getStaticContentUrl();

    boolean isMockingNetwork();

    boolean isPinningEnabled();

    String getStringRepresentation();

    HttpLoggingInterceptor.Level getLogLevel();

    Environment getGooglePayEnvironment();

    String silentAcceptEnvironment();

    String getOrigin();

}
