package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_webview_module.R;

public class WebPaymentView extends RelativeLayout implements WebPayment {
    private AddressBarView addressBarView;
    private WebView webView;
    private ProgressBar progressBar;

    public WebPaymentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WebPaymentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void configureWebViewSettings(WebSettings webSettings) {
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSaveFormData(false);
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_web_payment, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.addressBarView = findViewById(R.id.address_bar_view);
        this.webView = findViewById(R.id.payment_webView);
        this.progressBar = findViewById(R.id.payment_progressBar);
        configureWebViewSettings(webView.getSettings());
        applyStyles();
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public AddressBarView getAddressBarView() {
        return addressBarView;
    }


    private void applyStyles() {
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());
        addressBarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.payu_styles_color_web_view_background));
        //hardcoded colors when dark mode is triggered
        webView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.payu_styles_color_web_view_background));
        progressBar.setDrawingCacheBackgroundColor(applicationStyleInfo.getAccentColor());
        progressBar.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
    }

}
