package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

public interface PaymentUrlMatcher {

    PaymentUrlMatcher EMPTY_MATCHER = new PaymentUrlMatcher() {

        @Override
        public boolean isPaymentCvvRequiredUrl(String url) {
            return false;
        }

        @Override
        public boolean isPaymentErrorUrl(String url) {
            return false;
        }

        @Override
        public boolean isPaymentSuccessUrl(String url) {
            return false;
        }

        @Override
        public boolean isPaymentRequireOpenMobileApp(String url) {
            return false;
        }
    };

    boolean isPaymentCvvRequiredUrl(String url);

    boolean isPaymentErrorUrl(String url);

    boolean isPaymentSuccessUrl(String url);

    boolean isPaymentRequireOpenMobileApp(String url);
}
