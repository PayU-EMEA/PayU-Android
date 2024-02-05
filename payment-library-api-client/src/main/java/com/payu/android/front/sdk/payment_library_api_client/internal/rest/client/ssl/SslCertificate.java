package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigInteger;
import java.util.Arrays;

public class SslCertificate {

    public static final int DECIMAL_BASE = 10;
    public static final int HEX_BASE = 16;
    private final int publicKeyHash;
    private final String serial;
    private final int serialRadix;
    @Nullable
    private final String certPath;

    public SslCertificate(String publicKeyHash, String serial) {
        this(Arrays.hashCode(publicKeyHash.getBytes()), serial);
    }

    public SslCertificate(int publicKeyHashCode, String serial) {
        this(publicKeyHashCode, serial, HEX_BASE);
    }

    public SslCertificate(int publicKeyHashCode, String serial, int radix) {
        this(publicKeyHashCode, serial, radix, null);
    }

    public SslCertificate(int publicKeyHashCode, @NonNull String serial, int radix, @Nullable String certPath) {
        this.publicKeyHash = publicKeyHashCode;
        this.serial = serial;
        this.serialRadix = radix;
        this.certPath = certPath;
    }

    @Nullable
    public String getCertPath() {
        return certPath;
    }

    int getPublicKeyHash() {
        return publicKeyHash;
    }

    BigInteger getSerial() {
        return new BigInteger(serial, serialRadix);
    }
}
