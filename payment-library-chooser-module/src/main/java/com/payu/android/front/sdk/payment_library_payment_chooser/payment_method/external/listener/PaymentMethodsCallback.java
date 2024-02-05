package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodCreator;

import java.util.List;

/**
 * Callback used for communicating about received payment methods. Callback will be provided in the
 * {@linkplain PaymentMethodActions:providePaymentMethods method}
 */
public interface PaymentMethodsCallback {

    /**
     * This method should be called after payment methods have been received from Merchant backend. Payment methods should be created using
     * builders provided by {@linkplain PaymentMethodCreator}
     *
     * @param paymentMethods This SDK is supporting only {@linkplain PayByLinkPaymentMethod} and {@linkplain CardPaymentMethod}.
     */
    void onFetched(List<PaymentMethod> paymentMethods);

    /**
     * This method should be called when user is logged out from  applications
     */
    void onClean();

    /**
     * This method should be called when user is logged out from
     * applications and selected method should persist
     */
    void onCleanWithoutRemovingSelectedMethod();
}
