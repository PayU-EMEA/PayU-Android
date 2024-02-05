package com.payu.android.front.sdk.payment_library_core_android.base;

import androidx.annotation.NonNull;

public class BasePresenter<T> implements Presenter<T> {

    protected T view;

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void onLoad() {
        // template method
    }

    @Override
    public void takeView(@NonNull T view) {
        this.view = view;
        onLoad();
    }

    protected boolean hasView() {
        return view != null;
    }
}