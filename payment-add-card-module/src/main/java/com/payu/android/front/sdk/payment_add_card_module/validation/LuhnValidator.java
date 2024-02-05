package com.payu.android.front.sdk.payment_add_card_module.validation;

public class LuhnValidator {

    private static final int DECIMAL_BASE = 10;

    public boolean isValid(String cardNumber) {
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(cardNumber).reverse().toString();

        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), DECIMAL_BASE);

            if (i % 2 == 0) {// this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            } else {// add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;

                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }

        return (s1 + s2) % 10 == 0;
    }
}
