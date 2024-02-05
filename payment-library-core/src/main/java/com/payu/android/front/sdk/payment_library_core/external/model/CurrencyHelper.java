package com.payu.android.front.sdk.payment_library_core.external.model;

import java.util.HashMap;
import java.util.Map;

/***
 * This class is a mapper for ISO_4217
 * Right now Android only support retrieving currency symbol from current locale
 * In case when you have US locale and would like to pay in PLN you will see only PLN as a currencySymbol (not zł)
 * so this is a workaround
 */
public class CurrencyHelper {
    static Map<String, String> currencyCodeToSymbol = new HashMap<>();

    static {
        currencyCodeToSymbol.put("PLN", "zł");
        currencyCodeToSymbol.put("EUR", "€");
        currencyCodeToSymbol.put("USD", "$");
        currencyCodeToSymbol.put("GBP", "£");
        currencyCodeToSymbol.put("CZK", "Kč");
        currencyCodeToSymbol.put("RON", "L");
        currencyCodeToSymbol.put("HUF", "Ft");
        currencyCodeToSymbol.put("HRK", "Kn");
        currencyCodeToSymbol.put("SEK", "Kr");
        currencyCodeToSymbol.put("DKK", "Kr");

    }

    public static String getCurrencySymbol(String currencyCode) {
        return currencyCodeToSymbol.get(currencyCode);
    }

}
