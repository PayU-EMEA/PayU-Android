package com.payu.android.front.sdk.payment_library_payment_methods.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PblStatusValidatorTest {
    private PblStatusValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new PblStatusValidator();
    }

    @Test
    public void shouldReturnTrueForCorrectPblStatuses() {
        //given

        //when
        boolean temporaryDisabled = objectUnderTest.validate("TEMPORARY_DISABLED");
        boolean enabled = objectUnderTest.validate("ENABLED");
        boolean disabled = objectUnderTest.validate("DISABLED");

        //then
        assertThat(temporaryDisabled).isTrue();
        assertThat(enabled).isTrue();
        assertThat(disabled).isTrue();
    }

    @Test
    public void shouldReturnFalseForCardStatuses() {
        //given

        //when
        boolean active = objectUnderTest.validate("ACTIVE");
        boolean expired = objectUnderTest.validate("EXPIRED");

        //then
        assertThat(active).isFalse();
        assertThat(expired).isFalse();
    }

    @Test
    public void shouldReturnFalseForInvalidStatus() {
        //given

        //when
        boolean invalid = objectUnderTest.validate("invalid");

        //then
        assertThat(invalid).isFalse();
    }
}

