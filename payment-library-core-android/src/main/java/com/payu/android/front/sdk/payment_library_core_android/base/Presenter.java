package com.payu.android.front.sdk.payment_library_core_android.base;


import androidx.annotation.NonNull;

public interface Presenter<V> {

    void dropView();

    void onLoad();

    void takeView(@NonNull V view);
}
