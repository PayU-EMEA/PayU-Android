package com.payu.android.front.sdk.payment_library_core.hashing;

import androidx.annotation.NonNull;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class TokenHasher {

    public String getHash(@NonNull String token) {
        return Hashing.sha256().newHasher().putString(token, StandardCharsets.UTF_8).hash().toString();
    }
}
