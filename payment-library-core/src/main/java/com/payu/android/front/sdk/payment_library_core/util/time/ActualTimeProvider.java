package com.payu.android.front.sdk.payment_library_core.util.time;

import java.util.Calendar;

public class ActualTimeProvider implements TimeProvider {

    private static final ActualTimeProvider INSTANCE = new ActualTimeProvider();

    private ActualTimeProvider() {
        // Singleton
    }

    public static final ActualTimeProvider getInstance() {
        return INSTANCE;
    }

    public Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    public long getCurrentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
