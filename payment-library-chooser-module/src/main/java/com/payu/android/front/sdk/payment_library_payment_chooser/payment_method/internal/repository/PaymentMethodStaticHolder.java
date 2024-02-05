package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodConfigurationProviderFactory;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodPersistenceStorage;

public class PaymentMethodStaticHolder {
    private static PaymentMethodStaticHolder instance;
    @NonNull
    private PaymentMethodRepository paymentMethodRepository;
    @Nullable
    private PaymentMethodsAdapter paymentMethodsAdapter;

    private PaymentMethodStaticHolder(@NonNull Context context) {
        paymentMethodRepository =
                new PaymentMethodRepository(PaymentMethodConfigurationProviderFactory.createProvider(context),
                        new SelectedPaymentMethodPersistenceStorage(context), new TokenHasher());
    }

    public synchronized static PaymentMethodStaticHolder getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new PaymentMethodStaticHolder(context);
        }
        return instance;
    }

    @Nullable
    public PaymentMethodsAdapter getPaymentMethodsAdapter() {
        return paymentMethodsAdapter;
    }

    public void setPaymentMethodsAdapter(@Nullable PaymentMethodsAdapter paymentMethodsAdapter) {
        this.paymentMethodsAdapter = paymentMethodsAdapter;
    }

    @NonNull
    public PaymentMethodRepository getPaymentMethodRepository() {
        return paymentMethodRepository;
    }
}
