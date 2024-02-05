package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

/**
 * Builder used for creation of the {@linkplain PayByLinkPaymentMethod}. Data used by this builder should be fetch from the paymethods API endpoint.
 *
 * @see <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/oauth-api-endpoint/retrieve-paymethods-and-tokens">paymethods API endpoint
 * documentation</a>
 */
public class PayByLinkPaymentMethodBuilder {
    private String name;
    private String value;
    private String brandImageUrl;
    private String status;

    /**
     * @param name - Name of the Pbl payment method provided from paymethods API endpoint
     * @return self
     */
    public PayByLinkPaymentMethodBuilder withName(@NonNull String name) {
        this.name = name;
        return this;
    }

    /**
     * @param value - Value of the Pbl payment method provided from paymethods API endpoint
     * @return self
     */
    public PayByLinkPaymentMethodBuilder withValue(@NonNull String value) {
        this.value = value;
        return this;
    }

    /**
     * @param brandImageUrl - Icon of the Pbl payment method provided from paymethods API endpoint
     * @return self
     */
    public PayByLinkPaymentMethodBuilder withBrandImageUrl(@NonNull String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
        return this;
    }

    /**
     * @param status - Status of the Pbl payment method provided from paymethods API endpoint. Can be either "ENABLED", "TEMPORARY_DISABLED" or
     *               "DISABLED"
     * @return self
     */
    public PayByLinkPaymentMethodBuilder withStatus(@NonNull String status) {
        this.status = status;
        return this;
    }

    /**
     * @return validated instance of the {@linkplain PayByLinkPaymentMethod}
     */
    public PayByLinkPaymentMethod build() {
        checkArgument(notEmpty(value), "value cannot be empty");
        checkArgument(notEmpty(brandImageUrl), "brandImageUrl cannot be empty");
        checkArgument(notEmpty(name), "Name cannot be empty");
        StatusValidator validator = new PblStatusValidator();
        checkArgument(validator.validate(status),
                String.format("Invalid status: %s. Only %s are allowed.", status, validator.getAllowedStatusList()));
        return new PayByLinkPaymentMethod(name, value, brandImageUrl,
                status.equals(StatusConstants.ENABLED) ? PaymentMethodStatus.ENABLED : PaymentMethodStatus.DISABLED);
    }
}