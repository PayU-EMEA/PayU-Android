package com.payu.android.front.sdk.payment_add_card_module.formatter;

import static com.google.common.base.Strings.repeat;

public class CardMaskApplier {

    private static final int CARD_NUMBER_VISIBLE_PREFIX_LENGTH = 6;
    private static final int CARD_NUMBER_VISIBLE_SUFFIX_LENGTH = 4;
    private static final String STAR_CHARACTER = "*";
    private final String mNumber;

    public CardMaskApplier(String number) {
        mNumber = number;
    }

    public String maskCardNumber() {
        int numberLength = mNumber.length();
        String prefix = mNumber.substring(0, CARD_NUMBER_VISIBLE_PREFIX_LENGTH);
        String suffix = mNumber.substring(numberLength - CARD_NUMBER_VISIBLE_SUFFIX_LENGTH, numberLength);
        return prefix + repeat(STAR_CHARACTER, getRequiredStarsCount(numberLength)) + suffix;
    }

    private int getRequiredStarsCount(int numberLength) {
        return numberLength - CARD_NUMBER_VISIBLE_PREFIX_LENGTH - CARD_NUMBER_VISIBLE_SUFFIX_LENGTH;
    }
}
