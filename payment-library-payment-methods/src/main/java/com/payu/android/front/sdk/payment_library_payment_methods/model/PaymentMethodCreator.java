package com.payu.android.front.sdk.payment_library_payment_methods.model;

/**
 * Helper class, which provides builders for {@linkplain CardPaymentMethod} and {@linkplain PayByLinkPaymentMethod}
 */
public final class PaymentMethodCreator {

    /**
     * Returns builder which helps with creation of Card payments. For more details see {@linkplain CardPaymentMethodBuilder}
     *
     * @return {@linkplain CardPaymentMethodBuilder}, which is required for the creation of the {@linkplain CardPaymentMethod}
     */
    public static CardPaymentMethodBuilder cardBuilder() {
        return new CardPaymentMethodBuilder();
    }

    /**
     * Returns builder which helps with creation of Pbl payments. For more details see {@linkplain PayByLinkPaymentMethodBuilder}
     *
     * @return {@linkplain PayByLinkPaymentMethodBuilder}, which is required for the creation of the {@linkplain PayByLinkPaymentMethod}
     */
    public static PayByLinkPaymentMethodBuilder pblBuilder() {
        return new PayByLinkPaymentMethodBuilder();
    }

    /**
     * Returns builder which helps with creation of Blik payments. For more details see {@linkplain BlikPaymentMethodBuilder}
     *
     * @return {@linkplain BlikPaymentMethodBuilder}, which is required for the creation of the {@linkplain BlikPaymentMethod}
     */
    public static BlikPaymentMethodBuilder blikPaymentBuilder() {
        return new BlikPaymentMethodBuilder();
    }

    /**
     * Returns builder which helps with creation of Blik Ambiguity payments. For more details see {@linkplain BlikAmbiguityPaymentMethodBuilder}
     *
     * @return {@linkplain BlikAmbiguityPaymentMethodBuilder}, which is required for the creation of the {@linkplain BlikAmbiguityPaymentMethod}
     */
    public static BlikAmbiguityPaymentMethodBuilder blikAmbiguityPaymentMethodBuilder() {
        return new BlikAmbiguityPaymentMethodBuilder();
    }

    /**
     * Returns builder which helps with creation of PEX payments. For more details see {@linkplain PexPaymentMethodBuilder}
     *
     * @return {@linkplain PexPaymentMethodBuilder}, which is required for the creation of the {@linkplain PexPaymentMethod}
     */
    public static PexPaymentMethodBuilder pexPaymentMethodBuilder() {
        return new PexPaymentMethodBuilder();
    }

}
