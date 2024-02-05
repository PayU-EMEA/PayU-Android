package com.payu.android.front.sdk.payment_library_core_android.configuration.dynamicaddcard;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;

public class DynamicCardActionDelegate implements DynamicCardActions {
    private final ConfigurationDataProvider configurationDataProvider;
    private final DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider;

    protected DynamicCardActionDelegate(@NonNull DynamicAddCardClassConfigurationProvider dynamicAddCardClassConfigurationProvider,
                                     @NonNull ConfigurationDataProvider configurationDataProvider) {
        this.configurationDataProvider = configurationDataProvider;
        this.dynamicAddCardClassConfigurationProvider = dynamicAddCardClassConfigurationProvider;
    }

    @Override
    public boolean addCardFlow() {
        if (configurationDataProvider.getDynamicCardConfigurationClassName().isPresent()) {
            return dynamicAddCardClassConfigurationProvider.getDynamicCardActions().addCardFlow();
        }
        return configurationDataProvider.isAddCardPossible();
    }

    @Override
    public boolean saveAndUseOption() {
        if (configurationDataProvider.getDynamicCardConfigurationClassName().isPresent()) {
            return dynamicAddCardClassConfigurationProvider.getDynamicCardActions().saveAndUseOption();
        }
        return configurationDataProvider.isSaveAndUsePossible();
    }

    @Override
    public boolean scanCardOption() {
        if (configurationDataProvider.getDynamicCardConfigurationClassName().isPresent()) {
            return dynamicAddCardClassConfigurationProvider.getDynamicCardActions().scanCardOption();
        }
        return configurationDataProvider.isScanCardPossible();
    }
}
