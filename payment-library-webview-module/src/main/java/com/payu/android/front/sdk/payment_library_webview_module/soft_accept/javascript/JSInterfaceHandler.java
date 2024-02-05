package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript;

import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.OnRetrieveStateListener;

public class JSInterfaceHandler {
    private final OnRetrieveStateListener listener;

    public JSInterfaceHandler(@NonNull OnRetrieveStateListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void handleState(@NonNull String state, @NonNull String pageInfo, @NonNull String userAgentInfo) {
        Log.v(JSInterfaceHandler.class.toString(), "print: " + state);
        listener.retrieveState(state, pageInfo, userAgentInfo);
    }
}
