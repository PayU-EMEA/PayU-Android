package com.payu.android.front.sdk.payment_library_core_android.configuration;

import android.content.Context;

import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.resource.StringResourceIdProvider;

public class ConfigurationDataProviderHolder {

    private static ConfigurationDataProvider configurationDataProvider;

    private ConfigurationDataProviderHolder() {
        // Static access only
    }

    public static synchronized ConfigurationDataProvider getInstance(Context context) {

        if (configurationDataProvider == null) {
            Context applicationContext = context.getApplicationContext();
            configurationDataProvider = new XmlConfigurationDataProvider(applicationContext.getResources(), //
                    new StringResourceIdProvider(applicationContext));
            setupLocale(configurationDataProvider);
        }

        return configurationDataProvider;
    }

    private static void setupLocale(ConfigurationDataProvider configurationDataProvider) {
        TranslationFactory.forceTranslation(configurationDataProvider.getLocale());
    }

    public static synchronized void setInstance(ConfigurationDataProvider instance) {
        configurationDataProvider = instance;
    }
}
