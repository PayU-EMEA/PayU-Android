package com.payu.android.front.sdk.payment_add_card_module.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CardNumberFormattingStrategyTest {

    private static final String CARD_NUMBER_WITH_SPACES = "    41111   11111111111  ";
    private static final String FORMATTED_CARD_NUMBER = "4111 1111 1111 1111";
    private static final String SHORT_CARD_NUMBER = "411122";
    private static final String FORMATTED_SHORT_CARD_NUMBER = "4111 22";

    CardNumberFormattingStrategy objectUnderTest = new CardNumberFormattingStrategy();

    @Test
    public void shouldFormatTooShortCardNumber() {
        // expect
        assertThat(objectUnderTest.format(SHORT_CARD_NUMBER)).isEqualTo(FORMATTED_SHORT_CARD_NUMBER);
    }

    @Test
    public void shouldTrimCardNumberAndFormatIt() {
        // expect
        assertThat(objectUnderTest.format(CARD_NUMBER_WITH_SPACES)).isEqualTo(FORMATTED_CARD_NUMBER);
    }
}