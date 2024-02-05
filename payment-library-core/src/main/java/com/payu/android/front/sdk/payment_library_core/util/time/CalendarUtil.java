package com.payu.android.front.sdk.payment_library_core.util.time;

import java.util.Calendar;

public class CalendarUtil {

    public static final int CALENDAR_ZERO_BASED_MONTH_SHIFT = 1;

    public static boolean isSameDay(Calendar lhs, Calendar rhs) {
        return lhs.get(Calendar.YEAR) == rhs.get(Calendar.YEAR) && lhs.get(Calendar.DAY_OF_YEAR) == rhs.get(Calendar.DAY_OF_YEAR);
    }

    public Calendar getCalendarSetToLastDayOfMonth(int selectedMonth, int selectedYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, selectedMonth - CalendarUtil.CALENDAR_ZERO_BASED_MONTH_SHIFT);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    public int getCurrentMonth(Calendar currentCalendar) {
        return currentCalendar.get(Calendar.MONTH) + CALENDAR_ZERO_BASED_MONTH_SHIFT;
    }

    public int getMaximumMonth(Calendar currentCalendar) {
        return currentCalendar.getMaximum(Calendar.MONTH) + CALENDAR_ZERO_BASED_MONTH_SHIFT;
    }

    public int getMinimumMonth(Calendar currentCalendar) {
        return currentCalendar.getMinimum(Calendar.MONTH) + CALENDAR_ZERO_BASED_MONTH_SHIFT;
    }
}
