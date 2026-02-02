package com.payu.android.front.sdk.payment_add_card_module.validation;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_add_card_module.validation.message.CvvInvalidMessageProvider;

import org.junit.Before;
import org.junit.Test;

public class CvvStringValidatorTest {

    CvvStringValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CvvStringValidator(new CvvInvalidMessageProvider());
    }

    @Test
    public void shouldAbsentValueForValidCvv() {
        // given
        String validCvv = "123";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(validCvv);

        // then
        assertFalse(errorString.isPresent());
    }

    @Test
    public void shouldReturnErrorForCvvContainingDigits() {
        // given
        String notValidCvv = "asd";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(notValidCvv);

        // then
        assertTrue(errorString.isPresent());
        assertEquals("Please enter a valid code", errorString.get());
    }

    @Test
    public void shouldReturnErrorForEmptyCvv() {
        // given
        String emptyCvv = "";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(emptyCvv);

        // then
        assertTrue(errorString.isPresent());
        assertEquals("Enter CVV code", errorString.get());
    }

    @Test
    public void shouldReturnErrorForTooLongCvv() {
        // given
        String tooLongCvv = "1231231";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(tooLongCvv);

        // then
        assertTrue(errorString.isPresent());
        assertEquals("Please enter a valid code", errorString.get());
    }

    @Test
    public void shouldReturnErrorForTooShortCvv() {
        // given
        String tooShortCvv = "1";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(tooShortCvv);

        // then
        assertTrue(errorString.isPresent());
        assertEquals("Please enter a valid code", errorString.get());
    }
}