package com.payu.android.front.sdk.payment_library_webview_module.web.service;

import static com.google.common.base.Preconditions.checkArgument;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProviderHolder;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentStatus;
import com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.WebPaymentActivity;

/**
 * Entry Point for WebPaymentModule
 */
public class WebPaymentService {
    /**
     * Request code for obtaining information about Payment Status
     * {@linkplain com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails}
     * from method {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}
     */
    public static final int REQUEST_CODE = 501;
    /**
     * Key for obtaining parcelable data
     * {@linkplain com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails}
     * from method {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}
     * when Payment process will end
     */
    public static final String INTENT_WEB_PAYMENT_EXTRA = "INTENT_WEB_PAYMENT_EXTRA";

    private WebPaymentService() {
    }

    /**
     * @param activity                       - context necessary for opening internal web Browser {@linkplain AuthorizationDetails}
     * @param authorizationDetails           - basic data that should be processed by Internal Browser
     * @param shouldCreateCancelOptionInMenu - should menu have additional Cancel option of WebPayments
     *                                       <p>
     *                                       Response will be provided in {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}
     *                                       Where requestCode is {@linkplain WebPaymentService#REQUEST_CODE}
     *                                       Response is extras {@linkplain com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails}
     *                                       under key {@linkplain WebPaymentService#INTENT_WEB_PAYMENT_EXTRA}
     */
    public static void pay(@NonNull Activity activity, @NonNull AuthorizationDetails authorizationDetails, boolean shouldCreateCancelOptionInMenu) {
        checkArgument(activity != null, "Activity  should be provided");
        checkArgument(authorizationDetails != null, "AuthorizationDetails should be provided");
        xmlVerifier(activity);
        WebPaymentActivity.start(activity, authorizationDetails, REQUEST_CODE, shouldCreateCancelOptionInMenu);
    }


    /**
     * @param activity             - context necessary for opening internal web Browser {@linkplain AuthorizationDetails}
     * @param authorizationDetails - basic data that should be processed by Internal Browser
     *                             <p>
     *                             Response will be provided in {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}
     *                             Where requestCode is {@linkplain WebPaymentService#REQUEST_CODE}
     *                             Response is extras {@linkplain com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails}
     *                             under key {@linkplain WebPaymentService#INTENT_WEB_PAYMENT_EXTRA}
     */
    public static void pay(@NonNull Activity activity, @NonNull AuthorizationDetails authorizationDetails) {
        pay(activity,authorizationDetails, false);
    }

    /**
     * Method used for unboxing payment result {@linkplain PaymentDetails} from intent returned in {@linkplain Activity#onActivityResult}.
     * Only intent from result with request code {@linkplain #REQUEST_CODE} should be passed to this method.
     *
     * @param result - Intent received in {@linkplain Activity#onActivityResult}
     * @return - PaymentDetails with information about {@linkplain WebPaymentStatus}
     */
    @Nullable
    public static PaymentDetails extractPaymentResult(@NonNull Intent result) {
        return result.getParcelableExtra(WebPaymentService.INTENT_WEB_PAYMENT_EXTRA);
    }

    private static void xmlVerifier(@NonNull Context activityContext) {
        ConfigurationDataProvider configurationDataProvider = ConfigurationDataProviderHolder.getInstance(activityContext);
        //xml verify
        configurationDataProvider.getEnvironment();
        configurationDataProvider.getLocale();

    }
}
