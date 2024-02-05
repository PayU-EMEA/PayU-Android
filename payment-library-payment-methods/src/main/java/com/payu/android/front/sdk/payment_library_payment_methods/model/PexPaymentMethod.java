package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public final class PexPaymentMethod extends PaymentMethod implements Parcelable {
    @NonNull
    private final String accountNumber;
    @NonNull
    private final boolean preferred;
    @NonNull
    private final String name;
    @NonNull
    private final String payType;

    PexPaymentMethod(@NonNull String value, @NonNull String brandImageUrl, @NonNull PaymentMethodStatus status,
                     @NonNull boolean preferred, @NonNull String name, @NonNull String payType,
                     @NonNull String accountNumber) {
        super(value, brandImageUrl, status);
        this.preferred = preferred;
        this.name = name;
        this.payType = payType;
        this.accountNumber = accountNumber;
    }

    PexPaymentMethod(Parcel in) {
        super(in);
        this.preferred = in.readByte() != 0;
        this.name = in.readString();
        this.payType = in.readString();
        this.accountNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(accountNumber);
        dest.writeByte((byte) (preferred ? 1 : 0));
        dest.writeString(name);
        dest.writeString(payType);
    }

    public static final Creator<PexPaymentMethod> CREATOR = new Creator<PexPaymentMethod>() {
        @Override
        public PexPaymentMethod createFromParcel(Parcel in) {
            return new PexPaymentMethod(in);
        }

        @Override
        public PexPaymentMethod[] newArray(int size) {
            return new PexPaymentMethod[size];
        }
    };

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.PEX;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PexPaymentMethod)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PexPaymentMethod that = (PexPaymentMethod) o;

        if (preferred != that.preferred) {
            return false;
        }

        if (!accountNumber.equals(that.accountNumber)) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        return payType.equals(that.payType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (preferred ? 1 : 0);
        result = 31 * result + accountNumber.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + payType.hashCode();
        return result;
    }

    public boolean isPreferred() {
        return preferred;
    }

    @NonNull
    public String getAccountNumber() {
        return accountNumber;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPayType() {
        return payType;
    }
}
