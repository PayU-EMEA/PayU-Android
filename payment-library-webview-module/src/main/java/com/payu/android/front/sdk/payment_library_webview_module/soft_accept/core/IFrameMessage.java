package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core;

import androidx.annotation.NonNull;

public enum IFrameMessage {

    DISPLAY_FRAME("DISPLAY_FRAME"),
    SUCCESS("AUTHENTICATION_SUCCESSFUL"),
    CANCEL("AUTHENTICATION_CANCELED"),
    NOT_REQUIRED("AUTHENTICATION_NOT_REQUIRED"),
    UNKNOWN("UNKNOWN");

    private final String message;

    IFrameMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public static IFrameMessage retrieveIFrameMessageByName(@NonNull String message) {
        for (IFrameMessage iFrameMessage : IFrameMessage.values()) {
            if (iFrameMessage.getMessage().equals(message.trim())) {
                return iFrameMessage;
            }
        }
        return UNKNOWN;
    }
}

