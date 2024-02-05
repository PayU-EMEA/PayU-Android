package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation;

import androidx.lifecycle.LiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view.PayByLinkView;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases.RetrievePblPaymentMethods;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PayByLinkPresenterTest {
    private PayByLinkPresenter objectUnderTest;
    @Mock
    private RetrievePblPaymentMethods useCase;
    @Mock
    private PblModelConverter converter;
    @Mock
    private PayByLinkView view;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new PayByLinkPresenter(useCase, converter);
    }

    @Test
    public void shouldBindPayByLinksWhenViewTaken() {
        //given

        // when
        objectUnderTest.takeView(view);

        // then
        verify(view, times(1)).bindToPayByLinkModel(any(LiveData.class));
    }

    @Test
    public void shouldSetPaymentMethodAndCloseViewWhenPaymentMethodIsSelected() {
        //given
        objectUnderTest.takeView(view);
        PayByLinkModel mock = mock(PayByLinkModel.class);
        String valid_id = "VALID_ID";
        when(mock.getId()).thenReturn(valid_id);

        //when
        objectUnderTest.onPblSelected(mock);

        //then
        verify(view).closeWithSuccess();
        verify(useCase).updateSelectedMethod(valid_id);
    }
}
