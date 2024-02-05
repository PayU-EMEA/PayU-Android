package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.view;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus;

public class SoftAcceptDialogDelegate {
    private final static int NO_VIEW = -1;
    private final FragmentManager fragmentManager;
    private final Translation translation;
    private final AuthorizationDetails authorizationDetails;
    private final String dialogBodyMessage;
    private final int childView;
    private final boolean passCustomView;
    private final boolean isCancelable;
    private final long displayAtLeastInMillis;
    private final long timeout;

    public SoftAcceptDialogDelegate(@Nullable String dialogBodyMessage,
                                    @NonNull FragmentManager fragmentManager,
                                    @NonNull AuthorizationDetails authorizationDetails,
                                    boolean isCancelable,
                                    long displayAtLeastInMillis) {
        this(dialogBodyMessage, NO_VIEW, false, fragmentManager, TranslationFactory.getInstance(),
                authorizationDetails, isCancelable, displayAtLeastInMillis, -1);
    }

    public SoftAcceptDialogDelegate(@Nullable String dialogBodyMessage,
                                    @NonNull FragmentManager fragmentManager,
                                    @NonNull AuthorizationDetails authorizationDetails,
                                    boolean isCancelable,
                                    long displayAtLeastInMillis,
                                    long timeout
    ) {
        this(dialogBodyMessage, NO_VIEW, false, fragmentManager, TranslationFactory.getInstance(),
                authorizationDetails, isCancelable, displayAtLeastInMillis, timeout);
    }

    public SoftAcceptDialogDelegate(@LayoutRes int childView,
                                    @NonNull FragmentManager fragmentManager,
                                    @NonNull AuthorizationDetails authorizationDetails,
                                    boolean isCancelable,
                                    long displayAtLeastInMillis,
                                    long timeout) {
        this(null, childView, true, fragmentManager, TranslationFactory.getInstance(),
                authorizationDetails, isCancelable, displayAtLeastInMillis, timeout);
    }

    public SoftAcceptDialogDelegate(@LayoutRes int childView,
                                    @NonNull FragmentManager fragmentManager,
                                    @NonNull AuthorizationDetails authorizationDetails,
                                    boolean isCancelable,
                                    long displayAtLeastInMillis) {
        this(null, childView, true, fragmentManager, TranslationFactory.getInstance(),
                authorizationDetails, isCancelable, displayAtLeastInMillis, -1);
    }

    private SoftAcceptDialogDelegate(@Nullable String dialogBodyMessage,
                                     @LayoutRes int childView,
                                     Boolean passCustomView,
                                     @NonNull FragmentManager fragmentManager,
                                     @NonNull Translation translation,
                                     @NonNull AuthorizationDetails authorizationDetails,
                                     Boolean isCancelable,
                                     long displayAtLeastInMillis,
                                     long timeout) {
        this.dialogBodyMessage = dialogBodyMessage;
        this.fragmentManager = fragmentManager;
        this.translation = translation;
        this.authorizationDetails = authorizationDetails;
        this.childView = childView;
        this.passCustomView = passCustomView;
        this.isCancelable = isCancelable;
        this.displayAtLeastInMillis = displayAtLeastInMillis;
        this.timeout = timeout;

    }

    public void show() {
        SoftAcceptDialog softAcceptDialog = SoftAcceptDialog.newInstance(retrieveDialogText(), authorizationDetails, passCustomView, childView, isCancelable, displayAtLeastInMillis, timeout);
        if (fragmentManager != null) {
            softAcceptDialog.show(fragmentManager, SoftAcceptDialog.TAG);
            softAcceptDialog.setFragmentManager(fragmentManager);
        }
    }

    public void dismiss() {
        if (fragmentManager != null) {
            SoftAcceptDialog softAcceptDialog = (SoftAcceptDialog) fragmentManager
                    .findFragmentByTag(SoftAcceptDialog.TAG);

            if (softAcceptDialog != null) {
                softAcceptDialog.dismissWithCancelResult(SoftAcceptTransactionStatus.DISMISS_FRAGMENT_MANAGER);
                softAcceptDialog = null;
            }
        }
    }

    private String retrieveDialogText() {
        return (dialogBodyMessage == null || dialogBodyMessage.isEmpty()) ?
                translation.translate(TranslationKey.SOFT_ACCEPT_DIALOG_TITLE) :
                dialogBodyMessage;

    }
}
