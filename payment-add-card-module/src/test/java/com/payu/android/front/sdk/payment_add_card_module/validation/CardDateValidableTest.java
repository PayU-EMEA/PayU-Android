package com.payu.android.front.sdk.payment_add_card_module.validation;

import static org.assertj.core.api.Assertions.assertThat;
import com.payu.android.front.sdk.payment_add_card_module.extraction.CardDateExtractor;
import com.payu.android.front.sdk.payment_library_core.util.time.ActualTimeProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class CardDateValidableTest {

    CardDateValidable objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CardDateValidable(new CardDateExtractor(), ActualTimeProvider.getInstance());
    }

    @Test
    public void shouldReturnFalseIfMonthIsEmpty() {
        // given
        objectUnderTest.setDate("", "1234");

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldReturnFalseIfProvidedMonthIsNotParsable() {
        // given
        objectUnderTest.setDate("124", "1234");

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldReturnFalseIfProvidedYearIsOutOfRange() {
        // given
        objectUnderTest.setDate("12", String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 21));

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldReturnFalseIfYearIsEmpty() {
        // given
        objectUnderTest.setDate("12", "");

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldReturnTrueIfProvidedDateIsFromFuture() {
        // given
        objectUnderTest.setDate("12", String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1));

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldReturnFalseIfProvidedDateIsFromPast() {
        // given
        objectUnderTest.setDate("12", String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1));

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void shouldReturnTrueIfProvidedYearIsInRange() {
        // given
        objectUnderTest.setDate("12", String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 20));

        // when
        boolean isValid = objectUnderTest.validate();

        // then
        assertThat(isValid).isTrue();
    }
}
