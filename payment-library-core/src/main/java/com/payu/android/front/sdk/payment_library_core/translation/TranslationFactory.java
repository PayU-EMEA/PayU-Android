package com.payu.android.front.sdk.payment_library_core.translation;


import static com.google.common.base.Optional.fromNullable;

import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Bulgarian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Croatian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Czech;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.English;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.French;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.German;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Greek;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Hungarian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Italian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Lithuanian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Polish;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Romanian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Russian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Slovak;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Slovenian;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Spanish;
import com.payu.android.front.sdk.payment_library_core.translation.dictionary.Ukrainian;

import java.util.HashMap;
import java.util.Map;

public final class TranslationFactory {

    private static Translation sTranslation;
    private static final Object sMonitor = new Object();

    private TranslationFactory() {

    }

    public static void forceTranslation(Locale locale) {

        synchronized (sMonitor) {
            sTranslation = findOutTranslation(locale);
        }
    }

    public static Translation getInstance() {

        synchronized (sMonitor) {

            if (sTranslation == null) {
                sTranslation = findOutTranslation(Locale.AUTO);
            }

            return sTranslation;
        }
    }

    private static Translation findOutTranslation(Locale locale) {
        return getTranslationOrEnglish(getDeterminedAutoLocaleOrForced(locale));
    }

    private static Locale getDeterminedAutoLocaleOrForced(Locale locale) {
        return locale == Locale.AUTO ? Locale.withLanguageCode(java.util.Locale.getDefault().getLanguage()) : locale;
    }

    private static Translation getTranslationOrEnglish(Locale locale) {
        Translation polishTranslation = new Polish();
        Translation englishTranslation = new English();
        Translation czechTranslation = new Czech();
        Translation germanTranslation = new German();
        Translation hungarianTranslation = new Hungarian();
        Translation ukrainianTranslation = new Ukrainian();
        Translation slovakianTranslation = new Slovak();
        Translation spanishTranslation = new Spanish();
        Translation frenchTranslation = new French();
        Translation lithuanianTranslation = new Lithuanian();
        Translation romanianTranslation = new Romanian();
        Translation slovenianTranslation = new Slovenian();
        Translation bulgarianTranslation = new Bulgarian();
        Translation greekTranslation = new Greek();
        Translation croatianTranslation = new Croatian();
        Translation italianTranslation = new Italian();
        Translation russianTranslation = new Russian();

        Map<Locale, Translation> translations = new HashMap<>();
        translations.put(polishTranslation.getLanguage(), polishTranslation);
        translations.put(englishTranslation.getLanguage(), englishTranslation);
        translations.put(czechTranslation.getLanguage(), czechTranslation);
        translations.put(germanTranslation.getLanguage(), germanTranslation);
        translations.put(hungarianTranslation.getLanguage(), hungarianTranslation);
        translations.put(ukrainianTranslation.getLanguage(), ukrainianTranslation);
        translations.put(slovakianTranslation.getLanguage(), slovakianTranslation);
        translations.put(spanishTranslation.getLanguage(), spanishTranslation);
        translations.put(frenchTranslation.getLanguage(), frenchTranslation);
        translations.put(lithuanianTranslation.getLanguage(), lithuanianTranslation);
        translations.put(romanianTranslation.getLanguage(), romanianTranslation);
        translations.put(slovenianTranslation.getLanguage(), slovenianTranslation);
        translations.put(bulgarianTranslation.getLanguage(), bulgarianTranslation);
        translations.put(greekTranslation.getLanguage(), greekTranslation);
        translations.put(croatianTranslation.getLanguage(), croatianTranslation);
        translations.put(italianTranslation.getLanguage(), italianTranslation);
        translations.put(russianTranslation.getLanguage(), russianTranslation);

        return fromNullable(translations.get(locale)).or(englishTranslation);
    }
}
