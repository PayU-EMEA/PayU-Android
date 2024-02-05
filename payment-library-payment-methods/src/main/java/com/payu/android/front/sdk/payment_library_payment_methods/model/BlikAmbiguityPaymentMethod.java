package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class BlikAmbiguityPaymentMethod extends PaymentMethod implements Parcelable {

    @NonNull
    private String appLabel;

    BlikAmbiguityPaymentMethod(@NonNull String appKey, @NonNull String appLabel, @Nullable String appImageUrl) {
        super(appKey, appImageUrl, PaymentMethodStatus.ENABLED);
        this.appLabel = appLabel;
    }

    protected BlikAmbiguityPaymentMethod(Parcel in) {
        super(in);
        appLabel = in.readString();
    }

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.BLIK_AMBIGUITY;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(appLabel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BlikAmbiguityPaymentMethod> CREATOR = new Creator<BlikAmbiguityPaymentMethod>() {
        @Override
        public BlikAmbiguityPaymentMethod createFromParcel(Parcel in) {
            return new BlikAmbiguityPaymentMethod(in);
        }

        @Override
        public BlikAmbiguityPaymentMethod[] newArray(int size) {
            return new BlikAmbiguityPaymentMethod[size];
        }
    };


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + appLabel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " BlikAmbiguityPaymentMethod [ appLabel: " + appLabel + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlikAmbiguityPaymentMethod)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        BlikAmbiguityPaymentMethod that = (BlikAmbiguityPaymentMethod) obj;
        return appLabel.equals(that.appLabel);
    }


    @NonNull
    public String getAppLabel() {
        return appLabel;
    }
}
