package com.payu.android.front.sdk.payment_add_card_module.formatter;

import androidx.annotation.NonNull;

public class DateFormattingStrategy implements FormattingStrategy {

    private String lastDate;

    public DateFormattingStrategy() {
        lastDate = "";
    }

    @NonNull
    private String dropMaskedCharacters(@NonNull String dateString) {
        return dateString.replaceAll("/", "");
    }

    @Override
    public String format(String input) {
        StringBuilder stringBuilder = new StringBuilder(dropMaskedCharacters(input));
        boolean deleted = input.length() < lastDate.length();
        if ((!deleted && input.length() >= 2) || (deleted && input.length() >= 3)) {
            stringBuilder.insert(2, "/");
        }
        lastDate = stringBuilder.toString();
        return lastDate;
    }
}
