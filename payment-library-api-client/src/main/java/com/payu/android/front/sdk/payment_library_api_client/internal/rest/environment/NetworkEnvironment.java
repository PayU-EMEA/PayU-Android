package com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment;

import static com.google.common.collect.Lists.newArrayList;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.common.base.Optional;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

public abstract class NetworkEnvironment implements RestEnvironment {
    /**
     * Those certificates are added for production & sandbox environment. For network/security purpose we are prepared to change between mentioned
     * certificates
     */
    protected List<String> commonCertificates = newArrayList(
            "certs/SentigoR46.cer",
            "certs/entrustG2Ca.cer",
            "certs/PayURoot.crt"
    );

    protected Optional<List<String>> getAllowedCertificates() {
        return Optional.absent();
    }

    @Override
    public KeyStore getAllowedCertificatesKeyStore(Context context) {
        Optional<List<String>> allowedCertificates = getAllowedCertificates();

        if (!allowedCertificates.isPresent() || allowedCertificates.get().isEmpty()) {
            return null;
        }
        AssetManager assetManager = context.getAssets();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (String certificatePath : allowedCertificates.get()) {
                InputStream is = assetManager.open(certificatePath);
                X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(is);
                keyStore.setCertificateEntry(cert.getSerialNumber().toString(), cert);
                is.close();
            }
            return keyStore;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load allowed certificates", e);
        }

    }

    @Override
    public boolean isMockingNetwork() {
        return false;
    }

}
