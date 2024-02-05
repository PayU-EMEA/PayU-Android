package com.payu.android.front.sdk.payment_add_card_module.extraction;


import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDateExtractorTest {

    CardDateExtractor objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CardDateExtractor();
    }

    @Test
    public void shouldReturnAbsentIfMonthIsOutOfRange() {
        // given
        String month = "13";
        String year = "2014";

        // when
        Optional<Calendar> calendarOptional = objectUnderTest.getDate(month, year);

        // then
        assertThat(calendarOptional).isEqualTo(Optional.absent());
    }

    @Test
    public void shouldReturnCalendarForSelectedDate() {
        // given
        String month = "02";
        String year = "2014";

        // when
        Calendar calendar = objectUnderTest.getDate(month, year).get();

        // then
        assertThat(calendar.get(Calendar.MILLISECOND)).isEqualTo(999);
        assertThat(calendar.get(Calendar.SECOND)).isEqualTo(59);
        assertThat(calendar.get(Calendar.MINUTE)).isEqualTo(59);
        assertThat(calendar.get(Calendar.HOUR)).isEqualTo(11);
        assertThat(calendar.get(Calendar.AM_PM)).isEqualTo(1);
        assertThat(calendar.get(Calendar.DAY_OF_MONTH)).isEqualTo(28);
        assertThat(calendar.get(Calendar.MONTH)).isEqualTo(Integer.parseInt(month) - 1);
        assertThat(calendar.get(Calendar.YEAR)).isEqualTo(Integer.parseInt(year));
    }
}
