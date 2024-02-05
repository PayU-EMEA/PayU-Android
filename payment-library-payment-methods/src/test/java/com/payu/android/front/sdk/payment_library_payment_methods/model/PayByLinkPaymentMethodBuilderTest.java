package com.payu.android.front.sdk.payment_library_payment_methods.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.fail;

public class PayByLinkPaymentMethodBuilderTest {
    private PayByLinkPaymentMethodBuilder objectUnderTest;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        objectUnderTest = new PayByLinkPaymentMethodBuilder();
    }

    @Test
    public void shouldRequireName(){
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name cannot be empty");
        //when
        objectUnderTest.withBrandImageUrl("URL")
                       .withStatus(StatusConstants.ENABLED)
                       .withValue("VALUE")
                       .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireBrandImageUrl(){
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("brandImageUrl cannot be empty");
        //when
        objectUnderTest.withName("name")
                       .withStatus(StatusConstants.ENABLED)
                       .withValue("VALUE")
                       .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireStatus(){
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid status: null. Only [ENABLED, DISABLED, TEMPORARY_DISABLED] are allowed.");
        //when
        objectUnderTest.withName("name")
                       .withBrandImageUrl("URL")
                       .withValue("VALUE")
                       .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireValue(){
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("value cannot be empty");
        //when
        objectUnderTest.withName("name")
                       .withStatus(StatusConstants.ENABLED)
                       .withBrandImageUrl("URL")
                       .build();

        //then
        fail("Should throw exception");
    }

}