package com.payu.android.front.sdk.payment_library_google_pay_module.builder;

import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_google_pay_module.model.Currency;

import static com.google.common.base.Preconditions.checkArgument;

public class Cart {
    private final int totalPrice;
    private final Currency currency;

    private Cart(int totalPrice, @NonNull Currency currency) {
        this.totalPrice = totalPrice;
        this.currency = currency;
    }

    @NonNull
    public Currency getCurrency() {
        return currency;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public static class Builder {
        private int totalPrice;
        private Currency currency;

        public Cart build() {
            checkArgument(currency != null, "currency should be provided");
            checkArgument(totalPrice > 0, "price should be greater than 0");

            return new Cart(totalPrice, currency);
        }


        public Builder withCurrency(@NonNull Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder withTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

    }
}
