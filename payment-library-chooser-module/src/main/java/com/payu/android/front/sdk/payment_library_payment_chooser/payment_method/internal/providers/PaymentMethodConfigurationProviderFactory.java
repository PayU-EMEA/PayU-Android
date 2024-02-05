package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;

public class PaymentMethodConfigurationProviderFactory {

    private static PaymentMethodClassConfigurationProvider paymentMethodClassConfigurationProvider;

    public static PaymentMethodClassConfigurationProvider createProvider(@NonNull Context context) {
        if (paymentMethodClassConfigurationProvider == null) {
            paymentMethodClassConfigurationProvider =
                    new PaymentMethodClassConfigurationProvider(ConfigurationDataProviderHolder.getInstance(context),
                            (Application) context.getApplicationContext());
        }
        return paymentMethodClassConfigurationProvider;
    }
}
