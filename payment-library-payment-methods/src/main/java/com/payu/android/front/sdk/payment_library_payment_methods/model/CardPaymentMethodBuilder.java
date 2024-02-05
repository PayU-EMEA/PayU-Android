package com.payu.android.front.sdk.payment_library_payment_methods.model;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.payu.android.front.sdk.payment_library_core.util.StringUtils.notEmpty;

/**
 * Builder used for creation of the {@linkplain CardPaymentMethod}. Data used by this builder should be fetch from the paymethods API endpoint.
 *
 * @see <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/oauth-api-endpoint/retrieve-paymethods-and-tokens">paymethods API endpoint
 * documentation</a>
 */
public class CardPaymentMethodBuilder {
    private String value;
    private String brandImageUrl;
    private String status;
    private String cardExpirationYear;
    private String cardExpirationMonth;
    private String cardNumberMasked;
    private String cardScheme;
    private boolean preferred;

    /**
     * @param value - Token for the card payment method provided from paymethods API endpoint
     * @return self
     */
    public CardPaymentMethodBuilder withValue(@NonNull String value) {
        this.value = value;
        return this;
    }

    /**
     * @param brandImageUrl - Icon of the Card payment method provided from paymethods API endpoint
     * @return self
     */
    public CardPaymentMethodBuilder withBrandImageUrl(@NonNull String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
        return this;
    }

    /**
     * @param status - Status of the Card payment method provided from paymethods API endpoint. Can be either "ACTIVE" or "EXPIRED"
     * @return self
     */
    public CardPaymentMethodBuilder withStatus(@NonNull String status) {
        this.status = status;
        return this;
    }

    /**
     * @param cardExpirationYear - Expiration year of the card linked to this payment method provided from paymethods API endpoint.
     * @return self
     */
    public CardPaymentMethodBuilder withCardExpirationYear(@NonNull String cardExpirationYear) {
        this.cardExpirationYear = cardExpirationYear;
        return this;
    }

    /**
     * @param cardExpirationMonth - Expiration month of the card linked to this payment method provided from paymethods API endpoint.
     * @return self
     */
    public CardPaymentMethodBuilder withCardExpirationMonth(@NonNull String cardExpirationMonth) {
        this.cardExpirationMonth = cardExpirationMonth;
        return this;
    }

    /**
     * @param cardNumberMasked - Masked card number for the card linked to this payment method provided from paymethods API endpoint.
     * @return self
     */
    public CardPaymentMethodBuilder withCardNumberMasked(@NonNull String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
        return this;
    }

    /**
     * @param cardScheme - Card scheme of the card linked to this payment method provided from paymethods API endpoint.
     * @return self
     */
    public CardPaymentMethodBuilder withCardScheme(@NonNull String cardScheme) {
        this.cardScheme = cardScheme;
        return this;
    }

    /**
     * @param preferred - Indicates whether this payment method provided from paymethods API endpoint should be shown as first.
     * @return self
     */
    public CardPaymentMethodBuilder withPreferred(boolean preferred) {
        this.preferred = preferred;
        return this;
    }

    /**
     * @return validated instance of the {@linkplain CardPaymentMethod}
     */
    public CardPaymentMethod build() {
        checkArgument(notEmpty(value), "value cannot be empty");
        checkArgument(notEmpty(brandImageUrl), "brandImageUrl cannot be empty");
        checkArgument(notEmpty(cardExpirationYear), "cardExpirationYear cannot be empty");
        checkArgument(notEmpty(cardExpirationMonth), "cardExpirationMonth cannot be empty");
        checkArgument(notEmpty(cardNumberMasked), "cardNumberMasked cannot be empty");

        StatusValidator validator = new PaymentMethodStatusValidator();
        checkArgument(validator.validate(status),
                String.format("Invalid status: %s. Only %s are allowed.", status, validator.getAllowedStatusList()));
        return new CardPaymentMethod(value, brandImageUrl,
                status.equals(StatusConstants.ACTIVE) ? PaymentMethodStatus.ENABLED : PaymentMethodStatus.DISABLED, cardExpirationYear,
                cardExpirationMonth, cardNumberMasked, cardScheme, preferred);
    }
}