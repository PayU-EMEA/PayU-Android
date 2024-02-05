package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import androidx.annotation.NonNull;

import java.util.Locale;

public class PriceFormatter {
    private final int price;

    PriceFormatter(int price) {
        this.price = price;
    }

    static String formatAsString(int price) {
        return new PriceFormatter(price).asString();
    }

    /**
     * With order creation, price is sent as integer. For more details see
     * <a href=http://developers.payu.com/en/restapi.html#creating_new_order>documentation</a>
     *
     * @return Formatted price as String with two decimal places
     */
    String asString() {
        return String.format(Locale.ENGLISH, "%.2f", (float) price / 100);
    }
}
