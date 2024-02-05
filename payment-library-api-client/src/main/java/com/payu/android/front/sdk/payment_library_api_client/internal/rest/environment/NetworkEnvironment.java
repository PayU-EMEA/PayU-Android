package com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment;

import android.content.Context;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl.SslCertificate;

import java.security.KeyStore;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class NetworkEnvironment implements RestEnvironment {
    /**
     * Those certificates are added for production & sandbox environment. For network/security purpose we are prepared to change between mentioned
     * certificates
     */
    protected List<SslCertificate> commonCertificates =
            newArrayList(new SslCertificate(1907389399, "10944719598952040374951832963794454346",
                            SslCertificate.DECIMAL_BASE, "certs/DigiCertGlobalRootCA.crt"),
                    new SslCertificate(1954573457, "4293743540046975378534879503202253541",
                            SslCertificate.DECIMAL_BASE, "certs/DigiCertGlobalRootG2.crt"),
                    new SslCertificate(-490239304, "3553400076410547919724730734378100087",
                            SslCertificate.DECIMAL_BASE, "certs/DigiCertHighAssuranceEVRootCA.crt"),
                    new SslCertificate(1927689311, "1246989352",
                            SslCertificate.DECIMAL_BASE, "certs/entrustG2Ca.cer"),
                    new SslCertificate(-24664157, "38055854624439058553644166412003131803",
                            SslCertificate.DECIMAL_BASE, "certs/PayURoot.crt"));

    @Override
    public Optional<List<SslCertificate>> getAllowedCertificates() {
        return Optional.absent();
    }

    @Override
    public KeyStore getClientKeyStore(Context context) {
        return null;
    }

    @Override
    public String getClientKeyStorePassword() {
        return null;
    }

    @Override
    public boolean isMockingNetwork() {
        return false;
    }

}
