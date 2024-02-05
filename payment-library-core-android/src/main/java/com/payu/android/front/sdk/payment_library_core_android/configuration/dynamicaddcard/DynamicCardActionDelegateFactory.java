package com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;

public class DynamicCardActionDelegateFactory {
    private static DynamicCardActionDelegate instance;

    public static DynamicCardActionDelegate create(@NonNull DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider,
                                                   @NonNull ConfigurationDataProvider configurationDataProvider) {
        if (instance == null) {
            instance = new DynamicCardActionDelegate(dynamicAddCardClassConfigurationProvider, configurationDataProvider);
        }
        return instance;
    }
}
