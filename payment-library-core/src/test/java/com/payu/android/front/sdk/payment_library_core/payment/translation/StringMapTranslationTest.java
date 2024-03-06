package com.payu.android.front.sdk.payment_library_core.payment.translation;

import static org.assertj.core.api.Assertions.assertThat;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringMapTranslationTest {

    @Test
    public void shouldHaveAllKeysTranslated() {
        // given
        TranslationKey[] translationKeys = TranslationKey.values();
        List<Translation> availableTranslations = new ArrayList<>();
        availableTranslations.add(new Polish());
        availableTranslations.add(new English());
        availableTranslations.add(new Czech());
        availableTranslations.add(new German());
        availableTranslations.add(new Hungarian());
        availableTranslations.add(new Ukrainian());
        availableTranslations.add(new Slovak());
        availableTranslations.add(new Spanish());
        availableTranslations.add(new French());
        availableTranslations.add(new Lithuanian());
        availableTranslations.add(new Romanian());
        availableTranslations.add(new Slovenian());
        availableTranslations.add(new Bulgarian());
        availableTranslations.add(new Croatian());
        availableTranslations.add(new Greek());
        availableTranslations.add(new Italian());
        availableTranslations.add(new Russian());

        // expect
        for (Translation translation : availableTranslations) {

            for (TranslationKey translationKey : translationKeys) {
                assertThat(translation.translate(translationKey)).overridingErrorMessage(String.format(
                        "Expected translation in" + " language %s for key %s", translation.getLanguage(), translationKey))
                        .isNotEmpty();
            }
        }
    }
}