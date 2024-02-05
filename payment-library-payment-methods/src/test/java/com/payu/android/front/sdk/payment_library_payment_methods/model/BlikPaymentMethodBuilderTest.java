package com.payu.android.front.sdk.payment_library_payment_methods.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BlikPaymentMethodBuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BlikPaymentMethodBuilder objectUnderTest;


    @Before
    public void setUp() {
        objectUnderTest = new BlikPaymentMethodBuilder();
    }

    @Test
    public void shouldRequireValue() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("value cannot be empty");
        //when
        objectUnderTest.withBrandImageUrl("Url")
                .withType("Type")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireImageUrl() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("brandImageUrl cannot be empty");
        //when
        objectUnderTest.withValue("Value")
                .withType("Type")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireType() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("type cannot be empty");
        //when
        objectUnderTest.withValue("Value")
                .withBrandImageUrl("Url")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldCreateObject() {
        //given

        //when
        BlikPaymentMethod blikPaymentMethod = objectUnderTest
                .withBrandImageUrl("Url")
                .withType("Type")
                .withValue("Value")
                .build();
        //then
        assertTrue("Url should be the same", blikPaymentMethod.getBrandImageUrl().equals("Url"));
        assertTrue("Type should be the same", blikPaymentMethod.getType().equals("Type"));
        assertTrue("Value should be the same", blikPaymentMethod.getValue().equals("Value"));

    }
}

