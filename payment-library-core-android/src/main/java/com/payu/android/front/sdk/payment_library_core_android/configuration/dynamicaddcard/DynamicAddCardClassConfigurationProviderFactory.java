package com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;

public class DynamicAddCardClassConfigurationProviderFactory {

    private static DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider;

    public static DynamicAddCardClassConfigurationProvider createProvider(@NonNull Context context) {
        if (dynamicAddCardClassConfigurationProvider == null) {
            dynamicAddCardClassConfigurationProvider =
                    new DynamicAddCardClassConfigurationProvider(ConfigurationDataProviderHolder.getInstance(context),
                            (Application) context.getApplicationContext());
        }
        return dynamicAddCardClassConfigurationProvider;
    }
}
