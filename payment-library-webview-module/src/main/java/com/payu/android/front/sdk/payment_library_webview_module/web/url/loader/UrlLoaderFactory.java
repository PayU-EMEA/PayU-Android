package com.payu.android.front.sdk.payment_library_webview_module.web.url.loader;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;

import java.util.Map;

public class UrlLoaderFactory {

    public UrlLoader createUrlLoader(AuthorizationDetails authorizationDetails) {
        Optional<Map<String, String>> postParameterMapOptional = authorizationDetails.getPostParameterMap();
        return postParameterMapOptional.isPresent() ? new PostUrlLoader() : new GetUrlLoader();
    }
}
