package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import static com.payu.android.front.sdk.payment_library_core.translation.TranslationKey.INFORMATIONS;
import static com.payu.android.front.sdk.payment_library_webview_module.web.service.WebPaymentService.INTENT_WEB_PAYMENT_EXTRA;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.about.AboutActivity;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseMenuActivity;
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails;
import com.payu.android.front.sdk.payment_library_core_android.styles.PayUDefaultDialogBuilder;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_webview_module.R;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.OnAuthorizationFinishedListener;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.PostDataEncoder;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentStatus;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.WebPaymentWrapper;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.encoder.UrlEscaperPostDataEncoder;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcher;
import com.payu.android.front.sdk.payment_library_webview_module.web.authorization.matcher.PaymentUrlMatcherFactory;
import com.payu.android.front.sdk.payment_library_webview_module.web.event.PaymentDetails;
import com.payu.android.front.sdk.payment_library_webview_module.web.formatter.SslAddressFormatter;
import com.payu.android.front.sdk.payment_library_webview_module.web.url.loader.UrlLoaderFactory;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.AddressBarPresenter;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter.WebPaymentPresenter;


public class WebPaymentActivity extends BaseMenuActivity {
    private static final String INTENT_WEB_AUTHORIZATION_EXTRA = "INTENT_WEB_AUTHORIZATION_EXTRA";
    private static final String DISPLAY_CANCEL_IN_MENU = "DISPLAY_CANCEL_IN_MENU";
    private static final String TAG = WebPaymentActivity.class.getSimpleName();
    private static final String THEME_CHANGED_KEY = "theme_changed";

    private WebPaymentView webPaymentView;
    private Toolbar toolbar;
    private TextView textTitle;

    private AlertDialog dialog;

    private WebPaymentPresenter webPaymentPresenter;
    private AuthorizationDetails authorizationDetails;
    private PaymentDetails paymentDetails;
    private LibraryStyleInfo applicationStyleInfo;
    private Boolean shouldDisplayCancelInMenu;

