package com.payu.android.front.sdk.payment_add_card_module.issuer;

public enum CardIssuer {

    UNKNOWN("payment_method_placeholder.png", "UNKNOWN"),
    VISA("acceptance_mark/visa.png", "VS"),
    MASTER_CARD("acceptance_mark/mastercard.png", "MC"),
    MAESTRO("acceptance_mark/maestro.png", "MC");

    private static final String IMAGES_PATH = "/images/mobile/";
    private String mPath;
    private String mIssuerName;

    private CardIssuer(String path, String issuerName) {
        mPath = path;
        mIssuerName = issuerName;
    }

    public String getName() {
        return mIssuerName;
    }

    public String getPath() {
        return IMAGES_PATH + mPath;
    }
}
