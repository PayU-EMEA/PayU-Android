package com.payu.android.front.sdk.payment_library_google_pay_module.service;

import androidx.annotation.Nullable;

import com.google.android.gms.common.api.Status;

public class ErrorStatus {
    private static final ErrorStatus UNKNOWN =
            new ErrorStatus(-11, "Unknown error while using Google Pay has occurred", false, false);
    private int errorCode;
    private String errorMessage;
    private boolean isCanceled;
    private boolean isInterrupted;

    private ErrorStatus(int errorCode, String errorMessage, boolean isCanceled, boolean isInterrupted) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.isCanceled = isCanceled;
        this.isInterrupted = isInterrupted;
    }

    public static ErrorStatus fromGooglePayStatus(@Nullable Status status) {
        if (status == null) {
            return UNKNOWN;
        }
        return new ErrorStatus(status.getStatusCode(), status.getStatusMessage(), status.isCanceled(), status.isInterrupted());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }
}
