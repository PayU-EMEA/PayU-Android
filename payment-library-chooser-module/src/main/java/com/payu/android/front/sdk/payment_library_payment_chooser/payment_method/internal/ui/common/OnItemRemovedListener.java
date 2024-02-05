package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common;

import androidx.annotation.NonNull;

public interface OnItemRemovedListener<T> {
    void onRemoved(@NonNull T element);
}
