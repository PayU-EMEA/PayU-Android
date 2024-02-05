package com.payu.android.front.sdk.payment_library_core_android;

import android.content.Context;

import com.payu.android.front.sdk.payment_environment_resolver.configuration.EnvironmentClassPathScanner;
import com.payu.android.front.sdk.payment_environment_resolver.configuration.RestEnvironmentResolver;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;

public class ConfigurationEnvironmentProvider {
    public static RestEnvironment provideEnvironment(Context context) {
        String environment = ConfigurationDataProviderHolder.getInstance(context).getEnvironment();
        return new RestEnvironmentResolver(new EnvironmentClassPathScanner(context.getApplicationInfo())).get(environment);
    }
}
