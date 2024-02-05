package com.payu.android.front.sdk.payment_library_webview_module.web.authorization;


import java.util.Map;

public interface PostDataEncoder {
    byte[] encode(Map<String, String> parameters);

}
