package com.payu.android.front.sdk.payment_add_card_module.formatter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateFormattingStrategyTest {
    private DateFormattingStrategy objectUnderTest;

    @Test
    public void shouldReturnTheSameStringWhenSingleCharacterInputted() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("2");

        //then
        assertThat(formatted).isEqualTo("2");
    }

    @Test
    public void shouldReturnTheSameStringWhenDoubleCharacterInputted() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("22");

        //then
        assertThat(formatted).isEqualTo("22/");
    }

    @Test
    public void shouldReturnSlashedStringWhenThirdCharacterInputted() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("222");

        //then
        assertThat(formatted).isEqualTo("22/2");
    }

    @Test
    public void shouldReturnSlashedStringWhenFourthCharacterInputtedWithSlashIncluded() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("22/22");

        //then
        assertThat(formatted).isEqualTo("22/22");
    }

    @Test
    public void shouldReturnSlashedStringWhenFourthCharacterDeleted() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("22/22");
        formatted = objectUnderTest.format("22/2");

        //then
        assertThat(formatted).isEqualTo("22/2");
    }

    @Test
    public void shouldDeleteSlashWhenThirdCharacterDeleted() {
        //given
        objectUnderTest = new DateFormattingStrategy();

        //when
        String formatted = objectUnderTest.format("22/2");
        formatted = objectUnderTest.format("22/");

        //then
        assertThat(formatted).isEqualTo("22/");
    }
}