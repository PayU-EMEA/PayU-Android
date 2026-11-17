package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;

/**
 * Interface used for dynamic POS ID creation used for eg. adding new card. To learn more about POS please check
 * <a href="https://developers.payu.com/europe/docs/get-started/set-up-account/management-panel/#management-panel-point-of-sale">official documentation</a>.
 * This class would be provided in {@linkplain PaymentMethodActions#providePosId(PosIdListener)} method argument.
 */
public interface PosIdListener {

    /**
     * This method should be called with merchant pos id.
     *
     * @param posId - merchant pos id used used for card token creation
     */
    void onPosId(@NonNull String posId);
}
