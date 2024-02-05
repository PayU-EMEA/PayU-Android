package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;

public class TokenMatcher {
    @Nullable
    private final String tokenHashed;
    @NonNull
    private final TokenHasher tokenHasher;

    public TokenMatcher(@Nullable String tokenHashed, @NonNull TokenHasher tokenHasher) {
        this.tokenHashed = tokenHashed;
        this.tokenHasher = tokenHasher;
    }

    public boolean isMatching(@NonNull String token) {
        return tokenHasher.getHash(token).equals(tokenHashed);
    }
}
