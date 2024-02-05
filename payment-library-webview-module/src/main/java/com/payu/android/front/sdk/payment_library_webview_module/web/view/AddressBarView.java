package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_webview_module.R;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.AddressBarPresenter;

import static com.google.common.base.Preconditions.checkArgument;

public class AddressBarView extends RelativeLayout implements AddressView {

    private TextView addressView;
    private ImageView padlockView;
    private AddressBarPresenter addressBarPresenter;

    public AddressBarView(Context context) {
        super(context);
        init();
    }

    public AddressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public AddressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.addressView = findViewById(R.id.address_view_url_text_view);
        this.padlockView = findViewById(R.id.address_bar_image_view);
        applyStyles();
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_address_bar, this);
    }

    public void setAddress(@NonNull String url) {
        checkArgument(addressBarPresenter != null, "Presenter should be set");
        addressBarPresenter.setAddress(url);
    }

    @Override
    public void setAddressVerified(boolean isVerified) {
        checkArgument(addressBarPresenter != null, "Presenter should be set");
        addressBarPresenter.setIsAddressVerified(isVerified);
    }

    private void bindPadlockIcon(int imageResource) {
        padlockView.setImageResource(imageResource);
    }

    @Override
    public void setFormattedAddress(@NonNull String formattedAddress) {
        addressView.setText(formattedAddress);
    }

    @Override
    public void setPadlockIcon(@DrawableRes int icon) {
        bindPadlockIcon(icon);
    }

    @Override
    public void setPresenter(@NonNull AddressBarPresenter presenter) {
        addressBarPresenter = presenter;
        addressBarPresenter.takeView(this);
    }

    public void clearPresenter() {
        this.addressBarPresenter = null;
    }

    @NonNull
    private TextViewStyle createStyleFromInfo(TextStyleInfo styleInfo) {
        return new TextViewStyle(styleInfo, new FontProvider(getContext()));
    }

    private void applyStyles() {
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());
        createStyleFromInfo(applicationStyleInfo.getTextStyleDescription()).applyTo(addressView);
        int NO_PADDING = 0;
        padlockView.setPadding(6, NO_PADDING, NO_PADDING, NO_PADDING);
    }

}