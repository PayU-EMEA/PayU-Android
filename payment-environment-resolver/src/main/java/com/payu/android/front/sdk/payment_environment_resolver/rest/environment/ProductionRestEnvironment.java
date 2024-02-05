package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslCertificate;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.NetworkEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;

public class ProductionRestEnvironment extends NetworkEnvironment implements RestEnvironment {

    @Override
    public Optional<List<SslCertificate>> getAllowedCertificates() {
        List<SslCertificate> certificates = newArrayList(commonCertificates);
        certificates.addAll(newArrayList(new SslCertificate(565044404, "3365500879ad73e230b9e01d0d7fac91"),
                new SslCertificate(565044404, "5fa6be80b686c62f01ed0cabb196a105"),
                new SslCertificate(565044404, "69529181992039203566298953787712940909", SslCertificate.DECIMAL_BASE)));
        return of(certificates);
    }

    @Override
    public String getCardEndpointUrl() {
        return "https://mobilesdk.secure.payu.com";
    }

    @Override
    public String getStaticContentUrl() {
        return "https://static.payu.com";
    }

    @Override
    public boolean isPinningEnabled() {
        return true;
    }

    @Override
    public String getStringRepresentation() {
        return "production";
    }

    @Override
    public HttpLoggingInterceptor.Level getLogLevel() {
        return HttpLoggingInterceptor.Level.NONE;
    }

    @Override
    public Environment getGooglePayEnvironment() {
        return Environment.PROD;
    }

    @Override
    public String silentAcceptEnvironment() {
        return "'https://secure.payu.com'";
    }
}
