package com.payu.android.front.sdk.payment_add_card_module.presenter;

import com.payu.android.front.sdk.payment_add_card_module.validation.CardDateValidable;
import com.payu.android.front.sdk.payment_add_card_module.view.CardDate;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardDatePresenterTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    CardDatePresenter objectUnderTest;

    @Mock
    CardDate view;

    @Mock
    CardDateValidable cardDateValidable;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new CardDatePresenter(cardDateValidable, TranslationFactory.getInstance());
        when(view.getDate()).thenReturn("22/10");
    }

    @Test
    public void shouldThrowAnErrorWhenViewWillNotBeProvidedAndGetValidMonthWasCalled() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.getMonth();

        //then
        //should throw an exception
    }

    @Test
    public void shouldThrowAnErrorWhenViewWillNotBeProvidedAndGetValidYearWasCalled() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.getYear();

        //then
        //should throw an exception
    }

    @Test
    public void shouldThrowAnErrorWhenViewWillNotBeProvidedAndSetDateWasCalled() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.setExpirationDate(0, 0);

        //then
        //should throw an exception
    }

    @Test
    public void shouldThrowAnErrorWhenViewWillNotBeProvidedAndValidate() {
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
    public void shouldSetErrorWhenValidationWasIncorrect() {
        //given
        when(cardDateValidable.validate()).thenReturn(false);
        objectUnderTest.takeView(view);

        //when
        boolean isValid = objectUnderTest.validate();

        //then
        verify(view, (times(1))).setErrorState(true);
        assertFalse(isValid);
    }


    @Test
    public void shouldRemoveErrorWhenValidationWasCorrect() {
        //given
        when(cardDateValidable.validate()).thenReturn(true);
        objectUnderTest.takeView(view);

        //when
        boolean isValid = objectUnderTest.validate();

        //then
        verify(view, (times(1))).setErrorState(false);
        assertTrue(isValid);
    }

    @Test
    public void shouldExpendDateWithTwoNumbers() {
        //given
        objectUnderTest.takeView(view);
        String date = "02/02";
        when(view.getDate()).thenReturn(date);

        //when
        objectUnderTest.validate();

        //then
        verify(cardDateValidable).setDate("02", "2002");
    }

    @Test
    public void shouldSetOnFocusChangeListenerOnInit() {
        objectUnderTest.takeView(view);

        verify(view).addValidateOnFocusChangeListener(any(ValidableView.ValidateOnFocusChangeListener.class));
    }
}
