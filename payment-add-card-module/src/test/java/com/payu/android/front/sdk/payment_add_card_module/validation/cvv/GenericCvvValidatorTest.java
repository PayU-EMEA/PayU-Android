package com.payu.android.front.sdk.payment_add_card_module.validation.cvv;


import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GenericCvvValidatorTest {

    GenericCvvValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new GenericCvvValidator();
    }

    @Test
    public void shouldAbsentValueForValidCvv() {
        // given
        String validCvv = "123";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(validCvv);

        // then
        assertTrue(!errorString.isPresent());
    }

    @Test
    public void shouldReturnErrorForCvvContainingDigits() {
        // given
        String notValidCvv = "asd";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(notValidCvv);

        // then
        assertTrue(errorString.isPresent());
        assertTrue(errorString.get().contains(GenericCvvValidator.INVALID_CVV_ERROR));
    }

    @Test
    public void shouldReturnErrorForEmptyCvv() {
        // given
        String emptyCvv = "";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(emptyCvv);

        // then
        assertTrue(errorString.isPresent());
        assertTrue(errorString.get().contains(GenericCvvValidator.INVALID_CVV_ERROR));
    }

    @Test
    public void shouldReturnErrorForTooLongCvv() {
        // given
        String tooLongCvv = "1231231";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(tooLongCvv);

        // then
        assertTrue(errorString.isPresent());
        assertTrue(errorString.get().contains(GenericCvvValidator.INVALID_CVV_ERROR));
    }

    @Test
    public void shouldReturnErrorForTooShortCvv() {
        // given
        String tooShortCvv = "1";

        // when
        Optional<String> errorString = objectUnderTest.getErrorString(tooShortCvv);

        // then
        assertTrue(errorString.isPresent());
        assertTrue(errorString.get().contains(GenericCvvValidator.INVALID_CVV_ERROR));
    }
}