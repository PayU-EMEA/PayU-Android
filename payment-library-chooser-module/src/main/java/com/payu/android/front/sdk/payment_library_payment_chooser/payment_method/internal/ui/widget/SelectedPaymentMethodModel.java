package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SelectedPaymentMethodModel {
    @NonNull
    private final String imageUrl;
    @NonNull
    private final String header;
    @Nullable
    private final String content;
    @NonNull
    private final String id;

    SelectedPaymentMethodModel(@NonNull String imageUrl, @NonNull String header, @Nullable String content, @NonNull String id) {
        this.imageUrl = imageUrl;
        this.header = header;
        this.content = content;
        this.id = id;
    }

    @Override
    public String toString() {
        return "SelectedPaymentMethodModel{" + "imageUrl='" + imageUrl + '\'' + ", header='" + header + '\'' + ", content='" + content + '\'' + ", " +
                "" + "id='" + id + '\'' + '}';
    }

    @NonNull
    public String getHeader() {
        return header;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }
}
