package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method;

import android.content.Context;
import androidx.annotation.NonNull;

import com.google.common.base.Optional;

public class SelectedPaymentMethodPersistenceStorage {
    private static final String PREFERENCES_NAME = "PAYU_TOKEN_PREFERENCES";
    private static final String SELECTED_METHOD_KEY = "SELECTED_METHOD";
    @NonNull
    private Context context;

    public SelectedPaymentMethodPersistenceStorage(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public Optional<String> getSelectedMethodHash() {
        String selectedMethod = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getString(SELECTED_METHOD_KEY, null);
        return Optional.fromNullable(selectedMethod);
    }

    public void saveSelectedPaymentMethodHash(@NonNull String hash) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(SELECTED_METHOD_KEY, hash).apply();
    }

    public void clear() {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().remove(SELECTED_METHOD_KEY).apply();
    }
}
