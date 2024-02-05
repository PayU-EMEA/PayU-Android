package com.payu.android.front.sdk.payment_add_card_module.validation.cvv;


import com.google.common.base.Optional;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MaestroCvvValidatorTest {

    MaestroCvvValidator objectUnderTest = new MaestroCvvValidator();

    @Test
    public void shouldReturnEmptyErrorIfCvvIsEmpty() {
        // given
        String emptyCvv = "";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(emptyCvv);

        // then
        assertFalse(errorString.isPresent());
    }

    @Test
    public void shouldReturnEmptyErrorIfCvvIsThreeDigits() {
        // given
        String correctCvv = "333";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(correctCvv);

        // then
        assertFalse(errorString.isPresent());
    }

    @Test
    public void shouldReturnErrorStringIfCvvContainsNonDigitsCharacters() {
        // given
        String incorrectCvv = "asd";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(incorrectCvv);

        // then
        assertTrue(errorString.isPresent());
    }

    @Test
    public void shouldReturnErrorStringIfCvvIsTooShort() {
        // given
        String incorrectCvv = "33";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(incorrectCvv);

        // then
        assertTrue(errorString.isPresent());
    }
}