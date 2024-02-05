package com.payu.android.front.sdk.payment_library_payment_chooser.utils;

import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodCreator;

import java.util.List;

public class PaymentMethodsModelTestDataProvider {
    public static PaymentMethodModel createCardPaymentMethodModel() {
        return new PaymentMethodModel.Builder().withTitleLabel("CARD")
                .withTitleContent("1111*****1111")
                .withDescriptionLabel("Data waznosci")
                .withImageUrl("www.image.png")
                .withDescriptionContent("12 / 2102")
                .withId("id")
                .withPaymentMethodType(PaymentMethodType.CARD)
                .build();
    }

    public static PaymentMethodModel createPblPaymentMethodModel() {
        return new PaymentMethodModel.Builder().withTitleLabel("Pbl")
                .withTitleContent("Mbank")
                .withImageUrl("www.image.png")
                .withId("id")
                .withPaymentMethodType(PaymentMethodType.PBL)
                .build();
    }

    @NonNull
    public static CardPaymentMethod createCardPaymentMethod() {
        return (CardPaymentMethod) createCardPayment("ACTIVE");
    }

    @NonNull
    public static PayByLinkPaymentMethod createPblPaymentMethod() {
        return (PayByLinkPaymentMethod) createPblPayment("ENABLED");
    }

    @NonNull
    public static PaymentMethod createCardPayment(@NonNull String status) {
        return createCardPayment(status, "VALUE");
    }

    @NonNull
    public static PaymentMethod createCardPayment(@NonNull String status, @NonNull String value) {
        return PaymentMethodCreator.cardBuilder()
                .withBrandImageUrl("URL")
                .withCardExpirationMonth("12")
                .withCardExpirationYear("2012")
                .withCardNumberMasked("1234******1231")
                .withCardScheme("VS")
                .withValue(value)
                .withPreferred(false)
                .withStatus(status)
                .build();
    }


    @NonNull
    public static PaymentMethod createBlikTokenPaymentMethod() {
        return PaymentMethodCreator
                .blikPaymentBuilder()
                .withValue("BLIK")
                .withBrandImageUrl("www.image.png")
                .withType("Type")
                .build();
    }

    @NonNull
    public static PaymentMethod createPblPayment(@NonNull String status) {
        return createPblPayment(status, "wm");
    }

    @NonNull
    public static PaymentMethod createPblPayment(@NonNull String status, @NonNull String value) {
        return PaymentMethodCreator.pblBuilder().withName("Name").withValue(value).withBrandImageUrl("URL").withStatus(status).build();
    }

    @NonNull
    public static Observer<List<PaymentMethod>> emptyObserver() {
        return new Observer<List<PaymentMethod>>() {
            @Override
            public void onChanged(@Nullable List<PaymentMethod> paymentMethods) {
                //ignored
            }
        };
    }
}
