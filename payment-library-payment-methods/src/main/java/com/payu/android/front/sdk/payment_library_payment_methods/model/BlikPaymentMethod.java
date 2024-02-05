package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class BlikPaymentMethod extends PaymentMethod implements Parcelable {
    @NonNull
    private final String type;

    BlikPaymentMethod(@NonNull String value, @NonNull String brandImageUrl, @NonNull String type) {
        super(value, brandImageUrl, PaymentMethodStatus.ENABLED);
        this.type = type;

    }

    BlikPaymentMethod(Parcel in) {
        super(in);
        this.type = in.readString();
    }

    public static final Creator<BlikPaymentMethod> CREATOR = new Creator<BlikPaymentMethod>() {
        @Override
        public BlikPaymentMethod createFromParcel(Parcel in) {
            return new BlikPaymentMethod(in);
        }

        @Override
        public BlikPaymentMethod[] newArray(int size) {
            return new BlikPaymentMethod[size];
        }
    };

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.BLIK_TOKENS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlikPaymentMethod)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        BlikPaymentMethod that = (BlikPaymentMethod) o;

        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
