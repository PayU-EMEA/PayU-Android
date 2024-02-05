package com.payu.android.front.sdk.payment_add_card_module.service;

import androidx.annotation.NonNull;

public interface InternalCardServiceTokenizer extends CardServiceTokenizer {
    /**
     * Hide payUTerms on specific view
     *
     * @param packageName - package name of class that request hiding PayU footer
     * @return true if payUTerms was hidden
     */
    boolean shouldHidePayUTerms(@NonNull String packageName);
}
