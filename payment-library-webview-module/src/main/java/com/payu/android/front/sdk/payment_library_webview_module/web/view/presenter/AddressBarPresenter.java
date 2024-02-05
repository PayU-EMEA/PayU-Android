package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.payu.android.front.sdk.payment_library_webview_module.R;
import com.payu.android.front.sdk.payment_library_webview_module.web.formatter.SslAddressFormatter;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.AddressView;

import static com.google.common.base.Strings.nullToEmpty;

public class AddressBarPresenter extends AddressBarAction<AddressView> {
    private SslAddressFormatter mSslAddressFormatter;
    private String mAddress;
    private boolean mIsAddressVerified = true;

    public AddressBarPresenter(SslAddressFormatter sslAddressFormatter) {
        this.mSslAddressFormatter = sslAddressFormatter;
    }

    @DrawableRes
    private int getPadlockImageResource(boolean isVerified) {
        return isVerified
               ? R.drawable.ic_lock_safe
               : R.drawable.ic_lock_unsafe;
    }

    @Override
    public void setAddress(@NonNull String url) {
        if (!url.equals(mAddress)) {
            mAddress = url;
            mIsAddressVerified = mSslAddressFormatter.isHttps(url);
            updateView();
        }
    }

    private void updateView() {
        if (view != null) {
            view.setPadlockIcon(getIconResource());
            view.setFormattedAddress(getFormattedAddress());
        }
    }

    @Override
    public void setIsAddressVerified(boolean isVerified) {
        mIsAddressVerified = isVerified;
        view.setPadlockIcon(getIconResource());
    }

    @NonNull
    private String getFormattedAddress() {
        return mSslAddressFormatter.getFormattedAddress(nullToEmpty(mAddress), mIsAddressVerified).toString();
    }

    @DrawableRes
    private int getIconResource() {
        return getPadlockImageResource(mIsAddressVerified);
    }
}
