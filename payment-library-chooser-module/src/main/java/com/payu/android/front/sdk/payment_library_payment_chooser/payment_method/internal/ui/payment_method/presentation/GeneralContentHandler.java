package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.IconPathProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;

import java.util.List;

public class GeneralContentHandler {
    private static final String GENERAL_CARD_PAYMENT_ID = "GENERAL_CARD_PAYMENT_ID";
    private static final String GENERAL_BANK_TRANSFER_ID = "GENERAL_BANK_TRANSFER_ID";
    private final Translation translation;
    private final IconPathProvider iconPathProvider;
    private final  DynamicCardActionDelegate dynamicCardActionDelegate;
    private final boolean isPBLVisible;

    public GeneralContentHandler(@NonNull Translation translation, @NonNull IconPathProvider iconPathProvider,
                                 DynamicCardActionDelegate dynamicCardActionDelegate, boolean isPBLVisible) {
        this.translation = translation;
        this.iconPathProvider = iconPathProvider;
        this.dynamicCardActionDelegate = dynamicCardActionDelegate;
        this.isPBLVisible = isPBLVisible;
    }

    public void appendGeneralContent(@NonNull List<PaymentMethodModel> paymentMethodModels, boolean pblPaymentMethodsEnabled) {
        PaymentMethodModel generalCardPayment = getGeneralCardPayment();
        if (!paymentMethodModels.contains(generalCardPayment) && dynamicCardActionDelegate.addCardFlow()) {
            paymentMethodModels.add(generalCardPayment);
        }
        PaymentMethodModel bankTransferPayment = getBankTransferPayment(pblPaymentMethodsEnabled);
        if (!paymentMethodModels.contains(bankTransferPayment) && isPBLVisible) {
            paymentMethodModels.add(bankTransferPayment);
        }
    }

    private PaymentMethodModel getGeneralCardPayment() {
        return new PaymentMethodModel.Builder()
                .withTitleContent(translation.translate(TranslationKey.CREDIT_CARD))
                .withDescriptionLabel(translation.translate(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION))
                .withPaymentMethodType(PaymentMethodType.GENERAL_ICON)
                .withId(GENERAL_CARD_PAYMENT_ID)
                .canBeRemoved(false)
                .withImageRes(iconPathProvider.getCardIconPath())
                .isEnabled(true)
                .build();
    }

    private PaymentMethodModel getBankTransferPayment(boolean pblPaymentMethodsEnabled) {
        return new PaymentMethodModel.Builder()
                .withTitleContent(translation.translate(TranslationKey.BANK_TRANSFER))
                .withDescriptionLabel(translation.translate(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION))
                .withPaymentMethodType(PaymentMethodType.GENERAL_ICON)
                .withId(GENERAL_BANK_TRANSFER_ID)
                .canBeRemoved(false)
                .withImageRes(iconPathProvider.getBankIconPath())
                .isEnabled(pblPaymentMethodsEnabled)
                .build();
    }


    public boolean isGeneralBankPayment(PaymentMethodModel paymentMethod) {
        return paymentMethod.getId().equals(GENERAL_BANK_TRANSFER_ID);
    }

    public boolean isGeneralCardPayment(PaymentMethodModel paymentMethod) {
        return paymentMethod.getId().equals(GENERAL_CARD_PAYMENT_ID);
    }
}
