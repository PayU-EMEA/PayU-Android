package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.service;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.view.BlikAmbiguityActivity;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import static com.google.common.base.Preconditions.checkArgument;

public class BlikAmbiguityService {


    private BlikAmbiguityService() {
    }

    /**
     * Key for obtaining Blik object as {@linkplain PaymentMethod}
     * After creating new order with new BLIK as payment method {@see <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/order-api-endpoint/create-a-new-order">documentation</a}
     * there is a possibility that the user could associate more than one BLIK so
     */
    public static final String INTENT_SELECTED_BLIK_EXTRA = "INTENT_SELECTED_BLIK_EXTRA";
    /**
     * Request code for obtaining information about Selected Blik
     * {@linkplain PaymentMethod}
     * from method {@linkplain android.app.Activity#onActivityResult(int, int, Intent)}
     */

    public static final int REQUEST_CODE = 502;

    /**
     * This method will open new activity with displayed saved by user Blicks and let him select one of it to end the payment process
     * <p>
     * After creating new order with new BLIK as payment method
     * {@see <a href="https://payu21.docs.apiary.io/#reference/api-endpoints/order-api-endpoint/create-a-new-order">documentation</a}
     * there is a possibility that the user could associate more than one BLIK so there is a need to select one BLIK to end the payment process
     * to populate this {@linkplain Activity} with Blik pass {@linkplain PaymentMethod} to your implementation of {@linkplain PaymentMethodActions}
     * you need to override method {@linkplain PaymentMethodActions#provideBlikPaymentMethods(PaymentMethodsCallback)}  }
     *
     * @param activity
     */
    public static void selectAmbiguityBlik(@NonNull Activity activity) {
        checkArgument(activity != null, "Activity  should be provided");
        BlikAmbiguityActivity.startForResult(activity, REQUEST_CODE);
    }

    /**
     * Method used for unboxing payment result {@linkplain PaymentMethod} from intent returned in {@linkplain Activity#onActivityResult}.
     * Only intent from result with request code {@linkplain #REQUEST_CODE} should be passed to this method.
     *
     * @param result - Intent received in {@linkplain Activity#onActivityResult}
     * @return - Selected blik
     */
    @Nullable
    public static PaymentMethod extractSelectedBlikResult(@NonNull Intent result) {
        return result.getParcelableExtra(INTENT_SELECTED_BLIK_EXTRA);
    }


}