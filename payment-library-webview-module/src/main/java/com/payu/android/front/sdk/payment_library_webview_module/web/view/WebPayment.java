package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import android.webkit.WebView;
import android.widget.ProgressBar;

public interface WebPayment {

    ProgressBar getProgressBar();

    WebView getWebView();

    AddressBarView getAddressBarView();
}
