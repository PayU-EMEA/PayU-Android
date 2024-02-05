package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository;

import android.content.Context;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodConfigurationProviderFactory;

public class BlikAmbiguityStaticHolder {
    private static BlikAmbiguityStaticHolder instance;
    @NonNull
    private BlikAmbiguityRepository repository;

    private BlikAmbiguityStaticHolder(@NonNull Context context) {
        repository = new BlikAmbiguityRepository(PaymentMethodConfigurationProviderFactory.createProvider(context));
    }

    public synchronized static BlikAmbiguityStaticHolder getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new BlikAmbiguityStaticHolder(context);
        }
        return instance;
    }

    @NonNull
    public BlikAmbiguityRepository getBlikAmbiguityRepository() {
        return repository;
    }
}
