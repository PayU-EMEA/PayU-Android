package com.payu.android.front.sdk.payment_library_core.util;

public class ProviderWrapper {

    public static <T> Provider<T> wrapInProvider(final T providable) {
        return new Provider<T>() {

            @Override
            public T get() {
                return providable;
            }
        };
    }
}
