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
import static java.util.Arrays.asList;

public class SandboxRestEnvironment extends NetworkEnvironment implements RestEnvironment {

    private static final String BASE_URL = "https://secure.snd.payu.com";

    @Override
    public Optional<List<SslCertificate>> getAllowedCertificates() {
        List<SslCertificate> certificates = newArrayList(commonCertificates);
        certificates.addAll(asList(new SslCertificate(1071256665, "95861235404570061302220811586143096878", SslCertificate.DECIMAL_BASE),
                new SslCertificate(-1006978217, "72051517793617806281683710734116942130", SslCertificate.DECIMAL_BASE),
                new SslCertificate(-566879510, "127614157056681299805556476275995414779", SslCertificate.DECIMAL_BASE)));
        return of(certificates);
    }

    @Override
    public String getCardEndpointUrl() {
        return BASE_URL;
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
        return "sandbox";
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
        return "'https://merch-prod.snd.payu.com'";
    }

    @Override
    public String getOrigin() {
        return "merch-test.snd.payu.com";
    }
}
