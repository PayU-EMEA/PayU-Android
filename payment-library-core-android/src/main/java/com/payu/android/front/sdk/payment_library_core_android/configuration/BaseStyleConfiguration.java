package com.payu.android.front.sdk.payment_library_core_android.configuration;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.payu.android.front.sdk.payment_library_core_android.R;

/**
 * Overlay with default values for {@linkplain StyleConfiguration} interface
 */
public abstract class BaseStyleConfiguration implements StyleConfiguration {
    @StyleRes
    public int payuStyle() {
        return R.style.Theme_PayU_Fronts;
    }

    @Override
    @DrawableRes
    public int payuLibraryIcon() {
        return NO_ICON;
    }

    @Override
    @DrawableRes
    public int pathIconAddNewCard() {
        return R.drawable.ic_addcard_button;
    }

    @Override
    @DrawableRes
    public int pathIconPBLPayment() {
        return R.drawable.ic_wire_transfer;
    }

}
