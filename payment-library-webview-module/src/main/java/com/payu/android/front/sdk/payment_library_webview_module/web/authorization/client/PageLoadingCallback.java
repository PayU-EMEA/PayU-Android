package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

public interface PageLoadingCallback {

    PageLoadingCallback HOLLOW_IMPLEMENTATION = new PageLoadingCallback() {

        @Override
        public void onPageLoadStarted(String url) {
            // empty callback
        }
    };

    void onPageLoadStarted(String url);
}
