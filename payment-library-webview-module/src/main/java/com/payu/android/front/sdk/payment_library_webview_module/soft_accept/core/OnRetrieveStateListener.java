package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core;

import androidx.annotation.NonNull;

public interface OnRetrieveStateListener {

    void retrieveState(@NonNull String state, @NonNull String pageInfo, @NonNull String userAgentInfo);
}
