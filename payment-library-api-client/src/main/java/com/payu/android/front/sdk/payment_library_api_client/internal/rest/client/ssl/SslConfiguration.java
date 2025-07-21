package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import static com.google.common.collect.Lists.newArrayList;

import androidx.annotation.NonNull;

import org.apache.http.conn.ssl.X509HostnameVerifier;

import java.util.List;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class SslConfiguration {

    public static class Builder {

        private final X509TrustManager mAndroidTrustManager;
        private final List<String> mAcceptedHosts = newArrayList();

        public Builder(X509TrustManager androidTrustManager) {
            mAndroidTrustManager = androidTrustManager;
        }

        public Builder addAcceptedHost(@NonNull String url) {
            mAcceptedHosts.add(url);
            return this;
        }

        public SslConfiguration build() {
            return new SslConfiguration(mAndroidTrustManager, mAcceptedHosts);
        }
    }

    private final X509TrustManager mAndroidTrustManager;
    private final List<String> mAcceptedHosts;

    SslConfiguration(X509TrustManager androidTrustManager, List<String> acceptedHosts) {
        mAndroidTrustManager = androidTrustManager;
        mAcceptedHosts = acceptedHosts;
    }

    public HostnameListStrictVerifier getHostnameVerifier() {
        return new HostnameListStrictVerifier(mAcceptedHosts.toArray(new String[0]));
    }

    public SSLSocketFactory getSslSocketFactory() {
        X509TrustManager trustManager = isPinningEnabled() ? mAndroidTrustManager : new LiberalHttpsVerifier();
        return new SSLSocketFactoryProvider(trustManager).getSSLFactory();
    }

    public X509TrustManager getTrustManager() {
        return mAndroidTrustManager;
    }

    public boolean isPinningEnabled() {
        return mAndroidTrustManager.getAcceptedIssuers().length !=0;
    }
}
