package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import android.util.Log;

import com.payu.android.front.sdk.payment_library_core.util.ArrayUtils;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

class SSLSocketFactoryProvider {

    private static final String TAG = SSLSocketFactoryProvider.class.getSimpleName();
    private static final String TLS_VERSION = "TLSv1.2";
    private final ArrayUtils mUtils = new ArrayUtils();
    private X509TrustManager mTrustManager;
    private KeyManagerFactory mKeyManagerFactory;

    public SSLSocketFactoryProvider(X509TrustManager trustManager, KeyManagerFactory keyManagerFactory) {
        mTrustManager = trustManager;
        mKeyManagerFactory = keyManagerFactory;
    }

    public SSLSocketFactory getSSLFactory() {

        try {
            SSLContext sslContext = SSLContext.getInstance(TLS_VERSION);
            sslContext.init(getKeyManagers(), mUtils.asArray(mTrustManager), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            Log.e(TAG, "An exception occurred during Red environment configuration", e);
            return null;
        }
    }

    private KeyManager[] getKeyManagers() {
        return mKeyManagerFactory == null ? null : mKeyManagerFactory.getKeyManagers();
    }
}
