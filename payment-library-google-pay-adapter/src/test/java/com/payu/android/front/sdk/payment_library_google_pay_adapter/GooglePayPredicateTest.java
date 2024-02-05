package com.payu.android.front.sdk.payment_library_google_pay_adapter;

import org.junit.Test;

import static com.payu.android.front.sdk.payment_library_google_pay_adapter.utils.GooglePayPaymentMethodFactory.createPblPayment;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GooglePayPredicateTest {
    private GooglePayPredicate objectUnderTest;

    @Test
    public void shouldMatchGooglePayValue() {
        objectUnderTest = new GooglePayPredicate(true);

        boolean isGood = objectUnderTest.apply(createPblPayment("ap"));

        assertTrue(isGood);
    }

    @Test
    public void shouldNotMatchOtherValue() {
        objectUnderTest = new GooglePayPredicate(true);

        boolean isGood = objectUnderTest.apply(createPblPayment("other"));

        assertFalse(isGood);
    }

    @Test
    public void shouldNotMatchGooglePayIfGooglePayDisabled() {
        objectUnderTest = new GooglePayPredicate(false);

        boolean isGood = objectUnderTest.apply(createPblPayment("ap"));

        assertFalse(isGood);
    }
}