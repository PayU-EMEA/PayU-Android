package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.List;

public class AnyHostPathAndParametersUrlMatcher implements PaymentUrlMatcher {

    private final List<HostPathAndParametersUrlMatcher> mUrlMatcherList;

    public AnyHostPathAndParametersUrlMatcher(HostPathAndParametersUrlMatcher... matchers) {
        mUrlMatcherList = Arrays.asList(matchers);
    }

    @Override
    public boolean isPaymentCvvRequiredUrl(final String url) {
        return Iterables.any(mUrlMatcherList, new Predicate<HostPathAndParametersUrlMatcher>() {
            @Override
            public boolean apply(HostPathAndParametersUrlMatcher input) {
                return input.isPaymentCvvRequiredUrl(url);
            }
        });
    }

    @Override
    public boolean isPaymentErrorUrl(final String url) {
        return Iterables.any(mUrlMatcherList, new Predicate<HostPathAndParametersUrlMatcher>() {
            @Override
            public boolean apply(HostPathAndParametersUrlMatcher input) {
                return input.isPaymentErrorUrl(url);
            }
        });
    }

    @Override
    public boolean isPaymentSuccessUrl(final String url) {
        return Iterables.any(mUrlMatcherList, new Predicate<HostPathAndParametersUrlMatcher>() {
            @Override
            public boolean apply(HostPathAndParametersUrlMatcher input) {
                return input.isPaymentSuccessUrl(url);
            }
        });
    }

    @Override
    public boolean isPaymentRequireOpenMobileApp(final String url) {
        return Iterables.any(mUrlMatcherList, new Predicate<HostPathAndParametersUrlMatcher>() {
            @Override
            public boolean apply(HostPathAndParametersUrlMatcher input) {
                return input.isPaymentRequireOpenMobileApp(url);
            }
        });
    }
}
