package com.payu.android.front.sdk.payment_library_payment_methods.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public final class CardPaymentMethod extends PaymentMethod implements Parcelable {
    public static final Creator<CardPaymentMethod> CREATOR = new Creator<CardPaymentMethod>() {
        @Override
        public CardPaymentMethod createFromParcel(Parcel in) {
            return new CardPaymentMethod(in);
        }

        @Override
        public CardPaymentMethod[] newArray(int size) {
            return new CardPaymentMethod[size];
        }
    };
    @NonNull
    private final String cardExpirationYear;
    @NonNull
    private final String cardExpirationMonth;
    @NonNull
    private final String cardNumberMasked;
    @NonNull
    private final String cardScheme;
    private final boolean preferred;

    CardPaymentMethod(
            @NonNull String value, @NonNull String brandImageUrl, @NonNull PaymentMethodStatus status, @NonNull String cardExpirationYear,
            @NonNull String cardExpirationMonth, @NonNull String cardNumberMasked, @NonNull String cardScheme, boolean preferred) {
        super(value, brandImageUrl, status);
        this.cardExpirationYear = cardExpirationYear;
        this.cardExpirationMonth = cardExpirationMonth;
        this.cardNumberMasked = cardNumberMasked;
        this.cardScheme = cardScheme;
        this.preferred = preferred;
    }

    private CardPaymentMethod(Parcel in) {
        super(in);
        cardExpirationYear = in.readString();
        cardExpirationMonth = in.readString();
        cardNumberMasked = in.readString();
        cardScheme = in.readString();
        preferred = in.readByte() != 0;
    }

    @NonNull
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CARD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String getCardExpirationYear() {
        return cardExpirationYear;
    }

    @NonNull
    public String getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    @NonNull
    public String getCardNumberMasked() {
        return cardNumberMasked;
    }

    @NonNull
    public String getCardScheme() {
        return cardScheme;
    }

    public boolean isPreferred() {
        return preferred;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(cardExpirationYear);
        dest.writeString(cardExpirationMonth);
        dest.writeString(cardNumberMasked);
        dest.writeString(cardScheme);
        dest.writeByte((byte) (preferred ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardPaymentMethod)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CardPaymentMethod that = (CardPaymentMethod) o;

        if (preferred != that.preferred) {
            return false;
        }
        if (!cardExpirationYear.equals(that.cardExpirationYear)) {
            return false;
        }
        if (!cardExpirationMonth.equals(that.cardExpirationMonth)) {
            return false;
        }
        if (!cardNumberMasked.equals(that.cardNumberMasked)) {
            return false;
        }
        return cardScheme.equals(that.cardScheme);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cardExpirationYear.hashCode();
        result = 31 * result + cardExpirationMonth.hashCode();
        result = 31 * result + cardNumberMasked.hashCode();
        result = 31 * result + cardScheme.hashCode();
        result = 31 * result + (preferred ? 1 : 0);
        return result;
    }
}
