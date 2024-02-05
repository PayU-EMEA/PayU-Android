package com.payu.android.front.sdk.payment_library_payment_methods.util;

import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodCreator;

public class PaymentMethodsTestDataProvider {

    public static PaymentMethod createCardPayment(String status) {
        return PaymentMethodCreator.cardBuilder()
                .withBrandImageUrl("URL")
                .withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1234******1231")
                .withCardScheme("VS")
                .withValue("VALUE")
                .withPreferred(false)
                .withStatus(status)
                .build();
    }

    public static PaymentMethod createPblPayment(String status) {
        return PaymentMethodCreator.pblBuilder().withName("Name").withValue("wm").withBrandImageUrl("URL").withStatus(status).build();
    }

    public static PaymentMethod createBlikPayment() {
        return PaymentMethodCreator.blikPaymentBuilder().withType("Type").withBrandImageUrl("Url").withValue("Value").build();
    }
}
