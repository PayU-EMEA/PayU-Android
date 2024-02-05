package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseMenuActivity;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.service.BlikAmbiguityService;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.blik_ambiguity.presentation.BlikPresenter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.OnItemSelectedListener;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.common.PaymentMethodPresenterProvider;

import java.util.ArrayList;
import java.util.List;

public class BlikAmbiguityActivity extends BaseMenuActivity implements BlikView {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView toolbarTextTitle;
    private BlikAmbiguityAdapter adapter;
    private BlikPresenter presenter;
    private ViewGroup activityContainer;

    public static void startForResult(@NonNull Activity activity, int resultCode) {
        Intent intent = new Intent(activity, BlikAmbiguityActivity.class);
        activity.startActivityForResult(intent, resultCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = PaymentMethodPresenterProvider.createBlikPresenter(this);
        presenter.takeView(this);
        setupGrid();
        setupTranslations();
        setupStyle();
    }

    private void setupTranslations() {
        toolbarTextTitle.setText(translation.translate(TranslationKey.BLIK_AMBIGUITY_SELECTION));
    }

    @Override
    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected void bindViews() {
        this.toolbar = findViewById(R.id.payu_toolbar);
        this.recyclerView = findViewById(R.id.payment_method_recyclerView);
        this.toolbarTextTitle = findViewById(R.id.title_payu_toolbar_textView);
        this.activityContainer = findViewById(R.id.ambiguity_blik_activity_container);
    }

    private void setupStyle() {
        LibraryStyleInfo libraryStyleInfo = LibraryStyleProvider.fromContext(this);
        activityContainer.setBackgroundColor(libraryStyleInfo.getBackgroundColor());
        toolbar.setBackgroundColor(libraryStyleInfo.getToolbarColor());
        int windowContentPadding = (int) libraryStyleInfo.getWindowContentPadding();
        recyclerView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
        createStyleFromInfo(libraryStyleInfo.getTextStyleTitle()).applyTo(toolbarTextTitle);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_blik_ambiguity;
    }

    @Override
    public void onBackPressed() {
        //We don't support going back in this view
    }

    private void setupGrid() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new BlikAmbiguityAdapter(new ArrayList<PaymentMethodModel>(), new OnItemSelectedListener<PaymentMethodModel>() {
            @Override
            public void onSelected(@NonNull PaymentMethodModel element) {
                presenter.onSelectedBlik(element);
            }
        });
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void closeWithSelectedBlik() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BlikAmbiguityService.INTENT_SELECTED_BLIK_EXTRA, presenter.getSelectedBlik());
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void bindToModel(LiveData<List<PaymentMethodModel>> modelLiveData) {
        modelLiveData.observe(this, new Observer<List<PaymentMethodModel>>() {
            @Override
            public void onChanged(@Nullable List<PaymentMethodModel> paymentMethodModels) {
                if (paymentMethodModels != null) {
                    adapter.setModel(paymentMethodModels);
                }
            }
        });

        List<PaymentMethodModel> value = modelLiveData.getValue();
        if (value != null) {
            adapter.setModel(value);
        }
    }
}