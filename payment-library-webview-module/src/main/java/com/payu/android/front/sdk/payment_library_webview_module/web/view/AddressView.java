package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.AddressBarPresenter;


public interface AddressView {
    void setAddress(@NonNull String url);

    void setAddressVerified(boolean isVerified);

    void setPresenter(@NonNull AddressBarPresenter presenter);

    void setPadlockIcon(@DrawableRes int iconPath);

    void setFormattedAddress(@NonNull String formattedAddress);
}
