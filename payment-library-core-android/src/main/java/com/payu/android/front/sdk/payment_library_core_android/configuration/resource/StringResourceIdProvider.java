package com.payu.android.front.sdk.payment_library_core_android.configuration.resource;


import android.content.Context;

public class StringResourceIdProvider {

    private static final String STRING_RESOURCE_TYPE = "string";
    private static final String BOOL_RESOURCE_TYPE = "bool";
    private Context context;

    public StringResourceIdProvider(Context context) {
        this.context = context;
    }

    public int getStringIdByName(String configKey) {
        return context.getResources().getIdentifier(configKey, STRING_RESOURCE_TYPE, context.getPackageName());
    }

    public int getBooleanIdByName(String configKey) {
        return context.getResources().getIdentifier(configKey, BOOL_RESOURCE_TYPE, context.getPackageName());
    }
}
