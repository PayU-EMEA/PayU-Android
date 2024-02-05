package com.payu.android.front.sdk.payment_add_card_module.service;

import androidx.annotation.NonNull;

/**
 * This interface is used to tokenize a Card at PayU backend
 */
public interface CardServiceTokenizer {
    /**
     * Check if all provided data are valid
     *
     * @return true if all data for tokenization are valid
     */
    boolean isCardValid();

    /**
     * Call for creating card token, after fist using, this card will be show in retrieve request <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/paymethods-api-endpoint/retrieve-paymethods-and-tokens>paymethods API endpoint
     * documentation</a>
     *
     * @param senderId - Merchant POS
     *                 more information @see <a href="http://developers.payu.com/en/restapi.html">documentation</a>
     */

    void addCardWithAgreement(@NonNull String senderId);

    /**
     * Call for creating one time card token
     *
     * @param senderId - Merchant POS
     *                 more information @see <a href="http://developers.payu.com/en/restapi.html">
     *                 documentation</a>
     */
    void addCardWithoutAgreement(@NonNull String senderId);
}
