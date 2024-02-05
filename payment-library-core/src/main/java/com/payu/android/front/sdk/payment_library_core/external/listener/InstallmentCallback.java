package com.payu.android.front.sdk.payment_library_core.external.listener;

import com.payu.android.front.sdk.payment_library_core.external.model.Installment;

public interface InstallmentCallback {

    /**
     * This method should be called after retrieving if transaction can be divided into installments
     * <p>
     * See <a href="https://developers.payu.com/en/mci.html">Point 3.1</a>
     * <p>
     * <p>
     * See <a href="https://developers.payu.com/en/mci.html">3.2 Fetch installment proposal</a>
     */
    void onFetched(Installment installment);

}
