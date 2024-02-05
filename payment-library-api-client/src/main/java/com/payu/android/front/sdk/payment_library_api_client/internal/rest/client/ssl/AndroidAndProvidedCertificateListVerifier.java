package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import static com.google.common.base.Preconditions.checkArgument;

import static java.util.Arrays.asList;

import android.util.Log;

import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.X509TrustManager;

public class AndroidAndProvidedCertificateListVerifier implements X509TrustManager {

    private static final String TAG = AndroidAndProvidedCertificateListVerifier.class.getSimpleName();
    private final Map<BigInteger, Integer> mAllowedCertificatesMap;
    private X509TrustManager mDefaultTrustManager;

    public AndroidAndProvidedCertificateListVerifier(X509TrustManager defaultTrustManager, List<SslCertificate> certificates) {
        checkArgument(certificates != null, "Non null certificate list must be provided.");
        mDefaultTrustManager = defaultTrustManager;
        mAllowedCertificatesMap = convertToMap(certificates);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (isDefaultTrustManagerAvailable()) {
            mDefaultTrustManager.checkClientTrusted(chain, authType);
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (isDefaultTrustManagerAvailable()) {
            mDefaultTrustManager.checkServerTrusted(chain, authType);
        }

        for (int i = 0; (i < chain.length); i++) {
            logCertificateData(chain[i]);
        }

        validateChain(asList(chain));
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return isDefaultTrustManagerAvailable() ? mDefaultTrustManager.getAcceptedIssuers() : null;
    }

    private Map<BigInteger, Integer> convertToMap(List<SslCertificate> certificates) {
        HashMap<BigInteger, Integer> certificatesMap = new HashMap<BigInteger, Integer>();

        for (SslCertificate certificate : certificates) {
            certificatesMap.put(certificate.getSerial(), certificate.getPublicKeyHash());
        }

        return certificatesMap;
    }

    private Integer getCertificateSerialHashIfAllowed(X509Certificate x509Certificate) {
        return mAllowedCertificatesMap.get(x509Certificate.getSerialNumber());
    }

    private int getPublicKeyHashCode(X509Certificate cert) {
        return Arrays.hashCode(cert.getPublicKey().getEncoded());
    }

    private boolean isAtLeastOneValid(List<X509Certificate> chain) {

        for (X509Certificate x509Certificate : chain) {

            if (isPublicKeyEqualTo(x509Certificate, getCertificateSerialHashIfAllowed(x509Certificate))) {
                return true;
            }
        }

        return false;
    }

    private boolean isDefaultTrustManagerAvailable() {
        return mDefaultTrustManager != null;
    }

    private boolean isPublicKeyEqualTo(X509Certificate x509Certificate, Integer publicKey) {
        return publicKey != null && publicKey.equals(getPublicKeyHashCode(x509Certificate));
    }

    private void logCertificateData(X509Certificate cert) {
        Log.v(TAG,
                "Server certificate information:\n  Subject DN: " + cert.getSubjectDN() + "\n  Issuer DN: " + cert.getIssuerDN()
                        + "\n  Serial number: " + cert.getSerialNumber() + "\n  Java hash: " + getPublicKeyHashCode(cert));
    }

    private void validateChain(List<X509Certificate> chain) throws CertificateException {

        if (!isAtLeastOneValid(chain)) {
            throw new CertificateException();
        }
    }
}
