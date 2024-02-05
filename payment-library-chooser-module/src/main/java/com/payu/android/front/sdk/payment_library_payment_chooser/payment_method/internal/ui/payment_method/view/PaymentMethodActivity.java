package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseMenuActivity;
import com.payu.android.front.sdk.payment_library_core_android.styles.PayUDefaultDialogBuilder;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.card.CreateAndSelectCardActivity;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemRemovedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.PaymentMethodPresenterProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view.PayByLinkChooserActivity;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.presentation.PaymentMethodsPresenter;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodActivity extends BaseMenuActivity implements PaymentMethodsView {
    private static final int PAY_BY_LINK_REQUEST_CODE = 1234;
    private static final int ADD_CARD_REQUEST_CODE = 1235;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView textTitle;
    private PaymentMethodAdapter paymentMethodAdapter;
    private PaymentMethodsPresenter presenter;
    private AlertDialog removalDialog;
    private ProgressBar progressBar;

    public static void start(Context context) {
        Intent intent = new Intent(context, PaymentMethodActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PaymentMethodPresenterProvider.createPaymentMethodPresenter(this);
        presenter.takeView(this);
        setupAdapter();
        textTitle.setText(translation.translate(TranslationKey.SELECT_PAYMENT_METHOD));
        applyStyles();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (removalDialog != null && removalDialog.isShowing()) {
            removalDialog.dismiss();
        }
    }

    @Override
    public void setLoadingVisible(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showItemRemovalConfirmation(final Runnable onConfirmed) {
        removalDialog = new PayUDefaultDialogBuilder(this)
                .setTitle(translation.translate(TranslationKey.REMOVE_METHOD_DIALOG_TITLE))
                .setMessage(translation.translate(TranslationKey.REMOVE_METHOD_DIALOG_CONTENT))
                .setPositiveButton(translation.translate(TranslationKey.REMOVE), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirmed.run();
                        removalDialog.dismiss();
                    }
                })
                .setNegativeButton(translation.translate(TranslationKey.CANCEL))
                .show();
    }

    @Override
    public void showAddCardScreen() {
        CreateAndSelectCardActivity.startForResult(this, ADD_CARD_REQUEST_CODE);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showBankPaymentsScreen() {
        PayByLinkChooserActivity.startForResult(this, PAY_BY_LINK_REQUEST_CODE);
    }

    @Override
    public void bindToPaymentMethods(@NonNull LiveData<List<PaymentMethodModel>> paymentMethods) {
        paymentMethods.observe(this, new Observer<List<PaymentMethodModel>>() {
            @Override
            public void onChanged(@Nullable List<PaymentMethodModel> paymentMethodModels) {
                if (paymentMethodModels != null) {
                    paymentMethodAdapter.setMethodModels(paymentMethodModels);
                }
            }
        });
        if (paymentMethods.getValue() != null) {
            paymentMethodAdapter.setMethodModels(paymentMethods.getValue());
        }
    }

    private void setupAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        paymentMethodAdapter = new PaymentMethodAdapter(new ArrayList<PaymentMethodModel>(), new OnItemSelectedListener<PaymentMethodModel>() {
            @Override
            public void onSelected(@NonNull PaymentMethodModel element) {
                presenter.onPaymentMethodSelected(element);
            }
        }, new OnItemRemovedListener<PaymentMethodModel>() {
            @Override
            public void onRemoved(@NonNull PaymentMethodModel element) {
                presenter.onPaymentMethodRemoved(element);
            }
        });
        recyclerView.setAdapter(paymentMethodAdapter);
    }

    private void applyStyles() {
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(this);
        createStyleFromInfo(applicationStyleInfo.getTextStyleTitle()).applyTo(textTitle);
        int windowContentPadding = (int) applicationStyleInfo.getWindowContentPadding();
        recyclerView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
        recyclerView.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
        toolbar.setBackgroundColor(applicationStyleInfo.getToolbarColor());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PAY_BY_LINK_REQUEST_CODE) {
                presenter.onPayByLinkSelectionSuccessful();
                return;
            } else if (requestCode == ADD_CARD_REQUEST_CODE) {
                presenter.onAddCardSuccessful();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected void bindViews() {
        this.toolbar = findViewById(R.id.payu_toolbar);
        this.recyclerView = findViewById(R.id.payment_method_recyclerView);
        this.textTitle = findViewById(R.id.title_payu_toolbar_textView);
        this.progressBar = findViewById(R.id.loading_payment_methods);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_payment_method;
    }
}
