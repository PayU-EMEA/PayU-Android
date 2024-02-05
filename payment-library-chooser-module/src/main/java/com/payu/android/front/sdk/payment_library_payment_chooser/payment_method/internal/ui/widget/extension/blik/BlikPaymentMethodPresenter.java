package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.extension.blik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.OnSelectionPaymentMethodListener;
import com.payu.android.front.sdk.payment_library_payment_methods.model.BlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.GenericBlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

public class BlikPaymentMethodPresenter extends BasePresenter<BlikPaymentMethodView> {
    private static final int BLIK_CODE_LENGTH = 6;

    @Nullable

    public String getBlikCode() {
        String code = view.getBlikCode();
        if (code == null) {
            return null;
        }
        return view.getBlikCode().trim();
    }

    @NonNull
    private OnSelectionPaymentMethodListener onSelectionPaymentMethodListener = new OnSelectionPaymentMethodListener() {
        @Override
        public void onSelectionChange(@Nullable PaymentMethod paymentMethod) {
            bindSelectedPaymentMethod(paymentMethod);
        }
    };

    private void bindSelectedPaymentMethod(@Nullable PaymentMethod selectedPaymentMethod) {
        if (selectedPaymentMethod == null) {
            view.hideInput();
            return;
        }
        if (selectedPaymentMethod instanceof GenericBlikPaymentMethod) {
            view.showInput();
        } else if (selectedPaymentMethod instanceof BlikPaymentMethod) {
            view.showCodeAction();
        } else {
            view.hideInput();
        }
    }

    @NonNull
    public OnSelectionPaymentMethodListener getOnSelectionPaymentMethodListener() {
        return onSelectionPaymentMethodListener;
    }

    public boolean checkProvidedCode() {
        String code = getBlikCode();
        if (code == null) {
            return false;
        }
        return code.trim().length() == BLIK_CODE_LENGTH;

    }
}
