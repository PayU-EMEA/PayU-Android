package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Pair;

public class CombineLiveData<F, S> extends MediatorLiveData<Pair<F, S>> {
    public CombineLiveData(final LiveData<F> first, final LiveData<S> second) {
        addSource(first, new Observer<F>() {
            @Override
            public void onChanged(@Nullable F firstValue) {
                if (checkIfNull(first, second)) {
                    return;
                }

                setValue(Pair.create(firstValue, second.getValue()));
            }
        });
        addSource(second, new Observer<S>() {
            @Override
            public void onChanged(@Nullable S secondValue) {
                if (checkIfNull(first, second)) {
                    return;
                }
                setValue(Pair.create(first.getValue(), secondValue));
            }
        });
    }

    private boolean checkIfNull(@Nullable final LiveData<F> first, @Nullable final LiveData<S> second) {
        return first == null || first.getValue() == null || second == null || second.getValue() == null;
    }
}
