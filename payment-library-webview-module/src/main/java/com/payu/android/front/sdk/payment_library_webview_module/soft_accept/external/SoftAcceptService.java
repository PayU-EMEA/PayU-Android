package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.view.SoftAcceptDialogDelegate;

public class SoftAcceptService {
    public static final String KEY_REQUEST_BUNDLE = "SoftAcceptService.KeyRequestBundle";
    public static final String KEY_SOFT_ACCEPT_RESPONSE_DETAIL = "SoftAcceptService.KeySoftAcceptResponseDetail";
    private final SoftAcceptDialogDelegate softAcceptDialogDelegate;

    private SoftAcceptService(
            @NonNull SoftAcceptDialogDelegate softAcceptDialogDelegate) {
        this.softAcceptDialogDelegate = softAcceptDialogDelegate;
    }

    /**
     * @param dialogBodyMessage    -message on dialog that could be seen by end user - if not provided then user will see default text
     * @param fragmentManager      -dialog will be attached using fragmentManager
     * @param authorizationDetails -start data for soft accept {@linkplain SoftAcceptTransactionData} - right now it consist of redirect link
     * @param isCancelable         - flag that indicate if user can cancel a dialog pressing outside of it. In this case dialog will return {@link SoftAcceptTransactionStatus#AUTHENTICATION_CANCELED}
     *                             <p>
     *                             To retrieve resonse {@linkplain SoftAcceptTransactionStatus}: there is a need to call getParentFragmentManager().setFragmentResultListener
     *                             Using Key: {@link SoftAcceptService#KEY_REQUEST_BUNDLE}
     *                             Object is stored {@link SoftAcceptService#KEY_SOFT_ACCEPT_RESPONSE_DETAIL}
     *                             Sample in Kotlin:
     *                             <p>
     *                             supportFragmentManager.setFragmentResultListener(SoftAcceptService.KEY_REQUEST_BUNDLE, this, { _, bundle ->
     *                             val result = bundle.getParcelable<SoftAcceptTransactionDetail>(SoftAcceptService.KEY_SOFT_ACCEPT_RESPONSE_DETAIL)
     *                             // Do something with the result
     *                             })
     */
    public SoftAcceptService(
            @Nullable String dialogBodyMessage,
            @NonNull FragmentManager fragmentManager,
            @NonNull AuthorizationDetails authorizationDetails,
            boolean isCancelable) {
        this(new SoftAcceptDialogDelegate(dialogBodyMessage, fragmentManager, authorizationDetails, isCancelable, 0));
    }

    /**
     * @param dialogBodyMessage      -message on dialog that could be seen by end user - if not provided then user will see default text
     * @param fragmentManager        -dialog will be attached using fragmentManager
     * @param authorizationDetails   -start data for soft accept {@linkplain SoftAcceptTransactionData} - right now it consist of redirect link
     * @param isCancelable           - flag that indicate if user can cancel a dialog pressing outside of it. In this case dialog will return {@link SoftAcceptTransactionStatus#AUTHENTICATION_CANCELED}
     * @param displayAtLeastInMillis - delay for closing a dialog (is triggered after action is completed or when user will cancel displaying a dialog)
     *                               <p>
     *                               To retrieve resonse {@linkplain SoftAcceptTransactionStatus}: there is a need to call getParentFragmentManager().setFragmentResultListener
     *                               Using Key: {@link SoftAcceptService#KEY_REQUEST_BUNDLE}
     *                               Object is stored {@link SoftAcceptService#KEY_SOFT_ACCEPT_RESPONSE_DETAIL}
     *                               Sample in Kotlin:
     *                               <p>
     *                               supportFragmentManager.setFragmentResultListener(SoftAcceptService.KEY_REQUEST_BUNDLE, this, { _, bundle ->
     *                               val result = bundle.getParcelable<SoftAcceptTransactionDetail>(SoftAcceptService.KEY_SOFT_ACCEPT_RESPONSE_DETAIL)
     *                               // Do something with the result
     *                               })
     */
    public SoftAcceptService(
            @Nullable String dialogBodyMessage,
            @NonNull FragmentManager fragmentManager,
            @NonNull AuthorizationDetails authorizationDetails,
            boolean isCancelable,
            long displayAtLeastInMillis) {
        this(new SoftAcceptDialogDelegate(dialogBodyMessage, fragmentManager, authorizationDetails, isCancelable, displayAtLeastInMillis));
    }

