package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.view;

import android.app.Activity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseMenuActivity;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.PaymentMethodPresenterProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation.PayByLinkPresenter;

import java.util.ArrayList;
import java.util.List;

public class PayByLinkChooserActivity extends BaseMenuActivity implements PayByLinkView {
    private static final int ROWS_COUNT = 3;
    private Toolbar toolbar;
    private RecyclerView payByLinkRecycler;
    private TextView toolbarTitleText;
    private ViewGroup payByLinkActivityContainer;
    private PayByLinkChooserAdapter payByLinkChooserAdapter;
    private PayByLinkPresenter payByLinkPresenter;

    public static void startForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, PayByLinkChooserActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void bindToPayByLinkModel(
            LiveData<List<PayByLinkModel>> pblModelLiveData) {
        pblModelLiveData.observe(this, new Observer<List<PayByLinkModel>>() {
            @Override
            public void onChanged(@Nullable List<PayByLinkModel> payByLinkModels) {
                if (payByLinkModels != null) {
                    payByLinkChooserAdapter.setModel(payByLinkModels);
                }
            }
        });
        List<PayByLinkModel> value = pblModelLiveData.getValue();
        if (value != null) {
            payByLinkChooserAdapter.setModel(value);
        }
    }

    @Override
    public void closeWithSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payByLinkPresenter = PaymentMethodPresenterProvider.createPayByLinkPresenter(this);
        payByLinkPresenter.takeView(this);
        setupGrid();
        setupTranslations();
        setupStyle();
    }

    private void setupTranslations() {
        toolbarTitleText.setText(translation.translate(TranslationKey.PBL_TITLE));
    }

    private void setupGrid() {
        payByLinkRecycler.setLayoutManager(new GridLayoutManager(this, ROWS_COUNT));
        payByLinkChooserAdapter = new PayByLinkChooserAdapter(new ArrayList<PayByLinkModel>(), new OnItemSelectedListener<PayByLinkModel>() {
            @Override
            public void onSelected(@NonNull PayByLinkModel element) {
                payByLinkPresenter.onPblSelected(element);
            }
        });
        payByLinkRecycler.setAdapter(payByLinkChooserAdapter);
    }

    private void setupStyle() {
        LibraryStyleInfo libraryStyleInfo = LibraryStyleProvider.fromContext(this);
        payByLinkActivityContainer.setBackgroundColor(libraryStyleInfo.getBackgroundColor());
        toolbar.setBackgroundColor(libraryStyleInfo.getToolbarColor());
        int windowContentPadding = (int) libraryStyleInfo.getWindowContentPadding();
        payByLinkRecycler.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
        createStyleFromInfo(libraryStyleInfo.getTextStyleTitle()).applyTo(toolbarTitleText);
    }

    @Override
    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected void bindViews() {
        toolbar = findViewById(R.id.payu_toolbar);
        toolbarTitleText = findViewById(R.id.title_payu_toolbar_textView);
        payByLinkRecycler = findViewById(R.id.pbl_methods_recyclerView);
        payByLinkActivityContainer = findViewById(R.id.pay_by_link_activity_container);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_pay_by_link;
    }
}
