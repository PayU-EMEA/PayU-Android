package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class PaymentMethod implements Parcelable {
    @NonNull
    private final String value;
    @Nullable
    private final String brandImageUrl;
    @NonNull
    private final PaymentMethodStatus status;

    PaymentMethod(@NonNull String value, @Nullable String brandImageUrl, @NonNull PaymentMethodStatus status) {
        this.value = value;
        this.brandImageUrl = brandImageUrl;
        this.status = status;
    }

    PaymentMethod(Parcel in) {
        value = in.readString();
        brandImageUrl = in.readString();
        status = PaymentMethodStatus.values()[in.readInt()];
    }

    @NonNull
    public PaymentMethodStatus getStatus() {
        return status;
    }

    @NonNull
    public String getValue() {
        return value;
    }

    @Nullable
    public String getBrandImageUrl() {
        return brandImageUrl;
    }

    @NonNull
    public abstract PaymentType getPaymentType();

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(brandImageUrl);
        dest.writeInt(status.ordinal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethod)) {
            return false;
        }

        PaymentMethod that = (PaymentMethod) o;

        if (!value.equals(that.value)) {
            return false;
        }
        if (!brandImageUrl.equals(that.brandImageUrl)) {
            return false;
        }
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + brandImageUrl.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payment method " + ", status: " + status.name() + ", value: " + value + ",  brandImageUrl: " + brandImageUrl;
    }
}
