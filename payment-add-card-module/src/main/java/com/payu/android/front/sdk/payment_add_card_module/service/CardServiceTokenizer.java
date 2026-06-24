package com.payu.android.front.sdk.payment_add_card_module.service;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenType;

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
     * Call for creating card token
     *
     * @param senderId Merchant POS
     *                 more information @see <a href="http://developers.payu.com/en/restapi.html">
     *                 documentation</a>
     * @param type Token type
     *             <ul>
     *                <li>SINGLE - Card not saved. Token with short lifespan (11 minutes).</li>
     *                <li>SINGLE_LONGTERM - Card not saved.</li>
     *                <li>MULTI - Card saved. - multi-use token will be returned after it is charged via REST API.</li>
     *             </ul>
     */

    void addCard(@NonNull String senderId, @NonNull TokenType type);

    /**
     * Call for creating one time card token
     *
     * @deprecated Use {@link #addCard(String, TokenType)} instead.
     * @param senderId - Merchant POS
     *                 more information @see <a href="http://developers.payu.com/en/restapi.html">
     *                 documentation</a>
     */
    @Deprecated
    void addCardWithAgreement(@NonNull String senderId);

    /**
     * Call for creating one time card token
     *
     * @deprecated Use {@link #addCard(String, TokenType)} instead.
     * @param senderId - Merchant POS
     *                 more information @see <a href="http://developers.payu.com/en/restapi.html">
     *                 documentation</a>
     */
    @Deprecated
    void addCardWithoutAgreement(@NonNull String senderId);
}
