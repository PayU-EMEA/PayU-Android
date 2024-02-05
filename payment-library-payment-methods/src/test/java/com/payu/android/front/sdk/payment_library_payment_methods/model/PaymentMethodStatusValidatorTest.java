package com.payu.android.front.sdk.payment_library_payment_methods.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodStatusValidatorTest {
    private PaymentMethodStatusValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new PaymentMethodStatusValidator();
    }

    @Test
    public void shouldReturnTrueForCardStatuses() {
        //given

        //when
        boolean active = objectUnderTest.validate("ACTIVE");
        boolean expired = objectUnderTest.validate("EXPIRED");

        //then
        assertThat(active).isTrue();
        assertThat(expired).isTrue();
    }

    @Test
    public void shouldReturnFalseForPblStatuses() {
        //given

        //when
        boolean temporaryDisabled = objectUnderTest.validate("TEMPORARY_DISABLED");
        boolean enabled = objectUnderTest.validate("ENABLED");
        boolean disabled = objectUnderTest.validate("DISABLED");

        //then
        assertThat(temporaryDisabled).isFalse();
        assertThat(enabled).isFalse();
        assertThat(disabled).isFalse();
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

