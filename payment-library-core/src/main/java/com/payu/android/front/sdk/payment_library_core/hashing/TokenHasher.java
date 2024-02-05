package com.payu.android.front.sdk.payment_library_core.hashing;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import javax.annotation.Nonnull;

public class TokenHasher {

    public String getHash(@Nonnull String token) {
        return Hashing.sha256().newHasher().putString(token, Charsets.UTF_8).hash().toString();
    }
}
