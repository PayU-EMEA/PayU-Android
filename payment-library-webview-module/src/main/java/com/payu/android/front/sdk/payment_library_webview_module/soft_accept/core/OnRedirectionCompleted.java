package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionDetail;

public interface OnRedirectionCompleted {

    void onDetailReceived(@NonNull SoftAcceptTransactionDetail detail);
}
