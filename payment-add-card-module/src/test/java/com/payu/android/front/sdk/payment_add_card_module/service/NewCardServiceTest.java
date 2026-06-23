package com.payu.android.front.sdk.payment_add_card_module.service;

import android.content.Context;

import com.payu.android.front.sdk.payment_add_card_module.presenter.NewCardPresenter;
import com.payu.android.front.sdk.payment_add_card_module.view.NewCardView;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.Card;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenType;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateRequest;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.TokenCreateResponse;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CardService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
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
        when(cardService.addCard(any(TokenCreateRequest.class))).thenReturn(call);
        objectUnderTest = new NewCardService(view, context, presenter, cardService, retrofitNewCardCallback);

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
    public void shouldNotAddCardWhenCardIsNotValid() {
        //given
        when(presenter.isCardValid()).thenReturn(false);

        //when
        objectUnderTest.addCard("testId", TokenType.SINGLE);

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(0)).getCardData();
        verify(cardService, times(0)).addCard(any(TokenCreateRequest.class));
    }

    @Test
    public void shouldCallAddCardWithProvidedType() {
        //given
        Card card = new Card("4111", "123", "10", "2018");
        when(presenter.isCardValid()).thenReturn(true);
        when(presenter.getCardData()).thenReturn(card);

        //when
        objectUnderTest.addCard("testId", TokenType.MULTI);

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(1)).getCardData();

        org.mockito.ArgumentCaptor<TokenCreateRequest> requestCaptor = forClass(TokenCreateRequest.class);
        verify(cardService, times(1)).addCard(requestCaptor.capture());
        assertThat(requestCaptor.getValue().getType()).isEqualTo(TokenType.MULTI);
    }


    @Test
    public void shouldNotAddCardWithoutAgreementWhenCardIsNotValid() {
        //given
        when(presenter.isCardValid()).thenReturn(false);

        //when
        objectUnderTest.addCard("testId", TokenType.SINGLE);

        //then
        verify(presenter, times(1)).isCardValid();
        verify(presenter, times(0)).getCardData();
        verify(cardService, times(0)).addCard(any(TokenCreateRequest.class));
    }
}
