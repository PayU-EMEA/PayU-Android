package com.payu.android.front.sdk.payment_library_core.payment.configuration;

/**
 * An interface language setting that allows to enforce language setting. Default value is auto and is correlated with system
 * setting.
 *
 * @author PayU S.A.
 */
public enum Locale {
    AUTO("auto"), POLISH("pl"), ENGLISH("en"),
    CZECH("cs"), GERMAN("de"), HUNGARIAN("hu"),
    UKRAINIAN("ua") , SLOVAK("sk"), SPANISH ("es"),
    FRENCH("fr"), LITHUANIAN("lt"), LATVIAN("lv"),
    ROMANIAN("ro"), SLOVENIAN("sl"), BULGARIAN("bg"),
    GREEK("el"), CROATIAN("hr"), ITALIAN("it"),
    RUSSIAN("ru");
    private final String mLanguageCode;

    Locale(String languageCode) {
        mLanguageCode = languageCode;
    }

    public static Locale withLanguageCode(String languageCode) {

        for (Locale locale : values()) {

            if (locale.getLanguageCode().equalsIgnoreCase(languageCode)) {
                return locale;
            }
        }

        return AUTO;
    }

    public String getLanguageCode() {
        return mLanguageCode;
    }
}
