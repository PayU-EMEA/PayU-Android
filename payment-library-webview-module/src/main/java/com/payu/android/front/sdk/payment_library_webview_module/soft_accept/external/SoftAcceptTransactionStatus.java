package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external;

import android.os.Parcel;
import android.os.Parcelable;

public enum SoftAcceptTransactionStatus implements Parcelable {


    // For details check {@link https://developers.payu.com/pl/3ds_2.html}
    DISPLAY_FRAME,
    AUTHENTICATION_SUCCESSFUL,
    AUTHENTICATION_NOT_REQUIRED,
    AUTHENTICATION_CANCELED,
    @Deprecated
    USER_CANCELED,
    DISMISS_FRAGMENT_MANAGER,
    DIALOG_DISMISS_USER_CANCELED,
    DIALOG_DISMISS_OUTSIDE_USER_CANCELED,
    TIMEOUT,
    UNKNOWN;


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SoftAcceptTransactionStatus> CREATOR = new Creator<SoftAcceptTransactionStatus>() {
        @Override
        public SoftAcceptTransactionStatus createFromParcel(Parcel in) {
            return SoftAcceptTransactionStatus.values()[in.readInt()];
        }

        @Override
        public SoftAcceptTransactionStatus[] newArray(int size) {
            return new SoftAcceptTransactionStatus[size];
        }
    };

}
