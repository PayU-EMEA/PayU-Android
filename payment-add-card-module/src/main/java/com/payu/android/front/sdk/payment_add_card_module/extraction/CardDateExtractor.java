package com.payu.android.front.sdk.payment_add_card_module.extraction;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class CardDateExtractor {

    public Optional<Calendar> getDate(@NonNull String monthString, @NonNull String yearString) {
        Calendar calendar = new GregorianCalendar();
        int yearInt = safeParse(yearString);
        int monthInt = safeParse(monthString) - 1;

        if (!areValuesInRange(calendar, monthInt, yearInt)) {
            return Optional.absent();
        } else {
            setValidToDate(calendar, monthInt, yearInt);
            return Optional.of(calendar);
        }
    }

    private boolean areValuesInRange(Calendar calendar, int month, int year) {
        return isFieldInRange(calendar, month, Calendar.MONTH) && isFieldInRange(calendar, year, Calendar.YEAR);
    }

    private boolean isFieldInRange(Calendar calendar, int value, int fieldType) {
        return value <= calendar.getMaximum(fieldType) && value >= calendar.getMinimum(fieldType);
    }

    private int safeParse(String valueString) {

        try {
            return Integer.parseInt(valueString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void setActualMaximum(Calendar calendar, int field) {
        calendar.set(field, calendar.getActualMaximum(field));
    }

    private void setValidToDate(Calendar calendar, int month, int year) {
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.AM_PM, 1);
        setActualMaximum(calendar, Calendar.MILLISECOND);
        setActualMaximum(calendar, Calendar.SECOND);
        setActualMaximum(calendar, Calendar.MINUTE);
        setActualMaximum(calendar, Calendar.HOUR);
        setActualMaximum(calendar, Calendar.DAY_OF_MONTH);
    }
}
