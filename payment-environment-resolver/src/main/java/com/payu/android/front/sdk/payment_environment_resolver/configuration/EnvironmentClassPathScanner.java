package com.payu.android.front.sdk.payment_environment_resolver.configuration;

import android.content.pm.ApplicationInfo;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_environment_resolver.classpath.ClassPathScanner;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.ProductionRestEnvironment;


public class EnvironmentClassPathScanner extends ClassPathScanner {

    private static final String ENVIRONMENT_PACKAGES = ProductionRestEnvironment.class.getPackage().getName();

    public EnvironmentClassPathScanner(@NonNull ApplicationInfo applicationInfo) {
        super(ENVIRONMENT_PACKAGES, applicationInfo);
    }
}
