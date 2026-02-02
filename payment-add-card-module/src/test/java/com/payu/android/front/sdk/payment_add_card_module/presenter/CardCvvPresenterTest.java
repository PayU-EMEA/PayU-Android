package com.payu.android.front.sdk.payment_add_card_module.presenter;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class CardCvvPresenterTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    CardCvvPresenter objectUnderTest;

    @Mock
    SelectorCvv selectorCvv;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new CardCvvPresenter();
    }

    @Test
    public void shouldThrowAnExceptionWhenViewWasNotProvidedAndGetCvvCodeWasCalled() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.getCvvCode();

        //then
        //should throw an exception
    }

    @Test
    public void shouldThrowAnExceptionWhenViewWasNotProvidedAndValidateWasCalled() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.validate();

        //then
        //should throw an exception
    }

    @Test
    public void shouldValidateCorrectlyWhenCorrectDataWasProvidedAndNoIssuerIsSet() {
        //given
        Mockito.when(selectorCvv.getCvvCode()).thenReturn("123");
        objectUnderTest.takeView(selectorCvv);

        //when
        boolean isValidateCorrectly = objectUnderTest.validate();

        //then
        assertTrue(isValidateCorrectly);
    }

    @Test
    public void shouldValidateCorrectlyWhenCorrectDataWasProvided() {
        //given
        Mockito.when(selectorCvv.getCvvCode()).thenReturn("123");
        objectUnderTest.takeView(selectorCvv);

        //when
        boolean isValidateCorrectly = objectUnderTest.validate();

        //then
        assertTrue(isValidateCorrectly);
    }


    @Test
    public void shouldNotValidateCorrectlyWhenCorrectDataWasProvided() {
        //given

        Mockito.when(selectorCvv.getCvvCode()).thenReturn("12");
        objectUnderTest.takeView(selectorCvv);

        //when
        boolean isValidateCorrectly = objectUnderTest.validate();

        //then
        assertFalse(isValidateCorrectly);
    }

    @Test
    public void shouldSetOnFocusChangeListenerOnInit() {
        objectUnderTest.takeView(selectorCvv);

        verify(selectorCvv).addValidateOnFocusChangeListener(any(ValidableView.ValidateOnFocusChangeListener.class));
    }
}
