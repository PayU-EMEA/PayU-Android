package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import androidx.annotation.NonNull;
import android.util.Log;

import org.apache.http.conn.ssl.X509HostnameVerifier;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import static com.google.common.collect.Lists.newArrayList;

public class SslConfiguration {

    public static class Builder {

        private final X509TrustManager mAndroidTrustManager;
        private KeyStore mClientKeyStore;
        private String mClientKeyStorePassword;
        private List<String> mAcceptedHosts = newArrayList();
        private List<SslCertificate> mAllowedCertificates = newArrayList();

        public Builder(X509TrustManager androidTrustManager) {
            mAndroidTrustManager = androidTrustManager;
        }

        public Builder addAcceptedHost(@NonNull String url) {
            mAcceptedHosts.add(url);
            return this;
        }

        public SslConfiguration build() {
            return new SslConfiguration(mAndroidTrustManager, mAllowedCertificates, mAcceptedHosts, mClientKeyStore,
                    mClientKeyStorePassword);
        }

        public Builder withAllowedCertificates(@NonNull List<SslCertificate> allowedCertificates) {
            mAllowedCertificates = allowedCertificates;
            return this;
        }

        public Builder withClientCertificate(KeyStore clientKeyStore, String clientKeyStorePassword) {
            mClientKeyStore = clientKeyStore;
            mClientKeyStorePassword = clientKeyStorePassword;
            return this;
        }

        public Builder withoutPinning() {
            mAllowedCertificates.clear();
            mAcceptedHosts.clear();
            return this;
        }
    }

    private static final String TAG = SslConfiguration.class.getSimpleName();
    private final X509TrustManager mAndroidTrustManager;
    private final List<SslCertificate> mAllowedCertificates;
    private final List<String> mAcceptedHosts;
    private final KeyStore mClientKeyStore;
    private final String mClientKeyStorePassword;

    SslConfiguration(X509TrustManager androidTrustManager, List<SslCertificate> allowedCertificates, List<String> acceptedHosts,
                     KeyStore clientKeyStore, String clientKeyStorePassword) {
        mAndroidTrustManager = androidTrustManager;
        mAllowedCertificates = allowedCertificates;
        mAcceptedHosts = acceptedHosts;
        mClientKeyStore = clientKeyStore;
        mClientKeyStorePassword = clientKeyStorePassword;
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return new HostnameListStrictVerifier(mAcceptedHosts.toArray(new String[0]));
    }

    public SSLSocketFactory getSslSocketFactory() {
        X509TrustManager trustManager = isPinningEnabled() ? createAndroidTrustManager() : new LiberalHttpsVerifier();
        return new SSLSocketFactoryProvider(trustManager, //
                createKeyManagerFactory(mClientKeyStore, mClientKeyStorePassword)).getSSLFactory();
    }

    public X509TrustManager getTrustManager() {
        return mAndroidTrustManager;
    }

    public boolean isPinningEnabled() {
        return !mAllowedCertificates.isEmpty();
    }

    private AndroidAndProvidedCertificateListVerifier createAndroidTrustManager() {
        return new AndroidAndProvidedCertificateListVerifier(mAndroidTrustManager, mAllowedCertificates);
    }

    private KeyManagerFactory createKeyManagerFactory(KeyStore clientKeyStore, String password) {

        if (clientKeyStore != null) {
            return null;
        }

        try {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(clientKeyStore, getPasswordCharsOrNull(password));
            return keyManagerFactory;
        } catch (NoSuchAlgorithmException e) {
            Log.w(TAG, "Algorithm not found", e);
            return null;
        } catch (UnrecoverableKeyException e) {
            Log.w(TAG, "UnrecoverableKeyException", e);
            return null;
        } catch (KeyStoreException e) {
            Log.w(TAG, "KeyStoreException", e);
            return null;
        }
    }

    private char[] getPasswordCharsOrNull(String password) {
        return password != null ? password.toCharArray() : null;
    }
}
