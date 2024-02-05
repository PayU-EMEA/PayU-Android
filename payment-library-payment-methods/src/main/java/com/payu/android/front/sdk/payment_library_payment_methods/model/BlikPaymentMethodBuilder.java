package com.payu.android.front.sdk.payment_library_payment_methods.model;


import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

/**
 * Builder used for creation of the {@linkplain BlikPaymentMethod}. Data used by this builder should be fetch from the paymethods API endpoint.
 *
 * @see <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/oauth-api-endpoint/retrieve-paymethods-and-tokens">paymethods API endpoint
 * documentation</a>
 */
public class BlikPaymentMethodBuilder {
    private String value;
    private String brandImageUrl;
    private String type;


    public BlikPaymentMethodBuilder withValue(@NonNull String value) {
        this.value = value;
        return this;
    }

    public BlikPaymentMethodBuilder withBrandImageUrl(@NonNull String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
        return this;
    }

    public BlikPaymentMethodBuilder withType(@NonNull String type) {
        this.type = type;
        return this;
    }

    public BlikPaymentMethod build() {
        checkArgument(notEmpty(value), "value cannot be empty");
        checkArgument(notEmpty(brandImageUrl), "brandImageUrl cannot be empty");
        checkArgument(notEmpty(type), "type cannot be empty");

        return new BlikPaymentMethod(value, brandImageUrl, type);
    }
}
