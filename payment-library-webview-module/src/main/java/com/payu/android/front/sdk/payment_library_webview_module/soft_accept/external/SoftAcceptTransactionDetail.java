package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;

public class SoftAcceptTransactionDetail implements Parcelable {
    @NonNull
    private final SoftAcceptTransactionStatus softAcceptTransactionStatus;

    @NonNull
    private final AuthorizationDetails authorizationDetails;

    public SoftAcceptTransactionDetail(@NonNull SoftAcceptTransactionStatus softAcceptTransactionStatus, @NonNull AuthorizationDetails authorizationDetails) {
        this.softAcceptTransactionStatus = softAcceptTransactionStatus;
        this.authorizationDetails = authorizationDetails;
    }

    protected SoftAcceptTransactionDetail(Parcel in) {
        softAcceptTransactionStatus = in.readParcelable(SoftAcceptTransactionStatus.class.getClassLoader());
        authorizationDetails = in.readParcelable(AuthorizationDetails.class.getClassLoader());
    }

    public static final Creator<SoftAcceptTransactionDetail> CREATOR = new Creator<SoftAcceptTransactionDetail>() {
        @Override
        public SoftAcceptTransactionDetail createFromParcel(Parcel in) {
            return new SoftAcceptTransactionDetail(in);
        }

        @Override
        public SoftAcceptTransactionDetail[] newArray(int size) {
            return new SoftAcceptTransactionDetail[size];
        }
    };

    @NonNull
    public SoftAcceptTransactionStatus getSoftAcceptTransactionStatus() {
        return softAcceptTransactionStatus;
    }

    @NonNull
    public AuthorizationDetails getAuthorizationDetails() {
        return authorizationDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(softAcceptTransactionStatus, flags);
        dest.writeParcelable(authorizationDetails, flags);
    }
}
