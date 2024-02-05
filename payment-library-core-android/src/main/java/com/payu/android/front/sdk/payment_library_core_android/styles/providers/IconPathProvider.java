package com.payu.android.front.sdk.payment_library_core_android.styles.providers;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationProvider;

public class IconPathProvider {
    private StyleClassConfigurationProvider styleClassConfigurationProvider;

    public IconPathProvider(@NonNull StyleClassConfigurationProvider styleClassConfigurationProvider) {
        this.styleClassConfigurationProvider = styleClassConfigurationProvider;
    }

    @DrawableRes
    public int getCardIconPath() {
        return styleClassConfigurationProvider.getStyleFromConfiguration().pathIconAddNewCard();
    }

    @DrawableRes
    public int getBankIconPath() {
        return styleClassConfigurationProvider.getStyleFromConfiguration().pathIconPBLPayment();
    }
}
