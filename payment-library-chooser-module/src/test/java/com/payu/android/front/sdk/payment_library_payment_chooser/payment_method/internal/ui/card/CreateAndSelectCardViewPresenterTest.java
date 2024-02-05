package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard.DynamicCardActionDelegate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.CardScannerAPI;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodActionsMock;
import com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CreateAndSelectCardViewPresenterTest {
    private CreateAndSelectCardViewPresenter objectUnderTest;
    @Mock
    private PaymentMethodRepository repository;
    @Mock
    private CreateAndSelectCardView view;
    @Mock
    private CardScannerAPI cardScannerAPI;

    @Mock
    DynamicCardActionDelegate dynamicCardActionDelegate;

    private PaymentMethodActions paymentMethodActions;

    @Before
    public void setUp() {
        openMocks(this);
        paymentMethodActions = new PaymentMethodActionsMock(null);
        when(dynamicCardActionDelegate.saveAndUseOption()).thenReturn(true);
        objectUnderTest = new CreateAndSelectCardViewPresenter(repository, dynamicCardActionDelegate, paymentMethodActions,false, cardScannerAPI);
        objectUnderTest.takeView(view);
    }

    @Test
    public void shouldRequestToAddCardWithAgreementWhenRequestedWithTrue() {
        //given

        //when
        objectUnderTest.onAddCard(true, true);

        //then
        verify(view, times(1)).addCardWithAgreement(anyString());
        verify(view, times(0)).addCardWithoutAgreement(anyString());
    }

    @Test
    public void shouldRequestToAddCardWithNoAgreementWhenRequestedWithFalse() {
        //given

        //when
        objectUnderTest.onAddCard(false, true);

        //then
        verify(view, times(1)).addCardWithoutAgreement(anyString());
        verify(view, times(0)).addCardWithAgreement(anyString());
    }

    @Test
    public void shouldAddCardToRepositoryAndCloseViewWhenCardCreatedSuccessfully() {
        //given
        CardPaymentMethod cardPaymentMethod = PaymentMethodsModelTestDataProvider.createCardPaymentMethod();

        //when
        objectUnderTest.onAddCardSuccess(cardPaymentMethod);

        //then
        verify(view, times(1)).closeWithSuccess();
        verify(repository, times(1)).addLocalCardPaymentMethod(cardPaymentMethod);
        verify(repository, times(1)).updateSelectedMethod(cardPaymentMethod.getValue());
    }

}