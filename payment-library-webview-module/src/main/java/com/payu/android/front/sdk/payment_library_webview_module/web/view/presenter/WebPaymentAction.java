package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;

import android.os.Bundle;
import android.webkit.WebBackForwardList;

import com.payu.android.front.sdk.payment_library_core_android.base.BasePresenter;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.OnAuthorizationFinishedListener;

import java.util.Map;

public abstract class WebPaymentAction extends BasePresenter {
    public abstract void clearCache();

    public abstract boolean isWebBackStackEmpty();

    public abstract void loadUrl(String url, Map<String, String> parameters);

    public abstract void loadUrl(String url);

    public abstract WebBackForwardList restoreState(Bundle inState);

    public abstract WebBackForwardList saveState(Bundle outState);

    public abstract void setOnAuthorizationFinishedListener(OnAuthorizationFinishedListener onAuthorizationFinishedListener);
}