    public SoftAcceptService(
            @Nullable String dialogBodyMessage,
            @NonNull FragmentManager fragmentManager,
            @NonNull AuthorizationDetails authorizationDetails,
            boolean isCancelable,
            long displayAtLeastInMillis,
            long timeout) {
        this(new SoftAcceptDialogDelegate(dialogBodyMessage, fragmentManager, authorizationDetails, isCancelable, displayAtLeastInMillis, timeout));
    }

    /**
     * @param childView            -custom layout, that should be displayed in default layout place, only simple layout are supported
     * @param fragmentManager      -dialog will be attached using fragmentManager
     * @param authorizationDetails -start data for soft accept {@linkplain SoftAcceptTransactionData} - right now it consist of redirect link
     * @param isCancelable         - flag that indicate if user can cancel a dialog pressing outside of it. In this case dialog will return {@link SoftAcceptTransactionStatus#AUTHENTICATION_CANCELED}
     *                             <p>
     *                             To retrieve resonse {@linkplain SoftAcceptTransactionStatus}: there is a need to call   getParentFragmentManager().setFragmentResultListener
     *                             Using Key: {@link SoftAcceptService#KEY_REQUEST_BUNDLE}
     *                             Object is stored {@link SoftAcceptService#KEY_SOFT_ACCEPT_RESPONSE_DETAIL}
     *                             Sample in Kotlin:
     *                             <p>
     *                             supportFragmentManager.setFragmentResultListener(SoftAcceptService.KEY_REQUEST_BUNDLE, this, { _, bundle ->
     *                             val result = bundle.getParcelable<SoftAcceptTransactionDetail>(SoftAcceptService.KEY_SOFT_ACCEPT_RESPONSE_DETAIL)
     *                             // Do something with the result
     *                             })
     */
    public SoftAcceptService(@LayoutRes int childView,
                             @NonNull FragmentManager fragmentManager,
                             @NonNull AuthorizationDetails authorizationDetails,
                             boolean isCancelable) {
        this(new SoftAcceptDialogDelegate(childView, fragmentManager, authorizationDetails, isCancelable, 0));
    }

    /**
     * @param childView              -custom layout, that should be displayed in default layout place, only simple layout are supported
     * @param fragmentManager        -dialog will be attached using fragmentManager
     * @param authorizationDetails   -start data for soft accept {@linkplain SoftAcceptTransactionData} - right now it consist of redirect link
     * @param isCancelable           - flag that indicate if user can cancel a dialog pressing outside of it. In this case dialog will return {@link SoftAcceptTransactionStatus#AUTHENTICATION_CANCELED}
     * @param displayAtLeastInMillis - delay for closing a dialog (is triggered after action is completed or when user will cancel displaying a dialog)
     *                               <p>
     *                               To retrieve resonse {@linkplain SoftAcceptTransactionStatus}: there is a need to call getParentFragmentManager().setFragmentResultListener
     *                               Using Key: {@link SoftAcceptService#KEY_REQUEST_BUNDLE}
     *                               Object is stored {@link SoftAcceptService#KEY_SOFT_ACCEPT_RESPONSE_DETAIL}
     *                               Sample in Kotlin:
     *                               <p>
     *                               supportFragmentManager.setFragmentResultListener(SoftAcceptService.KEY_REQUEST_BUNDLE, this, { _, bundle ->
     *                               val result = bundle.getParcelable<SoftAcceptTransactionDetail>(SoftAcceptService.KEY_SOFT_ACCEPT_RESPONSE_DETAIL)
     *                               // Do something with the result
     *                               })
     */
    public SoftAcceptService(@LayoutRes int childView,
                             @NonNull FragmentManager fragmentManager,
                             @NonNull AuthorizationDetails authorizationDetails,
                             boolean isCancelable,
                             long displayAtLeastInMillis) {
        this(new SoftAcceptDialogDelegate(childView, fragmentManager, authorizationDetails, isCancelable, displayAtLeastInMillis));
    }

    public void processSoftAccept() {
        softAcceptDialogDelegate.show();
    }

    public void dismissSoftAccept() {
        softAcceptDialogDelegate.dismiss();
    }
}
