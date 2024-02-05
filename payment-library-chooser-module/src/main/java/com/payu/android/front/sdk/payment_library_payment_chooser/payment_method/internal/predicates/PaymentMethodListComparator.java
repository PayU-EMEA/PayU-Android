package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates;

import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;

import java.util.Comparator;

import static com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus.ENABLED;

public class PaymentMethodListComparator implements Comparator<PaymentMethod> {
    @Nullable
    private final PaymentMethod selectedPaymentMethod;

    public PaymentMethodListComparator(@Nullable PaymentMethod selectedPaymentMethod) {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    @Override
    public int compare(PaymentMethod o1, PaymentMethod o2) {

        if (o1.equals(selectedPaymentMethod)) {
            return -1;
        } else if (o2.equals(selectedPaymentMethod)) {
            return 1;
        } else if (o1.getStatus() == ENABLED && o2.getStatus() != ENABLED) {
            return -1;
        } else if (o1.getStatus() != ENABLED && o2.getStatus() == ENABLED) {
            return 1;
        } else if (o1.getPaymentType() == PaymentType.GOOGLE_PAY && o2.getPaymentType() != PaymentType.BLIK_GENERIC && notSelected(o1, o2)) {
            return -1;
        } else if (o2.getPaymentType() == PaymentType.GOOGLE_PAY && o1.getPaymentType() != PaymentType.BLIK_GENERIC && notSelected(o1, o2)) {
            return 1;
        } else if (o1.getPaymentType() == PaymentType.BLIK_TOKENS && notSelected(o1, o2)) {
            return -1;
        } else if (o2.getPaymentType() == PaymentType.BLIK_TOKENS && notSelected(o1, o2)) {
            return 1;
        } else if (o1.getPaymentType() == PaymentType.BLIK_GENERIC && !o1.equals(selectedPaymentMethod) && notSelected(o1, o2)) {
            return 1;
        } else if (o2.getPaymentType() == PaymentType.BLIK_GENERIC && !o2.equals(selectedPaymentMethod) && notSelected(o1, o2)) {
            return -1;
        }
        //We do not want to apply sort operation to Not selected payment methods other than GooglePay
        return 0;
    }

    private boolean notSelected(PaymentMethod o1, PaymentMethod o2) {
        return (!o1.equals(selectedPaymentMethod)) && (!o2.equals(selectedPaymentMethod));
    }
}
