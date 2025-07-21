package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.NetworkEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

public class SandboxRestEnvironment extends NetworkEnvironment implements RestEnvironment {

    @Override
    protected Optional<List<String>> getAllowedCertificates() {
        List<String> certificates = newArrayList(commonCertificates);

        return of(certificates);
    }

    @Override
    public String getCardEndpointUrl() {
        return "https://mobilesdk.snd.payu.com";
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
