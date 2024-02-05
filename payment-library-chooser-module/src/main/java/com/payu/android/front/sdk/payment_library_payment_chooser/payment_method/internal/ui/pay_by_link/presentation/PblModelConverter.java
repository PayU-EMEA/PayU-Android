package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus;

import java.util.ArrayList;
import java.util.List;

public class PblModelConverter {

    @NonNull
    public List<PayByLinkModel> convert(@NonNull List<PaymentMethod> paymentMethods) {
        List<PayByLinkModel> pblModels = new ArrayList<>();
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod instanceof PayByLinkPaymentMethod) {
                pblModels.add(convertPbl((PayByLinkPaymentMethod) paymentMethod));
            }
        }
        return pblModels;
    }

    @NonNull
    private PayByLinkModel convertPbl(@NonNull PayByLinkPaymentMethod model) {
        return new PayByLinkModel(model.getName(), model.getValue(), model.getBrandImageUrl(), model.getStatus() == PaymentMethodStatus.ENABLED);
    }
}
