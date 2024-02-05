package com.payu.android.front.sdk.payment_library_webview_module.web.url.loader;

import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.WebPaymentPresenter;

public class PostUrlLoader implements UrlLoader {

    @Override
    public void load(WebPaymentPresenter webPaymentAuthorizationView, AuthorizationDetails authorizationDetails) {
        webPaymentAuthorizationView.loadUrl(authorizationDetails.getLink().orNull(),
                authorizationDetails.getPostParameterMap().orNull());
    }
}
