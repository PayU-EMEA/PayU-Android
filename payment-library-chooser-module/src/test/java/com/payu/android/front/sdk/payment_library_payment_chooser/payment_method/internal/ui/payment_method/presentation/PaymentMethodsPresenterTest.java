package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view.PaymentMethodsView;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePaymentMethodsList;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePblPaymentMethods;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PaymentMethodsPresenterTest {
    private PaymentMethodsPresenter objectUnderTest;
    @Mock
    private PaymentMethodConverter paymentMethodConverter;
    @Mock
    private RetrievePaymentMethodsList paymentMethodsUseCase;
    @Mock
    private RetrievePblPaymentMethods pblPaymentMethodsUseCase;
    @Mock
    private GeneralContentHandler generalContentHandler;
    @Mock
    private PaymentMethodsView view;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new PaymentMethodsPresenter(paymentMethodsUseCase, pblPaymentMethodsUseCase, paymentMethodConverter, generalContentHandler);

        when(paymentMethodsUseCase.getPaymentMethods()).thenReturn(new MutableLiveData<List<PaymentMethod>>());
        when(pblPaymentMethodsUseCase.getPaymentMethods()).thenReturn(new MutableLiveData<List<PaymentMethod>>());
    }

    @Test
    public void shouldBindPaymentMethodsWhenViewRegistered() {
        //given

        //when
        objectUnderTest.takeView(view);

        //then
        verify(view, times(1)).bindToPaymentMethods(any(LiveData.class));
    }

    @Test
    public void shouldShowPayByLinkScreenWhenBankTransferMethodSelected() {
        //given
        objectUnderTest.takeView(view);
        PaymentMethodModel mock = mock(PaymentMethodModel.class);
        when(generalContentHandler.isGeneralBankPayment(mock)).thenReturn(true);

        //when
        objectUnderTest.onPaymentMethodSelected(mock);

        //then
        verify(view, times(1)).showBankPaymentsScreen();
    }

    @Test
    public void shouldNotShowPayByLinkScreenWhenOtherPaymentMethodWasSelected() {
        //given
        objectUnderTest.takeView(view);
        PaymentMethodModel mock = mock(PaymentMethodModel.class);
        when(generalContentHandler.isGeneralBankPayment(mock)).thenReturn(false);

        //when
        objectUnderTest.onPaymentMethodSelected(mock);

        //then
        verify(view, times(0)).showBankPaymentsScreen();
    }

    @Test
    public void shouldCloseViewWhenPayByLinkHasBeenSelected() {
        //given
        objectUnderTest.takeView(view);

        //when
        objectUnderTest.onPayByLinkSelectionSuccessful();

        //then
        verify(view, times(1)).close();
    }

    @Test
    public void shouldSetSelectedMethodInRepositoryAndCloseViewWhenProperMethodIsSelected() {
        //given
        objectUnderTest.takeView(view);
        PaymentMethodModel mock = mock(PaymentMethodModel.class);
        when(generalContentHandler.isGeneralBankPayment(mock)).thenReturn(false);
        when(generalContentHandler.isGeneralCardPayment(mock)).thenReturn(false);
        String valid_id = "VALID_ID";
        when(mock.getId()).thenReturn(valid_id);

        //when
        objectUnderTest.onPaymentMethodSelected(mock);

        //then
        verify(paymentMethodsUseCase, times(1)).updateSelectedMethod(valid_id);
        verify(view, times(1)).close();

    }
}