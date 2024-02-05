package com.payu.android.front.sdk.payment_library_webview_module.web.event;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentStatus;

public class PaymentDetails implements Parcelable {
    private WebPaymentStatus webPaymentStatus;
    private Optional<String> orderId;
    private Optional<String> extOrderId;
    private Optional<String> continuePaymentUrl;

    public PaymentDetails(WebPaymentStatus webPaymentStatus, @Nullable String orderId, @Nullable String extOrderId, @Nullable String continuePaymentUrl) {
        this.webPaymentStatus = webPaymentStatus;
        this.orderId = Optional.fromNullable(orderId);
        this.extOrderId = Optional.fromNullable(extOrderId);
        this.continuePaymentUrl = Optional.fromNullable(continuePaymentUrl);
    }


    public static final Creator<PaymentDetails> CREATOR = new Creator<PaymentDetails>() {
        @Override
        public PaymentDetails createFromParcel(Parcel in) {
            return new PaymentDetails((WebPaymentStatus) in.readSerializable(), in.readString(), in.readString(), in.readString());
        }

        @Override
        public PaymentDetails[] newArray(int size) {
            return new PaymentDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(webPaymentStatus);
        parcel.writeString(orderId.orNull());
        parcel.writeString(extOrderId.orNull());
        parcel.writeString(continuePaymentUrl.orNull());
    }

    public WebPaymentStatus getWebPaymentStatus() {
        return webPaymentStatus;
    }

    public Optional<String> getOrderId() {
        return orderId;
    }

    public Optional<String> getExtOrderId() {
        return extOrderId;
    }

    public Optional<String> getContinuePaymentUrl() {
        return continuePaymentUrl;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "WebPaymentStatus=" + webPaymentStatus +
                ", orderId=" + orderId.orNull() +
                ", extOrderId=" + extOrderId.orNull() +
                ", continuePaymentUrl=" + continuePaymentUrl.orNull() +
                '}';
    }
}
