package com.payu.android.front.sdk.payment_library_webview_module.web.url.loader;

import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.WebPaymentPresenter;

public interface UrlLoader {
    void load(WebPaymentPresenter webPaymentAuthorizationView, AuthorizationDetails authorizationDetails);
}
