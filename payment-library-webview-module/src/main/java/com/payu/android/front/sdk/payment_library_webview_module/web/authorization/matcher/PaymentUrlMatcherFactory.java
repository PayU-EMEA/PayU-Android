package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher;

import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.events.PaymentAuthorization;


public class PaymentUrlMatcherFactory {

    private static final String DEFAULT_CONTINUE_URL = "https://not.existing.url";

    public PaymentUrlMatcher getUrlMatcher(AuthorizationDetails authorizationDetails) {
        return new AnyHostPathAndParametersUrlMatcher(new HostPathAndParametersUrlMatcher(getMatcherProvider(authorizationDetails)),
                new HostPathAndParametersUrlMatcher(getOpu21MatcherProvider(authorizationDetails)),
                new HostPathAndParametersUrlMatcher(new MobileMatcherProvider()));
    }

    private AuthorizationRedirectUriMatcherProvider getMatcherProvider(AuthorizationDetails authorizationDetails) {
        PaymentAuthorization authorizationType = authorizationDetails.getAuthorizationType();

        switch (authorizationType) {

            case PEX: {
                return new PexUrlMatcherProviderOpu21(authorizationDetails.getContinueUrl().or(DEFAULT_CONTINUE_URL));
            }
            case _3DS: {
                return new ThreeDSUrlMatcherProvider(authorizationDetails.getContinueUrl().or(DEFAULT_CONTINUE_URL));
            }
            case PAY_BY_LINK: {
                return getPayByLinkMatcherProvider(authorizationDetails);
            }
            default:
                throw new IllegalArgumentException(String.format( //
                        "%s is not supported. Use PEX or _3DS or PAY_BY_LINK", authorizationType.name()));
        }
    }

    private AuthorizationRedirectUriMatcherProvider getOpu21MatcherProvider(AuthorizationDetails authorizationDetails) {
        PaymentAuthorization authorizationType = authorizationDetails.getAuthorizationType();

        switch (authorizationType) {

            case PEX: {
                return new PexUrlMatcherProviderOpu21(authorizationDetails.getContinueUrl().or(DEFAULT_CONTINUE_URL));
            }
            case _3DS: {
                return new ThreeDSUrlMatcherProviderOpu21(authorizationDetails.getContinueUrl().or(DEFAULT_CONTINUE_URL));
            }
            case PAY_BY_LINK: {
                return getPayByLinkMatcherProvider(authorizationDetails);
            }
            default:
                throw new IllegalArgumentException(String.format( //
                        "%s is not supported. Use PEX or _3DS or PAY_BY_LINK", authorizationType.name()));
        }
    }


    private AuthorizationRedirectUriMatcherProvider getPayByLinkMatcherProvider(AuthorizationDetails authorizationDetails) {
        return new PayByLinkUrlMatcherProvider(authorizationDetails.getContinueUrl().or(DEFAULT_CONTINUE_URL));
    }

}
