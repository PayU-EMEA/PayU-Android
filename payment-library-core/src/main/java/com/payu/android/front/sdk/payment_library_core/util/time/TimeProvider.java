package com.payu.android.front.sdk.payment_library_core.util.time;

import java.util.Calendar;

public interface TimeProvider {

    Calendar getCurrentCalendar();

    long getCurrentTimeInMillis();
}
