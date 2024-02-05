package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class SelectedPaymentMethodPresenterTest {

    private SelectedPaymentMethodPresenter objectUnderTest;
    @Mock
    private PaymentMethodRepository repository;
    @Mock
    private SelectedPaymentMethodConverter converter;
    @Mock
    private SelectedPaymentMethodView view;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new SelectedPaymentMethodPresenter(repository, converter);
    }

    @Test
    public void shouldNavigateToPaymentChooserWhenSelectedMethodClicked() {
        //given
        objectUnderTest.takeView(view);

        //when
        objectUnderTest.onSelectPaymentMethodClick();

        //then
        verify(view, times(1)).showPaymentSelector();
    }
}
