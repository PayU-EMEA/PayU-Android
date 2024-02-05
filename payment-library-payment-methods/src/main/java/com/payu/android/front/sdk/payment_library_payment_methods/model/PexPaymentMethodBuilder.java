package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

public class PexPaymentMethodBuilder {
    private String value;
    private String brandImageUrl;
    private String status;
    private String accountNumber;
    private boolean preferred;
    private String name;
    private String payType;

    /**
     * @param value - Token for the card payment method provided from paymethods API endpoint
     * @return self
     */
    public PexPaymentMethodBuilder withValue(@NonNull String value) {
        this.value = value;
        return this;
    }

    /**
     * @param brandImageUrl - Icon of the PEX payment method provided from paymethods API endpoint
     * @return self
     */
    public PexPaymentMethodBuilder withBrandImageUrl(@NonNull String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
        return this;
    }

    /**
     * @param status - Status of the PEX payment method provided from paymethods API endpoint. Can be either "ACTIVE" or "EXPIRED"
     * @return self
     */
    public PexPaymentMethodBuilder withStatus(@NonNull String status) {
        this.status = status;
        return this;
    }

    /**
     * @param accountNumber - hashed number of the account provided from paymethods API endpoint.
     * @return self
     */
    public PexPaymentMethodBuilder withAccountNumber(@NonNull String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * @param preferred - Indicates whether this payment method provided from paymethods API endpoint should be shown as first.
     * @return self
     */
    public PexPaymentMethodBuilder withPreferred(boolean preferred) {
        this.preferred = preferred;
        return this;
    }

    /**
     * @param name - name of the PEX payment this should be provided from paymenthods API endpoint.
     * @return self
     */
    public PexPaymentMethodBuilder withName(@NonNull String name) {
        this.name = name;
        return this;
    }

    /**
     * @param payType - this should be provided from paymenthods API endpoint.
     * @return self
     */
    public PexPaymentMethodBuilder withPayType(@NonNull String payType) {
        this.payType = payType;
        return this;
    }

    /**
     * @return validated instance of the {@linkplain PexPaymentMethod}
     */
    public PexPaymentMethod build() {
        checkArgument(notEmpty(value), "value cannot be empty");
        checkArgument(notEmpty(brandImageUrl), "brandImageUrl cannot be empty");
        checkArgument(notEmpty(name), "name cannot be empty");

        StatusValidator validator = new PaymentMethodStatusValidator();
        checkArgument(validator.validate(status),
                String.format("Invalid status: %s. Only %s are allowed.", status, validator.getAllowedStatusList()));
        return new PexPaymentMethod(value, brandImageUrl, status.equals(StatusConstants.ACTIVE) ? PaymentMethodStatus.ENABLED : PaymentMethodStatus.DISABLED,
                preferred, name, payType, accountNumber);
    }
}
