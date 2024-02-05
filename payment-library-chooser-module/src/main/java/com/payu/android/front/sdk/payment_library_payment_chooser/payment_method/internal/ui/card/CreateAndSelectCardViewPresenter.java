package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_add_card_module.service.Error;
import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;

public class CreateAndSelectCardViewPresenter extends BasePresenter<CreateAndSelectCardView> {
    private static final String TAG = CreateAndSelectCardViewPresenter.class.toString();
    private final static String DATE_SEPARATOR = "/";
    private final PaymentMethodRepository paymentMethodRepository;
    private final DynamicCardActionDelegate dynamicCardActionDelegate;
    private final PaymentMethodActions merchantPosId;
    private final CardScannerAPI cardScannerAPI;
    private final boolean shouldScanDate;

    public CreateAndSelectCardViewPresenter(
            @NonNull PaymentMethodRepository paymentMethodRepository,
            @NonNull DynamicCardActionDelegate dynamicCardActionDelegate,
            @NonNull PaymentMethodActions merchantPosId,
            boolean shouldScanDate,
            @Nullable CardScannerAPI cardScannerAPI) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.dynamicCardActionDelegate = dynamicCardActionDelegate;
        this.merchantPosId = merchantPosId;
        this.cardScannerAPI = cardScannerAPI;
        this.shouldScanDate = shouldScanDate;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!dynamicCardActionDelegate.saveAndUseOption()) {
            view.hideSaveAndUse();
        } else {
            view.showSaveAndUse();
        }
    }

    public boolean shouldScanCard() {
        return cardScannerAPI != null;
    }

    public void onAddCardSuccess(CardPaymentMethod cardPaymentMethod) {
        view.hideLoading();
        paymentMethodRepository.addLocalCardPaymentMethod(cardPaymentMethod);
        paymentMethodRepository.updateSelectedMethod(cardPaymentMethod.getValue());
        view.closeWithSuccess();

    }

    public void onCardScanned(String cardNumber, String expirationDate) {
        int notDefined = -1;
        view.setCardNumber(cardNumber);
        if (expirationDate != null && expirationDate.contains(DATE_SEPARATOR)) {
            String date[] = expirationDate.split(DATE_SEPARATOR);
            int month = notDefined;
            int year = notDefined;
            try {
                month = Integer.parseInt(date[0]);
                year = Integer.parseInt(date[1]);
                view.setExpirationDate(month, year);

            } catch (NumberFormatException exception) {
                Log.e(TAG, exception.getMessage());
            }
        }
    }

    public void onAddCardError(Error error) {
        view.hideLoading();
        view.showError(error.getErrorLiteral());
    }

    public void onAddCard(final boolean withAgreement, boolean cardFieldsValid) {
        if (!cardFieldsValid) {
            return;
        }
        merchantPosId.providePosId(posId -> {
            if (withAgreement) {
                view.addCardWithAgreement(posId);
            } else {
                view.addCardWithoutAgreement(posId);
            }
        });
        view.showLoading();
    }

    public CardScannerAPI getScannerAPI() {
        return cardScannerAPI;
    }

    public boolean getScanDate() {
        return shouldScanDate;
    }

}
