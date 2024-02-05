package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model;

import androidx.annotation.NonNull;

public class PayByLinkModel {
    @NonNull
    private final String bankName;
    @NonNull
    private final String id;
    @NonNull
    private final String imageUrl;
    private final boolean enabled;

    public PayByLinkModel(@NonNull String bankName, @NonNull String id, @NonNull String imageUrl, boolean enabled) {
        this.bankName = bankName;
        this.id = id;
        this.imageUrl = imageUrl;
        this.enabled = enabled;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PayByLinkModel{" + "bankName='" + bankName + '\'' + ", id='" + id + '\'' + ", imageUrl='" + imageUrl + '\'' + ", enabled=" +
                enabled + '}';
    }

    @NonNull
    public String getBankName() {
        return bankName;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
