package com.payu.android.front.sdk.payment_library_webview_module.soft_accept.view;

import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus.DIALOG_DISMISS_OUTSIDE_USER_CANCELED;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus.DIALOG_DISMISS_USER_CANCELED;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus.TIMEOUT;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.utlis.RetrieveFromBundle.getBooleanFromBundle;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.utlis.RetrieveFromBundle.getLongFromBundle;
import static com.payu.android.front.sdk.payment_library_webview_module.soft_accept.utlis.RetrieveFromBundle.getParcelableFromBundle;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_webview_module.R;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.OnRedirectionCompleted;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.OnRetrieveStateListener;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.core.SoftAcceptStateListener;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptService;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionDetail;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.external.SoftAcceptTransactionStatus;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript.Configuration;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript.JSInterfaceHandler;
import com.payu.android.front.sdk.payment_library_webview_module.soft_accept.javascript.SoftAcceptConfiguration;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SoftAcceptDialog extends DialogFragment {
    //TODO: suggestion: create a Wrapper for this configuration
    public static final String KEY_BODY_MESSAGE = "SoftAcceptDialog.BodyMessage";
    public static final String KEY_TRANSACTION_DATA = "SoftAcceptDialog.TransactionData";
    public static final String KEY_CUSTOM_UI = "SoftAcceptDialog.CustomUI";
    public static final String KEY_IS_DIALOG_CANCELABLE = "SoftAcceptDialog.isCancelable";
    public static final String KEY_DISPLAY_AT_LEAST_IN_MILLIS = "SoftAcceptDialog.displayAtLeastInMillis";
    public static final String KEY_STARTED_COUNTING = "SoftAcceptDialog.startedCounting";

    public static final String KEY_DIALOG_CANCEL = "SoftAcceptDialog.dialogCancel";
    public static final String KEY_DIALOG_CLOSE = "SoftAcceptDialog.dialogClose";
    public static final String KEY_DIALOG_DATA = "SoftAcceptDialog.dialogData";
    public static final String KEY_DIALOG_TIMEOUT = "SoftAcceptDialog.timeout";

    public static final String KEY_SEND_DATA_TO_FRAGMENT = "SoftAcceptDialog.sendDataToFragment";

    public static final String TAG = SoftAcceptDialog.class.toString();
    private final static String INTERFACE_HANDLER_NAME = "JSInterfaceHandler";
    private final static int TIMER_TICK_IN_MILLIS = 100;

    private LibraryStyleInfo libraryStyleInfo;
    private FontProvider fontProvider;
    private Configuration configuration;
    private AuthorizationDetails authorizationDetails;
    private OnRetrieveStateListener onRetrieveStateListener;
    private String bodyMessage;
    private WebView webView;
    private RestEnvironment restEnvironment;
    private int layoutId;
    private boolean useCustomLayout = false;
    private boolean isDialogCancelable = false;
    private long displayAtLeastInMillisForCancelable = 0;
    private long startedCountingInMillisForCancelable = 0;

    private long timeout = -1;
    private SoftAcceptTransactionDetail transactionDetail;
    private boolean cancelActionDialog;
    private boolean successActionDialog;
    private Disposable disposableSuccess;
    private Disposable disposableTimeoutSuccess;

    private FragmentManager fragmentManager;
    private Disposable disposableCancel;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private boolean sendDataToFragment = false;

    public static SoftAcceptDialog newInstance(@NonNull String bodyMessage, @NonNull AuthorizationDetails authorizationDetails,
                                               boolean addCustomLayout, @LayoutRes int layoutId, boolean isCancelable,
                                               long displayAtLeastInMillis, long timeout) {
        SoftAcceptDialog myFragment = new SoftAcceptDialog();

        Bundle args = new Bundle();
        args.putString(KEY_BODY_MESSAGE, bodyMessage);
        args.putParcelable(KEY_TRANSACTION_DATA, authorizationDetails);
        args.putBoolean(KEY_IS_DIALOG_CANCELABLE, isCancelable);
        args.putLong(KEY_DISPLAY_AT_LEAST_IN_MILLIS, displayAtLeastInMillis);
        args.putLong(KEY_DIALOG_TIMEOUT, timeout);
        if (addCustomLayout) {
            args.putInt(KEY_CUSTOM_UI, layoutId);
        }
        myFragment.setArguments(args);

        return myFragment;
    }

    public void setFragmentManager(@Nullable FragmentManager manager) {
        this.fragmentManager = manager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.bodyMessage = getArguments().getString(KEY_BODY_MESSAGE);
        this.authorizationDetails = getArguments().getParcelable(KEY_TRANSACTION_DATA);
        this.isDialogCancelable = getArguments().getBoolean(KEY_IS_DIALOG_CANCELABLE);
        this.displayAtLeastInMillisForCancelable = getLongFromBundle(savedInstanceState, getArguments(), KEY_DISPLAY_AT_LEAST_IN_MILLIS);
        this.timeout = getLongFromBundle(savedInstanceState, getArguments(), KEY_DIALOG_TIMEOUT);

        this.startedCountingInMillisForCancelable = getLongFromBundle(savedInstanceState, getArguments(), KEY_STARTED_COUNTING);
        this.sendDataToFragment = getBooleanFromBundle(savedInstanceState, getArguments(), KEY_SEND_DATA_TO_FRAGMENT);
        if (getArguments().containsKey(KEY_CUSTOM_UI)) {
            this.useCustomLayout = true;
            this.layoutId = getArguments().getInt(KEY_CUSTOM_UI);
        }

        this.cancelActionDialog = getBooleanFromBundle(savedInstanceState, getArguments(), KEY_DIALOG_CANCEL);
        this.successActionDialog = getBooleanFromBundle(savedInstanceState, getArguments(), KEY_DIALOG_CLOSE);
        this.transactionDetail = getParcelableFromBundle(savedInstanceState, getArguments(), KEY_DIALOG_DATA);
        this.libraryStyleInfo = LibraryStyleProvider.fromContext(requireActivity());
        this.fontProvider = new FontProvider(requireActivity().getApplicationContext());
        this.configuration = new SoftAcceptConfiguration();
        this.restEnvironment = ConfigurationEnvironmentProvider.provideEnvironment(requireActivity());

        this.onRetrieveStateListener = new SoftAcceptStateListener(localRedirectAction, authorizationDetails);
    }

    /**
     * Manually Handling onClick action outside of Dialog.
     * This lets add delay when user press outside of dialog
     */
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        getDialog().getWindow().getDecorView().setOnTouchListener((v, event) -> {
            Rect dialogRect = new Rect();
            getDialog().getWindow().getDecorView().getHitRect(dialogRect);

            if (!dialogRect.contains((int) event.getX(), (int) event.getY())) {
                if (isCancelable()) {
                    dismissWithCancelResult(DIALOG_DISMISS_OUTSIDE_USER_CANCELED);
                } else {
                    delayCancelDialog();
                }
                return true;
            }
            return false;
        });

        runDialogTimeout();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (cancelActionDialog) {
            delayCancelDialog();
        }
        if (successActionDialog && transactionDetail != null) {
            delaySoftAcceptDetail(transactionDetail);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (cancelActionDialog && disposableCancel != null && !disposableCancel.isDisposed()) {
            disposableCancel.dispose();
        }
        if (successActionDialog && disposableSuccess != null && !disposableSuccess.isDisposed()) {
            disposableSuccess.dispose();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(KEY_DIALOG_CANCEL, cancelActionDialog);
        outState.putBoolean(KEY_DIALOG_CLOSE, successActionDialog);
        outState.putParcelable(KEY_DIALOG_DATA, transactionDetail);
        outState.putLong(KEY_DISPLAY_AT_LEAST_IN_MILLIS, displayAtLeastInMillisForCancelable);
        outState.putBoolean(KEY_SEND_DATA_TO_FRAGMENT, sendDataToFragment);

        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), 0);
        // set the custom layout

        builder.setView(setUpView());

        builder.setCancelable(shouldDialogBeCancelableAtStart());
        setCancelable(shouldDialogBeCancelableAtStart());
        if (!isDialogCancelable) {
            builder.setOnKeyListener((dialog, keyCode, event) -> isBackButtonPressed(keyCode, event));
        } else {
            builder.setOnKeyListener((dialog, keyCode, event) -> {
                if (isBackButtonPressed(keyCode, event)) {
                    delayCancelDialog();
                    return true;
                }
                return false;
            });
        }
        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (webView != null) {
            webView.removeJavascriptInterface(INTERFACE_HANDLER_NAME);
            webView.destroy();
            webView = null;
        }
        disposables.dispose();
        fragmentManager = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
    }

    public void dismissWithCancelResult(SoftAcceptTransactionStatus status) {
        SoftAcceptTransactionDetail detail = new SoftAcceptTransactionDetail(status, authorizationDetails);
        sendDataToFragmentResult(detail);
        dismiss();
    }

    private boolean isBackButtonPressed(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP &&
                !event.isCanceled();
    }

    private View setUpView() {
        final View customLayout = requireActivity().getLayoutInflater()
                .inflate(R.layout.payu_view_soft_accept, null);

        if (useCustomLayout) {
            setUpExternalView(customLayout);
        } else {
            setUpLocalView(customLayout);
        }
        setUpWebView(customLayout);
        return customLayout;
    }

    private void setUpLocalView(View parentView) {
        TextView textView = parentView.findViewById(R.id.payu_softAccept_descriptionText);

        parentView.setBackgroundColor(libraryStyleInfo.getBackgroundColor());
        textView.setText(bodyMessage);
        new TextViewStyle(libraryStyleInfo.getTextStyleText(), fontProvider).applyTo(textView);
        ProgressBar progressBar = parentView.findViewById(R.id.payu_softAccept_progressBar);
        progressBar.setDrawingCacheBackgroundColor(libraryStyleInfo.getAccentColor());
    }

    private void setUpExternalView(View rootView) {
        View viewToBeRemoved = rootView.findViewById(R.id.payu_softAccept_dialogContainer);
        ((ViewGroup) viewToBeRemoved).removeAllViews();
        final View externalLayout = requireActivity().getLayoutInflater()
                .inflate(layoutId, null);

        ((ViewGroup) viewToBeRemoved).addView(externalLayout);

    }

    private void setUpWebView(View parentView) {
        webView = parentView.findViewById(R.id.payu_softAccept_webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JSInterfaceHandler(onRetrieveStateListener), INTERFACE_HANDLER_NAME);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(configuration.provideJsFunctionBridge(restEnvironment.silentAcceptEnvironment()));
            }
        });
        webView.loadData(configuration.provideIframeHandler(authorizationDetails.getLink().get() + configuration.additionalParameters()),
                "text/html", "utf-8");
    }

    private final OnRedirectionCompleted localRedirectAction = new OnRedirectionCompleted() {
        @Override
        public void onDetailReceived(@NonNull final SoftAcceptTransactionDetail detail) {
            transactionDetail = detail;
            delaySoftAcceptDetail(transactionDetail);
        }
    };

    private synchronized void sendDataToFragmentResult(SoftAcceptTransactionDetail detail) {
        Bundle result = new Bundle();
        result.putParcelable(SoftAcceptService.KEY_SOFT_ACCEPT_RESPONSE_DETAIL, detail);
        if (fragmentManager != null && !sendDataToFragment) {
            fragmentManager.setFragmentResult(SoftAcceptService.KEY_REQUEST_BUNDLE, result);
            sendDataToFragment = !sendDataToFragment;
        }
    }

    private synchronized void delayCancelDialog() {
        disposableCancel = Single.timer(startedCountingInMillisForCancelable, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ignore -> changeDialogOnClickableState());
        disposables.add(disposableCancel);
        cancelActionDialog = true;
    }

    private synchronized void changeDialogOnClickableState() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(true);
            setCancelable(true);
            dialog.setOnKeyListener((dialog1, keyCode, event) -> {
                if (isBackButtonPressed(keyCode, event)) {
                    dismissWithCancelResult(DIALOG_DISMISS_USER_CANCELED);
                    return true;
                }
                return false;
            });
        }
    }

    private synchronized void runDialogTimeout() {
        if (timeout < 1000) {
            return;
        }
        disposableTimeoutSuccess = Single.timer(timeout, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (Consumer<Object>) o -> {
                            dismissWithCancelResult(TIMEOUT);
                            if (getDialog() != null) {
                                getDialog().dismiss();
                            }
                        }
                );

        disposables.add(disposableTimeoutSuccess);
    }

    private synchronized void delaySoftAcceptDetail(@NonNull final SoftAcceptTransactionDetail detail) {
        disposableSuccess = Single.timer(startedCountingInMillisForCancelable, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (Consumer<Object>) o -> {
                            sendDataToFragmentResult(detail);
                            //alternative -> add handler for this part
                            if (getDialog() != null) {
                                getDialog().dismiss();
                            }
                        }
                );

        disposables.add(disposableSuccess);
        successActionDialog = true;
    }

    private boolean shouldDialogBeCancelableAtStart() {
        return isDialogCancelable && displayAtLeastInMillisForCancelable <= TIMER_TICK_IN_MILLIS;
    }
}
