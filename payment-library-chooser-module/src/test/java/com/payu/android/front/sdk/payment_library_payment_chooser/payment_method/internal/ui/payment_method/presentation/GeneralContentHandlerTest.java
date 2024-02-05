package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPaymentMethodModel;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPaymentMethodModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.IconPathProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class GeneralContentHandlerTest {
    private static final String CREDIT_CARD_NAME = "CREDIT_CARD_NAME";
    private static final String BANK_TRANSFER = "BANK_TRANSFER";
    private static final String PAYMENT_METHOD_CARD_DESCRIPTION = "PAYMENT_METHOD_CARD_DESCRIPTION";
    private static final String PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION = "PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION";
    private static final int MOCK_ICON_RES = 1;
    private GeneralContentHandler objectUnderTest;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    IconPathProvider iconPathProvider;
    @Mock
    private Translation translation;
    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private DynamicCardActionDelegate dynamicCardActionDelegate;

    //TODO: inFreeTime add test for setting card as invisible
    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new GeneralContentHandler(translation, iconPathProvider, dynamicCardActionDelegate, true);

        MutableLiveData<List<PaymentMethod>> paymentMethods = new MutableLiveData<>();
        paymentMethods.setValue(new ArrayList<PaymentMethod>());
        when(paymentMethodRepository.getPayments()).thenReturn(paymentMethods);
        when(dynamicCardActionDelegate.addCardFlow()).thenReturn(true);
        when(iconPathProvider.getBankIconPath()).thenReturn(MOCK_ICON_RES);
        when(iconPathProvider.getCardIconPath()).thenReturn(MOCK_ICON_RES);

        when(translation.translate(TranslationKey.CREDIT_CARD)).thenReturn(CREDIT_CARD_NAME);
        when(translation.translate(TranslationKey.PAYMENT_METHOD_CARD_DESCRIPTION)).thenReturn(PAYMENT_METHOD_CARD_DESCRIPTION);
        when(translation.translate(TranslationKey.BANK_TRANSFER)).thenReturn(BANK_TRANSFER);
        when(translation.translate(TranslationKey.PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION)).thenReturn(PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION);
    }

    @Test
    public void shouldAppendListWithBankTransferAndCardPayment() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();
        paymentMethodModels.add(createPblPaymentMethodModel());
        paymentMethodModels.add(createCardPaymentMethodModel());

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        assertThat(paymentMethodModels.size()).isEqualTo(4);
        PaymentMethodModel cardPaymentMethodModel = paymentMethodModels.get(2);
        assertThat(cardPaymentMethodModel.getTitleContent()).isEqualTo(CREDIT_CARD_NAME);
        assertThat(cardPaymentMethodModel.getDescriptionLabel()).isEqualTo(PAYMENT_METHOD_CARD_DESCRIPTION);
        assertThat(cardPaymentMethodModel.getPaymentMethodType()).isEqualTo(PaymentMethodType.GENERAL_ICON);

        PaymentMethodModel bankPaymentMethodModel = paymentMethodModels.get(3);
        assertThat(bankPaymentMethodModel.getTitleContent()).isEqualTo(BANK_TRANSFER);
        assertThat(bankPaymentMethodModel.getDescriptionLabel()).isEqualTo(PAYMENT_METHOD_BANK_TRANSFER_DESCRIPTION);
        assertThat(bankPaymentMethodModel.getPaymentMethodType()).isEqualTo(PaymentMethodType.GENERAL_ICON);
    }

    @Test
    public void shouldProperlyIdentifyBankTransfer() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        PaymentMethodModel bankTransferMethod = paymentMethodModels.get(1);
        assertThat(objectUnderTest.isGeneralBankPayment(bankTransferMethod)).isTrue();
        assertThat(objectUnderTest.isGeneralCardPayment(bankTransferMethod)).isFalse();
    }

    @Test
    public void shouldProperlyIdentifyCardPayment() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        PaymentMethodModel cardPaymentMethod = paymentMethodModels.get(0);
        assertThat(objectUnderTest.isGeneralBankPayment(cardPaymentMethod)).isFalse();
        assertThat(objectUnderTest.isGeneralCardPayment(cardPaymentMethod)).isTrue();
    }

    @Test
    public void shouldAppendGeneralMethodsOnlyOnce() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        assertThat(paymentMethodModels.size()).isEqualTo(2);
    }

    @Test
    public void shouldAppendOnlyCardPayment() {
        //given
        GeneralContentHandler objectUnderTest = new GeneralContentHandler(translation, iconPathProvider, dynamicCardActionDelegate, false);
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();
        when(dynamicCardActionDelegate.addCardFlow()).thenReturn(true);

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        assertThat(paymentMethodModels.size()).isEqualTo(1);
        PaymentMethodModel cardPaymentMethod = paymentMethodModels.get(0);
        assertThat(objectUnderTest.isGeneralBankPayment(cardPaymentMethod)).isFalse();
        assertThat(objectUnderTest.isGeneralCardPayment(cardPaymentMethod)).isTrue();
    }

    @Test
    public void shouldAppendOnlyBankTransferPayment() {
        //given
        GeneralContentHandler objectUnderTest = new GeneralContentHandler(translation, iconPathProvider, dynamicCardActionDelegate, true);
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();
        when(dynamicCardActionDelegate.addCardFlow()).thenReturn(false);

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        assertThat(paymentMethodModels.size()).isEqualTo(1);
        PaymentMethodModel bankTransferMethod = paymentMethodModels.get(0);
        assertThat(objectUnderTest.isGeneralBankPayment(bankTransferMethod)).isTrue();
        assertThat(objectUnderTest.isGeneralCardPayment(bankTransferMethod)).isFalse();
    }

    @Test
    public void shouldDisableBankTransferPaymentMethodWhenParameterIsFalse() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, false);

        //then
        PaymentMethodModel bankTransferMethod = paymentMethodModels.get(1);
        assertThat(bankTransferMethod.isEnabled()).isFalse();
    }

    @Test
    public void shouldEnableBankTransferPaymentMethodWhenParameterIsTrue() {
        //given
        List<PaymentMethodModel> paymentMethodModels = new ArrayList<>();

        //when
        objectUnderTest.appendGeneralContent(paymentMethodModels, true);

        //then
        PaymentMethodModel bankTransferMethod = paymentMethodModels.get(1);
        assertThat(bankTransferMethod.isEnabled()).isTrue();
    }
}