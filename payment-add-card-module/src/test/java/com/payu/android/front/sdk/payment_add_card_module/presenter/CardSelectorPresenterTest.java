package com.payu.android.front.sdk.payment_add_card_module.presenter;


import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CardSelectorPresenterTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    CardSelectorPresenter objectUnderTest;

    @Mock
    SelectorView view;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new CardSelectorPresenter();
    }

    @Test
    public void shouldThrowAnExceptionWhenViewWasNotProvided() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "View should be set");

        //when
        objectUnderTest.selectIssuer(CardIssuer.UNKNOWN);

        //then
        //should throw an exception
    }


    @Test
    public void shouldChangeAlphaInImageViewToDeselected() {
        //given
        objectUnderTest.takeView(view);
        reset(view);

        //when
        objectUnderTest.selectIssuer(CardIssuer.MAESTRO);

        //then
        verify(view, times(1)).deselect(CardIssuer.MASTER_CARD);
        verify(view, times(0)).select(CardIssuer.MASTER_CARD);

        verify(view, times(1)).deselect(CardIssuer.MAESTRO);
        verify(view, times(1)).select(CardIssuer.MAESTRO);

        verify(view, times(1)).deselect(CardIssuer.VISA);
        verify(view, times(0)).select(CardIssuer.VISA);

    }

    @Test
    public void shouldSelectOnlyOneViewAndOtherShouldBeDeselected() {
        //given
        objectUnderTest.takeView(view);
        reset(view);

        //when
        objectUnderTest.selectIssuer(CardIssuer.MAESTRO);
        objectUnderTest.selectIssuer(CardIssuer.MASTER_CARD);
        objectUnderTest.selectIssuer(CardIssuer.VISA);

        //then

        verify(view, times(3)).deselect(CardIssuer.MASTER_CARD);
        verify(view, times(1)).select(CardIssuer.MASTER_CARD);

        verify(view, times(3)).deselect(CardIssuer.MAESTRO);
        verify(view, times(1)).select(CardIssuer.MAESTRO);

        verify(view, times(3)).deselect(CardIssuer.VISA);
        verify(view, times(1)).select(CardIssuer.VISA);


    }

    @Test
    public void shouldSelectAllWhenCardIssuerIsUnknown() {
        //given
        objectUnderTest.takeView(view);
        reset(view);

        //when
        objectUnderTest.selectIssuer(CardIssuer.MAESTRO);
        objectUnderTest.selectIssuer(CardIssuer.MASTER_CARD);
        objectUnderTest.selectIssuer(CardIssuer.VISA);
        objectUnderTest.selectIssuer(CardIssuer.UNKNOWN);

        //then
        verify(view, times(3)).deselect(CardIssuer.MASTER_CARD);
        verify(view, times(2)).select(CardIssuer.MASTER_CARD);

        verify(view, times(3)).deselect(CardIssuer.MAESTRO);
        verify(view, times(2)).select(CardIssuer.MAESTRO);

        verify(view, times(3)).deselect(CardIssuer.VISA);
        verify(view, times(2)).select(CardIssuer.VISA);

    }
}
