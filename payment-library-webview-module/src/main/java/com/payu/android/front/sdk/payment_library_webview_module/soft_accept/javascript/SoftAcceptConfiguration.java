package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript;

import androidx.annotation.NonNull;

public class SoftAcceptConfiguration implements Configuration {
    @Override
    public String additionalParameters() {
        return "&sendCreq=false";
    }

    @Override
    public String provideJsFunctionBridge(@NonNull String environment) {
        return "javascript:(function() {" +
                "window.addEventListener ('message', handleMessage, false);" +
                "function handleMessage(msg) {" +
                "if(msg.origin ===" + environment + "){" +
                "JSInterfaceHandler.handleState(msg.data,window.location.href,navigator.userAgent);}" +
                "}})();";
    }

    @Override
    public String provideIframeHandler(@NonNull String redirectUrl) {
        return "<div class='modal'>" +
                "<div class='modal-content'>" +
                "<iframe   src=" + redirectUrl + ">" + //sandbox='allow-forms allow-scripts allow-same-origin'
                "</iframe>" +
                "</div>" +
                "</div>";
    }
}
