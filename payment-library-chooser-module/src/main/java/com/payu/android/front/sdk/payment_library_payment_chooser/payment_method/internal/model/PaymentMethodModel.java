package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model;

import android.annotation.SuppressLint;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PaymentMethodModel {
    @NonNull
    private final String id;
    @NonNull
    private final PaymentMethodType paymentMethodType;
    @DrawableRes
    private final int imageRes;
    @Nullable
    private final String imageUrl;
    @Nullable
    private final String titleLabel;
    @Nullable
    private final String titleContent;
    @Nullable
    private final String descriptionLabel;
    @Nullable
    private final String descriptionContent;
    private final boolean canBeRemoved;
    private final boolean enabled;

    private PaymentMethodModel(
            @NonNull String id, @NonNull PaymentMethodType paymentMethodType, @Nullable String imageUrl, @DrawableRes int imageRes,
            @Nullable String titleLabel,
            @Nullable String titleContent, @Nullable String descriptionLabel, @Nullable String descriptionContent, boolean canBeRemoved,
            boolean enabled) {
        this.id = id;
        this.paymentMethodType = paymentMethodType;
        this.imageUrl = imageUrl;
        this.imageRes = imageRes;
        this.titleLabel = titleLabel;
        this.titleContent = titleContent;
        this.descriptionLabel = descriptionLabel;
        this.descriptionContent = descriptionContent;
        this.canBeRemoved = canBeRemoved;
        this.enabled = enabled;
    }

    public int getImageRes() {
        return imageRes;
    }

    public boolean canBeRemoved() {
        return canBeRemoved;
    }

    public boolean hasImageUrl() {
        return imageUrl != null && !imageUrl.isEmpty();
    }

    public boolean hasImage() {
        return hasImageUrl() || hasResourceImage();
    }

    @SuppressLint("ResourceType")
    public boolean hasResourceImage() {
        return imageRes > 0;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public PaymentMethodType getPaymentMethodType() {
        return paymentMethodType;
    }

    @Nullable
    public String getDescriptionContent() {
        return descriptionContent;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    @Nullable
    public String getTitleLabel() {
        return titleLabel;
    }

    @Nullable
    public String getTitleContent() {
        return titleContent;
    }

    @Nullable
    public String getDescriptionLabel() {
        return descriptionLabel;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentMethodModel)) return false;

        PaymentMethodModel that = (PaymentMethodModel) o;

        if (imageRes != that.imageRes) return false;
        if (canBeRemoved != that.canBeRemoved) return false;
        if (enabled != that.enabled) return false;
        if (!id.equals(that.id)) return false;
        if (paymentMethodType != that.paymentMethodType) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
            return false;
        if (titleLabel != null ? !titleLabel.equals(that.titleLabel) : that.titleLabel != null)
            return false;
        if (titleContent != null ? !titleContent.equals(that.titleContent) : that.titleContent != null)
            return false;
        if (descriptionLabel != null ? !descriptionLabel.equals(that.descriptionLabel) : that.descriptionLabel != null)
            return false;
        return descriptionContent != null ? descriptionContent.equals(that.descriptionContent) : that.descriptionContent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + paymentMethodType.hashCode();
        result = 31 * result + imageRes;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (titleLabel != null ? titleLabel.hashCode() : 0);
        result = 31 * result + (titleContent != null ? titleContent.hashCode() : 0);
        result = 31 * result + (descriptionLabel != null ? descriptionLabel.hashCode() : 0);
        result = 31 * result + (descriptionContent != null ? descriptionContent.hashCode() : 0);
        result = 31 * result + (canBeRemoved ? 1 : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }

    public static class Builder {
        private static final int NO_ICON = -1;
        private String imageUrl;
        private int imageRes = NO_ICON;
        private String titleLabel;
        private String titleContent;
        private String descriptionLabel;
        private String descriptionContent;
        private String id;
        private PaymentMethodType paymentMethodType;
        private boolean canBeRemoved;
        private boolean enabled;

        public Builder withImageUrl(@NonNull String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withImageRes(@DrawableRes int imageRes) {
            this.imageRes = imageRes;
            return this;
        }

        public Builder withId(@NonNull String id) {
            this.id = id;
            return this;
        }

        public Builder withPaymentMethodType(@NonNull PaymentMethodType paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
            return this;
        }

        public Builder withTitleLabel(String titleLabel) {
            this.titleLabel = titleLabel;
            return this;
        }

        public Builder withTitleContent(String titleContent) {
            this.titleContent = titleContent;
            return this;
        }

        public Builder withDescriptionLabel(String descriptionLabel) {
            this.descriptionLabel = descriptionLabel;
            return this;
        }

        public Builder withDescriptionContent(String descriptionContent) {
            this.descriptionContent = descriptionContent;
            return this;
        }

        public Builder canBeRemoved(boolean canBeRemoved) {
            this.canBeRemoved = canBeRemoved;
            return this;
        }

        public Builder isEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public PaymentMethodModel build() {
            checkArgument(id != null && !id.isEmpty(), "id is required");
            checkNotNull(paymentMethodType, "paymentMethodType is required");
            return new PaymentMethodModel(id, paymentMethodType, imageUrl, imageRes, titleLabel, titleContent, descriptionLabel, descriptionContent,
                    canBeRemoved, enabled);
        }
    }
}
