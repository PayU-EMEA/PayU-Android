package com.payu.android.front.sdk.payment_library_payment_methods.model;

import com.payu.android.front.sdk.payment_library_payment_methods.util.PaymentMethodsTestDataProvider;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus.DISABLED;
import static com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodStatus.ENABLED;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodCreatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldCreateEnabledCardPaymentMethodCorrectly() {
        //given
        String status = "ACTIVE";

        //when
        PaymentMethod cardPayment = PaymentMethodsTestDataProvider.createCardPayment(status);

        //then
        assertThat(cardPayment.getStatus()).isEqualTo(ENABLED);
    }

    @Test
    public void shouldCreateExpiredCardPaymentMethodCorrectly() {
        //given
        String status = "EXPIRED";

        //when
        PaymentMethod cardPayment = PaymentMethodsTestDataProvider.createCardPayment(status);

        //then
        assertThat(cardPayment.getStatus()).isEqualTo(DISABLED);
    }

    @Test
    public void shouldThrowWhenInvalidCardStatus() {
        //given
        String status = "INVALID";

        //when
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                String.format("Invalid status: %s. Only %s are allowed.", status, new PaymentMethodStatusValidator().getAllowedStatusList()));
        PaymentMethod ignored = PaymentMethodsTestDataProvider.createCardPayment(status);

        //then
        //should throw exception
    }

    @Test
    public void shouldCreateEnabledPblPaymentMethodCorrectly() {
        //given
        String status = "ENABLED";

        //when
        PaymentMethod cardPayment = PaymentMethodsTestDataProvider.createPblPayment(status);

        //then
        assertThat(cardPayment.getStatus()).isEqualTo(ENABLED);
    }

    @Test
    public void shouldCreateDisabledPblPaymentMethodCorrectly() {
        //given
        String status = "DISABLED";

        //when
        PaymentMethod cardPayment = PaymentMethodsTestDataProvider.createPblPayment(status);

        //then
        assertThat(cardPayment.getStatus()).isEqualTo(DISABLED);
    }

    @Test
    public void shouldCreateTemporaryDisabledPblPaymentMethodCorrectly() {
        //given
        String status = "TEMPORARY_DISABLED";

        //when
        PaymentMethod cardPayment = PaymentMethodsTestDataProvider.createPblPayment(status);

        //then
        assertThat(cardPayment.getStatus()).isEqualTo(DISABLED);
    }

    @Test
    public void shouldThrowWhenInvalidPblStatus() {
        //given
        String status = "INVALID";

        //when
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                String.format("Invalid status: %s. Only %s are allowed.", status, new PblStatusValidator().getAllowedStatusList()));
        PaymentMethod ignored = PaymentMethodsTestDataProvider.createPblPayment(status);

        //then
        //should throw exception
    }


    @Test
    public void shouldCreateBlikPaymentMethodCorrectly(){
        //given

        //when
        PaymentMethod blikPayment = PaymentMethodsTestDataProvider.createBlikPayment();

        //then
        assertThat(blikPayment.getStatus()).isEqualTo(ENABLED);
        assertThat(blikPayment instanceof  BlikPaymentMethod);

    }
}