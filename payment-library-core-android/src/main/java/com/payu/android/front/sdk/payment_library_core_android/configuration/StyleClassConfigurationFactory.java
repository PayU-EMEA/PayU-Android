package com.payu.android.front.sdk.payment_library_core_android.configuration;

import android.content.Context;

public class StyleClassConfigurationFactory {
    public static StyleClassConfigurationProvider createStyleClassProvider(Context context) {
        return new StyleClassConfigurationProvider(ConfigurationDataProviderHolder.getInstance(context));
    }
}
