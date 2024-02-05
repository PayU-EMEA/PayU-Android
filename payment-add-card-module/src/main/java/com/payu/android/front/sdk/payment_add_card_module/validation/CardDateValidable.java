package com.payu.android.front.sdk.payment_add_card_module.validation;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.payu.android.front.sdk.payment_add_card_module.extraction.CardDateExtractor;
import com.payu.android.front.sdk.payment_library_core.util.time.ActualTimeProvider;
import com.payu.android.front.sdk.payment_library_core.validable.Validable;

import java.util.Calendar;

public class CardDateValidable implements Validable {
    private static final int MAX_YEAR_RANGE = 20;
    private CardDateExtractor mCardDateExtractor;
    private ActualTimeProvider mActualTimeProvider;
    private String mMonth;
    private String mYear;

    public CardDateValidable(CardDateExtractor cardDateExtractor, ActualTimeProvider actualTimeProvider) {
        mCardDateExtractor = cardDateExtractor;
        mActualTimeProvider = actualTimeProvider;
    }

    public void setDate(String month, String year) {
        mYear = year;
        mMonth = month;
    }

    @Override
    public boolean validate() {
        Preconditions.checkNotNull(mMonth, "Month is not set");
        Preconditions.checkNotNull(mYear, "Year is not set");

        if (Strings.isNullOrEmpty(mMonth) || Strings.isNullOrEmpty(mYear)) {
            return false;
        }

        Optional<Calendar> cardDateOptional = mCardDateExtractor.getDate(mMonth, mYear);
        return cardDateOptional.isPresent() && isDateValid(cardDateOptional.get());
    }

    private boolean isDateValid(Calendar cardDate) {
        Calendar actualDate = mActualTimeProvider.getCurrentCalendar();
        return actualDate.before(cardDate) && isYearInRange(actualDate, cardDate);
    }

    private boolean isYearInRange(Calendar currentDate, Calendar cardDate) {
        return currentDate.get(Calendar.YEAR) + MAX_YEAR_RANGE >= cardDate.get(Calendar.YEAR);
    }
}

