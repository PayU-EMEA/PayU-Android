package com.payu.android.front.sdk.payment_add_card_module.issuer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardIssuerProviderIntegrationTest {

    CardIssuerProvider objectUnderTest = CardIssuerProvider.getInstance();

    @Test
    public void shouldHandleCardsWithDashes() {
        // given
        String shortCardNumber = "411-11-11-1--11111111 ";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.VISA);
    }

    @Test
    public void shouldHandleCardsWithWhitespaces() {
        // given
        String shortCardNumber = " 4111  11111    1111111 ";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.VISA);
    }

    @Test
    public void shouldReturnCardUnsupportedForNullCardNumber() {
        // given
        String longCardNumber = null;

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(longCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForNumbersLongerThan16Characters_17Characters() {
        // given
        String longCardNumber = "45397688491934400";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(longCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForNumbersLongerThan16Characters_TwentyCharacters() {
        // given
        String longCardNumber = "1234566789012345667890";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(longCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForNumbersShorterThan13Characters_10Characters() {
        // given
        String shortCardNumber = "1234566789";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForNumbersShorterThan13Characters_TwelveCharacters() {
        // given
        String shortCardNumber = "123456678900";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForSixteenNumbersButUnsupported() {
        // given
        String shortCardNumber = "1111111111111111";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }

    @Test
    public void shouldReturnCardUnsupportedForThirteenNumbersButUnsupported() {
        // given
        String shortCardNumber = "1111111111111";

        // when
        CardIssuer cardProvider = objectUnderTest.getCardProvider(shortCardNumber);

        // then
        assertThat(cardProvider).isEqualTo(CardIssuer.UNKNOWN);
    }
}