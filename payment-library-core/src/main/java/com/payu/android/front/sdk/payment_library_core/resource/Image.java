package com.payu.android.front.sdk.payment_library_core.resource;

public enum Image {

    PAYU_LOGO("payu_logo.png"),
    BRAND_LOGO_PATH("widget/android_logo_payuwhite_widget.png"),
    PAYU_APPLICATION_ICON("android_payu_applogo.png"),
    BUYER_PROTECTION_PROGRAM_UMBRELLA_ICON("ic_pok_umbrella.png"),
    BUYER_PROTECTION_PROGRAM_LOCK_ICON("ic_pok_lock.png"),
    CVV_FIELD_ERROR_ICON("ic_cardcvv_error.png"),
    CVV_FIELD_ICON("ic_cardcvv_input.png"),
    VENDORS_IMAGE("vendors.png"),
    CVV_FIELD_MARKER_ICON("ic_check.png"),
    IC_CHECK_TOOLBAR("ic_navbar_check.png"),
    ACTION_BAR_ICON("android_logo_payu_navbar.png"),
    USER_ICON("ic_big_user.png"),
    LOGIN_BUTTON_ICON("ic_user_button_cta.png"),
    ADD_CARD_BUTTON_ICON("ic_addcard_button.png"),
    ADD_BANK_BUTON_ICON("ic_addbank.png"),
    PAYMENT_METHOD_ICON_PLACEHOLDER("payment_method_placeholder.png"),

    BIG_WALLET("ic_big_wallet.png"),
    REMOVE_ICON("ic_remove.png"),
    PADLOCK_SAFE("ic_lock_safe.png"),
    PADLOCK_UNSAFE("ic_lock_unsafe.png");

    private static final String IMAGES_PATH = "/images/mobile/";
    private String mImageName;

    Image(String imageName) {
        mImageName = imageName;
    }

    public String getPath() {
        return IMAGES_PATH + mImageName;
    }
}
