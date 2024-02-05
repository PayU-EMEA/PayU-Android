package com.payu.android.front.sdk.payment_add_card_module.service;

import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;

/**
 * Information about tokenizing provided card
 */
public interface NewCardCallback {

    /**
     * Return one time token for card  payment
     *
     * @param cardPaymentMethod - payment method which can be used to proceed with payment process
     */
    void onSuccess(CardPaymentMethod cardPaymentMethod);

    /**
     * Return an error that was passed from network or from PayU servers
     * {@linkplain Error}
     * PayUStatusCodes{@linkplain com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.OpenPayuStatusCode}
     *
     * @param error - information about current issue with tokenize the card
     */
    void onError(Error error);
}
