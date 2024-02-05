package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;

public abstract class AddressBarAction<T> extends BasePresenter<T> {

    public abstract void setAddress(@NonNull String url);

    public abstract void setIsAddressVerified(boolean isVerified);

}