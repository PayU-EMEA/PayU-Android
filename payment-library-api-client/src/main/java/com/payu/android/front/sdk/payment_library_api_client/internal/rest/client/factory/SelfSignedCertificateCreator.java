package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.factory;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Optional;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class SelfSignedCertificateCreator {
    private static final String TAG = SelfSignedCertificateCreator.class.getSimpleName();
    @NonNull
    private final CertificateFactory certificateFactory;
    @NonNull
    private final Context context;

    public SelfSignedCertificateCreator(@NonNull Context context, @NonNull CertificateFactory certificateFactory) {
        this.context = context;
        this.certificateFactory = certificateFactory;
    }

    public Optional<Certificate> create(@NonNull String path) {
        Certificate ca;
        InputStream certInput = null;
        try {
            certInput = context.getAssets().open(path);
            ca = certificateFactory.generateCertificate(certInput);
        } catch (CertificateException | IOException e) {
            Log.e(TAG, "Cannot import provided certificate", e);
            return Optional.absent();
        } finally {
            if (certInput != null) {
                try {
                    certInput.close();
                } catch (IOException ignored) {
                }
            }
        }
        return Optional.fromNullable(ca);
    }
}
