package com.payu.android.front.sdk.payment_add_card_module.textwatcher;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.validation.LuhnValidator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardIssuerLogoTextWatcherTest {

    CardIssuerLogoTextWatcher objectUnderTest;
    @Mock
    LuhnValidator luhnValidator;
    @Mock
    CardIssuerProvider cardIssuerProvider;
    @Mock
    OnCardIssuerChangedListener listener;
    String validNumber = "valid";
    String invalidNumber = "invalid";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new CardIssuerLogoTextWatcher(cardIssuerProvider, luhnValidator, listener);
        when(luhnValidator.isValid(eq(validNumber))).thenReturn(true);
        when(luhnValidator.isValid(eq(invalidNumber))).thenReturn(false);
        when(cardIssuerProvider.dropDashAndWhitespaces(eq(validNumber))).thenReturn(validNumber);
        when(cardIssuerProvider.dropDashAndWhitespaces(eq(invalidNumber))).thenReturn(invalidNumber);
    }

    @Test
    public void shouldNotNotifyListenerTwiceWhenCardNumberIsValid() {
        // given
        CardIssuer cardIssuer = CardIssuer.MAESTRO;
        when(cardIssuerProvider.getCardProvider(validNumber)).thenReturn(cardIssuer);

        // when
        objectUnderTest.onTextChanged(validNumber, 0, 10, 10);
        objectUnderTest.onTextChanged(validNumber, 0, 10, 10);

        // then
        verify(listener, times(1)).onCardIssuerChanged(cardIssuer);
    }

    @Test
    public void shouldNotifyListenerWhenCardNumberIsValid() {
        // given
        CardIssuer cardIssuer = CardIssuer.MAESTRO;
        when(cardIssuerProvider.getCardProvider(validNumber)).thenReturn(cardIssuer);

        // when
        objectUnderTest.onTextChanged(validNumber, 0, 10, 10);

        // then
        verify(listener).onCardIssuerChanged(cardIssuer);
    }

    @Test
    public void shouldNotifyListenerWithUnknownProviderWhenCardNumberIsInvalid() {
        // when
        objectUnderTest.onTextChanged(invalidNumber, 0, 10, 10);

        // then
        verify(listener).onCardIssuerChanged(CardIssuer.UNKNOWN);
    }
}