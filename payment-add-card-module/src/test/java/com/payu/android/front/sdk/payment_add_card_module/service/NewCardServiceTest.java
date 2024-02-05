package com.payu.android.front.sdk.payment_add_card_module.service;

import android.content.Context;

import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.presenter.NewCardPresenter;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateResponse;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewCardServiceTest {
    NewCardService objectUnderTest;

    @Mock
    NewCardView view;
    @Mock
    Context context;
    @Mock
    NewCardPresenter presenter;
    @Mock
    CardService cardService;
    @Mock
    Call<TokenCreateResponse> call;


    @Mock
    RetrofitNewCardCallback retrofitNewCardCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(context.getApplicationContext()).thenReturn(context);
        when(cardService.addCard(anyString())).thenReturn(call);
        objectUnderTest = new NewCardService(view, context, presenter, cardService, new Gson(), retrofitNewCardCallback);

    }

    @Test
    public void shouldValidatePositivelyWhenPresenterReturnTrue() {
        //given
        when(presenter.isCardValid()).thenReturn(true);

        //when
        boolean isValid = objectUnderTest.isCardValid();

        //then
        verify(presenter, times(1)).isCardValid();
        assertTrue(isValid);
    }

    @Test
    public void shouldValidateNegativelyWhenPresenterReturnFalse() {
        //given
        when(presenter.isCardValid()).thenReturn(false);

        //when
        boolean isValid = objectUnderTest.isCardValid();

        //then
        verify(presenter, times(1)).isCardValid();
        assertFalse(isValid);
    }

    @Test
    public void shouldNotAddCardWithAgreementWhenCardIsNotValid() {
        //given
        when(presenter.isCardValid()).thenReturn(false);

        //when
        objectUnderTest.addCardWithAgreement("testId");

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(0)).getCardData();
        verify(cardService, times(0)).addCard(anyString());
    }

    @Test
    public void shouldCallAddCardWithAgreementWhenCardIsValid() {
        //given
        Card card = new Card("4111", "123", "10", "2018");
        when(presenter.isCardValid()).thenReturn(true);
        when(presenter.getCardData()).thenReturn(card);
        //when
        objectUnderTest.addCardWithAgreement("testId");

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(1)).getCardData();
        verify(cardService, times(1)).addCard(anyString());
    }


    @Test
    public void shouldNotAddCardWithoutAgreementWhenCardIsNotValid() {
        //given
        when(presenter.isCardValid()).thenReturn(false);

        //when
        objectUnderTest.addCardWithoutAgreement("testId");

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(0)).getCardData();
        verify(cardService, times(0)).addCard(anyString());
    }

    @Test
    public void shouldCallAddCardWithoutAgreementWhenCardIsValid() {
        //given
        Card card = new Card("4111", "123", "10", "2018");
        when(presenter.isCardValid()).thenReturn(true);
        when(presenter.getCardData()).thenReturn(card);

        //when
        objectUnderTest.addCardWithoutAgreement("testId");

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(1)).getCardData();
        verify(cardService, times(1)).addCard(anyString());
    }
}
