package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import android.util.Log;

import com.google.common.base.Optional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import androidx.annotation.NonNull;

public class AndroidTrustManagerProvider {
    private static final String TAG = AndroidTrustManagerProvider.class.getSimpleName();

    public Optional<X509TrustManager> create(KeyStore keyStore) {

        try {
            return getAndroidX509TrustManager(getAndroidTrustManagers(keyStore));
        } catch (GeneralSecurityException e) {
            Log.w(TAG, "Local ssl keystore initialization failed", e);
            return absent();
        }
    }

    TrustManager[] getAndroidTrustManagers(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        return trustManagerFactory.getTrustManagers();
    }

    private Optional<X509TrustManager> getAndroidX509TrustManager(TrustManager[] trustManagers) {

        for (TrustManager trustManager : trustManagers) {

            if (trustManager instanceof X509TrustManager) {
                return of((X509TrustManager) trustManager);
            }
        }

        return absent();
    }
}