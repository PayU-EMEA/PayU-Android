package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers;

import android.app.Application;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;

public class PaymentMethodClassConfigurationProvider extends ClassConfigurationProvider {

    private final Application application;
    private PaymentMethodActions paymentMethodActions;

    PaymentMethodClassConfigurationProvider(@NonNull ConfigurationDataProvider configurationDataProvider, @NonNull Application application) {
        super(configurationDataProvider);
        this.application = application;
    }

    public PaymentMethodActions getPaymentMethodsActions() {
        if (paymentMethodActions == null) {
            String paymentMethodClassName = configurationDataProvider.getPaymentMethodsClassName();
            paymentMethodActions = (PaymentMethodActions) createObjectFromClass(paymentMethodClassName, application);
        }
        return paymentMethodActions;
    }

    public boolean isBlikPossible() {
        return configurationDataProvider.isBlikPaymentPossible();
    }
}
