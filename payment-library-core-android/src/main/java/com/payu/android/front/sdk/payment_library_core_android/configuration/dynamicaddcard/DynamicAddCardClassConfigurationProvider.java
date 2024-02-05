package com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard;

import android.app.Application;

import androidx.annotation.Nullable;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;

public class DynamicAddCardClassConfigurationProvider extends ClassConfigurationProvider {
    private DynamicCardActions dynamicCardActions;
    private final Application application;

    protected DynamicAddCardClassConfigurationProvider(ConfigurationDataProvider configurationDataProvider, Application application) {
        super(configurationDataProvider);
        this.application = application;
    }


    @Nullable
    public DynamicCardActions getDynamicCardActions() {
        if (dynamicCardActions == null) {
            Optional<String> dynamicCardConfigurationClassName = configurationDataProvider.getDynamicCardConfigurationClassName();
            if (!dynamicCardConfigurationClassName.isPresent()) {
                return null;
            }
            dynamicCardActions = (DynamicCardActions) createObjectFromClass(dynamicCardConfigurationClassName.get(), application);
        }
        return dynamicCardActions;
    }
}
