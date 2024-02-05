package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_payment_methods.model.BlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PexPaymentMethod;

import static com.google.common.base.Preconditions.checkNotNull;

public class SelectedPaymentMethodConverter {
    @NonNull
    private final Translation translation;

    public SelectedPaymentMethodConverter(@NonNull Translation translation) {
        this.translation = checkNotNull(translation);
    }

    public SelectedPaymentMethodModel convert(@NonNull PaymentMethod selectedPaymentMethod) {
        String paymentMethodValue = selectedPaymentMethod.getValue();
        String brandImageUrl = selectedPaymentMethod.getBrandImageUrl();
        String header;
        String content = null;
        if (selectedPaymentMethod instanceof CardPaymentMethod) {
            header = translation.translate(TranslationKey.CREDIT_CARD);
            content = ((CardPaymentMethod) selectedPaymentMethod).getCardNumberMasked();
        } else if (selectedPaymentMethod instanceof PayByLinkPaymentMethod) {
            header = ((PayByLinkPaymentMethod) selectedPaymentMethod).getName();
        } else if (selectedPaymentMethod instanceof BlikPaymentMethod) {
            header = translation.translate(TranslationKey.BLIK_PAYMENT_NAME);
        } else if (selectedPaymentMethod instanceof PexPaymentMethod) {
            header = ((PexPaymentMethod) selectedPaymentMethod).getName();
            content = ((PexPaymentMethod) selectedPaymentMethod).getAccountNumber();
        } else {
            throw new IllegalArgumentException(String.format("Payment method %s not supported", selectedPaymentMethod.getClass().getName()));
        }
        return new SelectedPaymentMethodModel(brandImageUrl, header, content, paymentMethodValue);
    }
}
