package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript;

import androidx.annotation.NonNull;

public interface Configuration {

    String additionalParameters();

    String provideJsFunctionBridge(@NonNull String environment);

    String provideIframeHandler(@NonNull String redirectUrl);
}
