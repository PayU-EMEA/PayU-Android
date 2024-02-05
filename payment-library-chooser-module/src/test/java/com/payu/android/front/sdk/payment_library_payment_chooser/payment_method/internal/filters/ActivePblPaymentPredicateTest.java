package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.filters;


import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Test;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPayment;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPayment;
import static org.assertj.core.api.Assertions.assertThat;

public class ActivePblPaymentPredicateTest {

    private ActivePblPaymentPredicate objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new ActivePblPaymentPredicate();
    }

    @Test
    public void shouldReturnTrueForEnabledPblMethod() {
        //given
        PaymentMethod paymentMethod = createPblPayment("ENABLED");

        //when
        boolean isMatched = objectUnderTest.apply(paymentMethod);

        //then
        assertThat(isMatched).isTrue();
    }

    @Test
    public void shouldReturnFalseForEnabledCardMethod() {
        //given
        PaymentMethod paymentMethod = createCardPayment("ACTIVE");

        //when
        boolean isMatched = objectUnderTest.apply(paymentMethod);

        //then
        assertThat(isMatched).isFalse();
    }

    @Test
    public void shouldReturnFalseForInactiveCardMethod() {
        //given
        PaymentMethod paymentMethod = createCardPayment("EXPIRED");

        //when
        boolean isMatched = objectUnderTest.apply(paymentMethod);

        //then
        assertThat(isMatched).isFalse();
    }

    @Test
    public void shouldReturnFalseForDisabledDisabledMethod() {
        //given
        PaymentMethod paymentMethod = createPblPayment("DISABLED");

        //when
        boolean isMatched = objectUnderTest.apply(paymentMethod);

        //then
        assertThat(isMatched).isFalse();
    }
}