package com.payu.android.front.sdk.payment_library_core_android.configuration;

public class StyleClassConfigurationProvider extends ClassConfigurationProvider {

    public StyleClassConfigurationProvider(ConfigurationDataProvider configurationDataProvider) {
        super(configurationDataProvider);
    }

    public StyleConfiguration getStyleFromConfiguration() {
        String styleClassName = configurationDataProvider.getStyleClassName();
        return (StyleConfiguration) createObjectFromClass(styleClassName);
    }

}
