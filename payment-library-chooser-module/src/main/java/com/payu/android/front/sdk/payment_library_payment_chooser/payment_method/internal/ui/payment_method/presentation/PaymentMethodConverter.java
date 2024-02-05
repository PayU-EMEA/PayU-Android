package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core.util.StringUtils;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;
import com.payu.android.front.sdk.payment_library_payment_methods.model.BlikAmbiguityPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.BlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.GenericBlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PexPaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodConverter {
    @NonNull
    private final Translation translation;

    public PaymentMethodConverter(@NonNull Translation translation) {
        this.translation = translation;
    }

    @NonNull
    public List<PaymentMethodModel> convert(@NonNull List<PaymentMethod> paymentMethods) {
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod instanceof PayByLinkPaymentMethod) {
                paymentMethodModels.add(convertPbl((PayByLinkPaymentMethod) paymentMethod));
            } else if (paymentMethod instanceof CardPaymentMethod) {
                paymentMethodModels.add(convertCardPayment((CardPaymentMethod) paymentMethod));
            } else if (paymentMethod instanceof GenericBlikPaymentMethod) {
                paymentMethodModels.add(convertGenericBlikTokenPayment((GenericBlikPaymentMethod) paymentMethod));
            } else if (paymentMethod instanceof BlikPaymentMethod) {
                paymentMethodModels.add(convertBlikTokenPayment((BlikPaymentMethod) paymentMethod));
            } else if (paymentMethod instanceof BlikAmbiguityPaymentMethod) {
                paymentMethodModels.add(convertBlikAmbiguityPayment((BlikAmbiguityPaymentMethod) paymentMethod));
            } else if (paymentMethod instanceof PexPaymentMethod) {
                paymentMethodModels.add(convertPexPayment((PexPaymentMethod) paymentMethod));
            }
        }
        return paymentMethodModels;
    }

    @NonNull
    private PaymentMethodModel convertPbl(@NonNull PayByLinkPaymentMethod model) {
        boolean canBeRemoved = model.getPaymentType() == PaymentType.PBL;
        return new PaymentMethodModel.Builder().withTitleContent(model.getName())
                .withId(model.getValue())
                .canBeRemoved(canBeRemoved)
                .withPaymentMethodType(model.getPaymentType() == PaymentType.GOOGLE_PAY ? PaymentMethodType.GOOGLE_PAY : PaymentMethodType.PBL)
                .withImageUrl(model.getBrandImageUrl())
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    @NonNull
    private PaymentMethodModel convertCardPayment(@NonNull CardPaymentMethod model) {
        return new PaymentMethodModel.Builder().withTitleLabel(translation.translate(TranslationKey.CARD_NAME))
                .withTitleContent(model.getCardNumberMasked())
                .withDescriptionLabel(translation.translate(TranslationKey.CARD_EXPIRATION_DATE))
                .withImageUrl(model.getBrandImageUrl())
                .withDescriptionContent(createCardExpirationDate(model))
                .canBeRemoved(true)
                .withId(model.getValue())
                .withPaymentMethodType(PaymentMethodType.CARD)
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    @NonNull
    private PaymentMethodModel convertBlikTokenPayment(@NonNull BlikPaymentMethod model) {
        return new PaymentMethodModel.Builder().withTitleContent(translation.translate(TranslationKey.BLIK_PAYMENT_NAME))
                .withDescriptionLabel(translation.translate(TranslationKey.BLIK_DEFINED_PAYMENT_DESCRIPTION))
                .withImageUrl(model.getBrandImageUrl())
                .canBeRemoved(false)
                .withId(model.getValue())
                .withPaymentMethodType(PaymentMethodType.BLIK_TOKENS)
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    @NonNull
    private PaymentMethodModel convertGenericBlikTokenPayment(@NonNull GenericBlikPaymentMethod model) {
        return new PaymentMethodModel.Builder().withTitleContent(translation.translate(TranslationKey.BLIK_PAYMENT_NAME))
                .withDescriptionLabel(translation.translate(TranslationKey.BLIK_NOT_DEFINED_PAYMENT_DESCRIPTION))
                .withImageUrl(model.getBrandImageUrl())
                .canBeRemoved(false)
                .withId(model.getValue())
                .withPaymentMethodType(PaymentMethodType.GENERIC_BLIK)
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    @NonNull
    private PaymentMethodModel convertBlikAmbiguityPayment(@NonNull BlikAmbiguityPaymentMethod model) {
        return new PaymentMethodModel.Builder()
                .withTitleContent(model.getAppLabel())
                .withDescriptionLabel(translation.translate(TranslationKey.BLIK_AMBIGUITY_DESCRIPTION))
                .withImageUrl(model.getBrandImageUrl())
                .canBeRemoved(false)
                .withId(model.getValue())
                .withPaymentMethodType(PaymentMethodType.BLIK_AMBIGUITY)
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    @NonNull
    private PaymentMethodModel convertPexPayment(@NonNull PexPaymentMethod model) {
        return new PaymentMethodModel.Builder()
                .withTitleContent(model.getName())
                .withDescriptionLabel(model.getAccountNumber())
                .withImageUrl(model.getBrandImageUrl())
                .withId(model.getValue())
                .canBeRemoved(true)
                .withPaymentMethodType(PaymentMethodType.PEX)
                .isEnabled(model.getStatus() == PaymentMethodStatus.ENABLED)
                .build();
    }

    private String createCardExpirationDate(CardPaymentMethod model) {
        return StringUtils.createCardExpirationString(model.getCardExpirationMonth(), model.getCardExpirationYear());
    }
}
