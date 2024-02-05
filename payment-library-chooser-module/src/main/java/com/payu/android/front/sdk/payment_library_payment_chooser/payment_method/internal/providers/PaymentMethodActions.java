package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core.external.listener.InstallmentCallback;
import com.payu.android.front.sdk.payment_library_core.external.model.Installment;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.NotImplementedException;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PosIdListener;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

/**
 * Base class responsible for communication between PayU SDK and Merchant application. This class is neccessary for the payment-chooser-module
 * to work correctly.
 */
public abstract class PaymentMethodActions {
    @NonNull
    private Context context;

    public PaymentMethodActions(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    protected Context getContext() {
        return context;
    }

    /**
     * This method will be called by the PayU SDK every time, when Payment methods are needed. After fetching available payment methods from PayU
     * backend, callback's {@linkplain PaymentMethodsCallback:onFetched()} method has to be called. To ensure proper PayU SDK behaviour, the
     * response from backend has to be parsed as-is.
     * <p>
     * PayU light SDK is supporting only {@linkplain PayByLinkPaymentMethod} and {@linkplain CardPaymentMethod}.
     *
     * @param callback - interface, which need to be notified with fetched payment methods
     */
    public abstract void providePaymentMethods(@NonNull PaymentMethodsCallback callback);

    /**
     * Method called when PaymentMethod has been removed by the user. Provided paymentMethod can only be of a type {@linkplain CardPaymentMethod}.
     * It should be removed from the <a href="https://payu21.docs.apiary.io/#introduction/integration-flow">PayU backend</a> using 'delete token'
     * method.
     *
     * @param paymentMethod - PaymentMethod to be removed
     */
    public abstract void onPaymentMethodRemoved(@NonNull PaymentMethod paymentMethod);

    /**
     * This method is not required when add card option is disabled in configuration. {@linkplain PosIdListener#onPosId(String)} provided in argument needs to
     * be called when posId is available.
     * <p>
     * When overriding this method, make sure NOT to call super method.
     *
     * @param posIdListener - Listener to be called when posId is available
     */
    public void providePosId(@NonNull PosIdListener posIdListener) {
        throw new NotImplementedException(
                "Method providePosId has to be overridden if you want to use Add card functionality. If this method is overridden, make sure NOT to " +
                        "call super method");
    }

    /**
     * This method will be called by the PayU SDK every time, when Bliks will be needed. After creating an OCR with proposed Saved Bliks to end Payment Process
     * backend, callback's {@linkplain PaymentMethodsCallback:onFetched()} method has to be called. To ensure proper PayU SDK behaviour, the
     * response from backend has to be parsed as-is.
     * <p>
     * PayU light SDK is supporting only {@linkplain PayByLinkPaymentMethod} and {@linkplain CardPaymentMethod}.
     *
     * @param callback - interface, which need to be notified with fetched payment methods
     */
    public void provideBlikPaymentMethods(@NonNull PaymentMethodsCallback callback) {
        throw new NotImplementedException("This method has to be overridden if you want to use new Blik payments");
    }

    /**
     * This method will be called by the PayU SDK every time, when Installments will be needed. After checking transaction
     * backend, callback's {@linkplain InstallmentCallback:onFetched()} method has to be called. To ensure proper PayU SDK behaviour, the
     * response from backend has to be parsed using builder pattern {@link com.payu.android.front.sdk.payment_library_core.external.model.Installment.Builder} &&
     * {@link com.payu.android.front.sdk.payment_library_core.external.model.InstallmentOption.Builder}
     * <p>
     *
     *
     * @param callback - interface, which need to be notified with fetched payment methods
     */
    public void provideInstallments(@NonNull InstallmentCallback callback) {
        throw new NotImplementedException("This method hast to be overridden if you want to use Installment Options");
    }
}
