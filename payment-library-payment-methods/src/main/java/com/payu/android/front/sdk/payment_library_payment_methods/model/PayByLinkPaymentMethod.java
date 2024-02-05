package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public final class PayByLinkPaymentMethod extends PaymentMethod implements Parcelable {
    public static final Creator<PayByLinkPaymentMethod> CREATOR = new Creator<PayByLinkPaymentMethod>() {
        @Override
        public PayByLinkPaymentMethod createFromParcel(Parcel in) {
            return new PayByLinkPaymentMethod(in);
        }

        @Override
        public PayByLinkPaymentMethod[] newArray(int size) {
            return new PayByLinkPaymentMethod[size];
        }
    };
    @NonNull
    private final String name;

    private PayByLinkPaymentMethod(Parcel in) {
        super(in);
        this.name = in.readString();
    }

    PayByLinkPaymentMethod(@NonNull String name, @NonNull String value, @NonNull String brandImageUrl, @NonNull PaymentMethodStatus status) {
        super(value, brandImageUrl, status);
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return getValue().equals("ap")
               ? PaymentType.GOOGLE_PAY
               : PaymentType.PBL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayByLinkPaymentMethod)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PayByLinkPaymentMethod that = (PayByLinkPaymentMethod) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