    @Override
    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected void bindViews() {
        webPaymentView = findViewById(R.id.payment_container);
        toolbar = findViewById(R.id.payu_toolbar);
        textTitle = findViewById(R.id.title_payu_toolbar_textView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (!shouldDisplayCancelInMenu) {
            return super.onOptionsItemSelected(item);
        }

        int i = item.getItemId();
        if (i == com.payu.android.front.sdk.payment_library_core_android.R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;

        } else if (i == com.payu.android.front.sdk.payment_library_core_android.R.id.cancel) {
            dialog.show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (shouldDisplayCancelInMenu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(com.payu.android.front.sdk.payment_library_core_android.R.menu.menu_with_cancel, menu);
            MenuItem item = menu.findItem(com.payu.android.front.sdk.payment_library_core_android.R.id.about);
            item.setTitle(translation.translate(INFORMATIONS));
            return true;

        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();
        applyStyles();
        bindDialog();
        bindWebViewUrlPayment(savedInstanceState);
        bindBackButton();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        webPaymentPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_web_payment;
    }


    private boolean getShouldDisplayCancel() {
        return getIntent().getBooleanExtra(DISPLAY_CANCEL_IN_MENU, false);
    }

    private AuthorizationDetails getAuthorizationDetailsFromIntent() {
        return getIntent().getParcelableExtra(INTENT_WEB_AUTHORIZATION_EXTRA);
    }

    private void setupData() {
        authorizationDetails = getAuthorizationDetailsFromIntent();
        shouldDisplayCancelInMenu = getShouldDisplayCancel();
        AddressBarPresenter addressBarPresenter = new AddressBarPresenter(new SslAddressFormatter(this.getApplicationContext()));
        CookieManager cookieManager = CookieManager.getInstance();
        PostDataEncoder postDataEncoder = new UrlEscaperPostDataEncoder();
        PaymentUrlMatcherFactory paymentUrlMatcherFactory = new PaymentUrlMatcherFactory();
        PaymentUrlMatcher paymentUrlMatcher = paymentUrlMatcherFactory.getUrlMatcher(authorizationDetails);
        webPaymentPresenter = new WebPaymentPresenter(addressBarPresenter, cookieManager, postDataEncoder, paymentUrlMatcher, authorizationDetails.getFallbackLink().orNull(), getCurrentRestEnvironment(this));
        webPaymentPresenter.setOnAuthorizationFinishedListener(authorizationFinishedListener);
        webPaymentPresenter.takeView(webPaymentView, this);
        textTitle.setText("");

    }

    private void bindWebViewUrlPayment(Bundle savedInstanceState) {
        if (savedInstanceState == null || savedInstanceState.getBoolean(THEME_CHANGED_KEY, false)) {
            String authorizationLink = authorizationDetails.getLink().orNull();
            Log.v(TAG, "Loading authorization link into webview: " + authorizationLink);
            new UrlLoaderFactory().createUrlLoader(authorizationDetails).load(webPaymentPresenter, authorizationDetails);
        } else {
            webPaymentPresenter.restoreState(savedInstanceState);
        }
    }

    private void bindDialog() {
        dialog = new PayUDefaultDialogBuilder(this)
                .setTitle(translation.translate(TranslationKey.DIALOG_CANCEL_PAYMENT_TITLE))
                .setMessage(translation.translate(TranslationKey.DIALOG_CANCEL_PAYMENT_MESSAGE))
                .setPositiveButton(translation.translate(TranslationKey.DIALOG_CANCEL_PAYMENT_POSITIVE), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (paymentDetails == null) {
                            paymentDetails = new PaymentDetails(WebPaymentStatus.CANCEL_PAYMENT,
                                    authorizationDetails.getOrderId().orNull(),
                                    authorizationDetails.getExtOrderId().orNull(),
                                    authorizationDetails.getContinueUrl().orNull());
                        }
                        onPaymentResult(paymentDetails);
                    }
                })
                .setNegativeButton(translation.translate(TranslationKey.DIALOG_CANCEL_PAYMENT_NEGATIVE))
                .create();
    }

    public static void start(Activity activity, AuthorizationDetails authorizationDetails, int requestCode,
                             boolean displayCancelOptionInMenu) {
        Intent intent = new Intent(activity, WebPaymentActivity.class);
        intent.putExtra(INTENT_WEB_AUTHORIZATION_EXTRA, authorizationDetails);
        intent.putExtra(DISPLAY_CANCEL_IN_MENU, displayCancelOptionInMenu);
        activity.startActivityForResult(intent, requestCode);
    }

    private OnAuthorizationFinishedListener authorizationFinishedListener = new OnAuthorizationFinishedListener() {

        @Override
        public void onAuthorizationFinished(@NonNull WebPaymentWrapper authorizationStatus) {
            paymentDetails = new PaymentDetails(authorizationStatus.getStatus(),
                    authorizationDetails.getOrderId().orNull(),
                    authorizationDetails.getExtOrderId().orNull(),
                    authorizationStatus.getContinueUrl());
            onPaymentResult(paymentDetails);
        }
    };

    private void bindBackButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webPaymentPresenter != null && webPaymentPresenter.isWebBackStackEmpty()) {
                    dialog.show();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (webPaymentPresenter != null) {
            webPaymentPresenter.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (webPaymentPresenter != null) {
            webPaymentPresenter.saveState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        super.onDestroy();
    }

    private void onPaymentResult(PaymentDetails paymentDetails) {
        Log.v(TAG, paymentDetails.toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_WEB_PAYMENT_EXTRA, paymentDetails);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void applyStyles() {
        applicationStyleInfo = LibraryStyleProvider.fromContext(this);
        toolbar.setBackgroundColor(applicationStyleInfo.getToolbarColor());
        webPaymentView.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
        createStyleFromInfo(applicationStyleInfo.getTextStyleTitle()).applyTo(textTitle);

    }
}
