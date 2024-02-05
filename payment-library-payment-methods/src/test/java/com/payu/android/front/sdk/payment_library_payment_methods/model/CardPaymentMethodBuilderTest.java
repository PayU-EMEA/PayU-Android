package com.payu.android.front.sdk.payment_library_payment_methods.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus.DISABLED;
import static com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus.ENABLED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class CardPaymentMethodBuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private CardPaymentMethodBuilder objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CardPaymentMethodBuilder();
    }

    @Test
    public void shouldRequireCardExpirationYear() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cardExpirationYear cannot be empty");

        //when
        objectUnderTest.withCardExpirationMonth("12")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.ACTIVE)
                .withValue("VALUE")
                .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireCardExpirationMonth() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cardExpirationMonth cannot be empty");

        //when
        objectUnderTest.withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.ACTIVE)
                .withValue("VALUE")
                .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireCardNumberMasked() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("cardNumberMasked cannot be empty");

        //when
        objectUnderTest.withCardExpirationYear("2012")
                .withCardExpirationMonth("12")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.ACTIVE)
                .withValue("VALUE")
                .build();

        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireBrandImageUrl() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("brandImageUrl cannot be empty");

        //when
        objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withStatus(StatusConstants.ACTIVE)
                .withValue("VALUE")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldRequireStatus() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid status: null. Only [ACTIVE, EXPIRED] are allowed.");

        //when
        objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withValue("VALUE")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldValidateStatus() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid status: ENABLED. Only [ACTIVE, EXPIRED] are allowed.");

        //when
        objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus("ENABLED")
                .withValue("VALUE")
                .build();
        //then
        fail("Should throw exception");
    }

    @Test
    public void shouldCreateModelWithActiveStatus() {
        //given

        //when
        CardPaymentMethod paymentMethod = objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.ACTIVE)
                .withValue("VALUE")
                .build();
        //then
        assertThat(paymentMethod.getStatus()).isEqualTo(ENABLED);
    }

    @Test
    public void shouldCreateModelWithExpiredStatus() {
        //given

        //when
        CardPaymentMethod paymentMethod = objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.EXPIRED)
                .withValue("VALUE")
                .build();
        //then
        assertThat(paymentMethod.getStatus()).isEqualTo(DISABLED);
    }

    @Test
    public void shouldRequireValue() {
        //given
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("value cannot be empty");

        //when
        objectUnderTest.withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1111****1111")
                .withCardScheme("VS")
                .withBrandImageUrl("URL")
                .withStatus(StatusConstants.ACTIVE)
                .build();
        //then
        fail("Should throw exception");
    }
}
