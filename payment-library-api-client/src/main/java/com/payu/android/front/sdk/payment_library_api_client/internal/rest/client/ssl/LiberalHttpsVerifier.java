package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import javax.net.ssl.X509TrustManager;

public class LiberalHttpsVerifier implements X509TrustManager {

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        // Accept all
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        // Accept all
    }

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
