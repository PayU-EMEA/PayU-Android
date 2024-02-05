package com.payu.android.front.sdk.payment_add_card_module.formatter;

public class CardNumberFormattingStrategy implements FormattingStrategy {

    private static final int GROUP_4_CHARS = 4;
    private static final int CARD_MAX_LENGTH = 16;
    private static final char CARD_NUMBER_SEPARATOR = ' ';

    public String dropWhitespaces(String cardNumber) {
        return cardNumber.replaceAll("\\s", "");
    }

    private String dropWhitespacesRegex(String cardNumber) {
        return cardNumber.replaceAll("\\s", "").replaceAll(" ", "");
    }

    @Override
    public String format(String input) {
        if (dropWhitespaces(input).length() > CARD_MAX_LENGTH) {
            input = dropWhitespacesRegex(input).trim().substring(0, CARD_MAX_LENGTH);
        }
        return formatCardNumber4(input);
    }

    public String formatCardNumber4(String input) {
        return formatInGroups(dropWhitespaces(input), GROUP_4_CHARS);
    }

    private String formatInGroups(String input, int groupSize) {
        StringBuilder stringBuilder = new StringBuilder(dropWhitespaces(input));

        for (int i = groupSize; i < stringBuilder.length(); i += groupSize + 1) {
            stringBuilder.insert(i, CARD_NUMBER_SEPARATOR);
        }

        return stringBuilder.toString();
    }
}
