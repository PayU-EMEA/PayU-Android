package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_core_android.util.ThemeUtils;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodStaticHolder;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view.PaymentMethodActivity;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType;

public class SelectedPaymentMethodWidget extends LinearLayout implements SelectedPaymentMethodView, Observer<SelectedPaymentMethodModel> {
    private SelectedPaymentMethodPresenter presenter;

    private TextView textSelectedPaymentHeader;
    private TextView textSelectedPaymentContent;
    private ImageView imageSelectedPaymentMethod;
    private FrameLayout logoContainer;
    private Translation translation;
    private OnSelectionPaymentMethodListener onSelectionPaymentMethodListener;
    private LiveData<SelectedPaymentMethodModel> selectedPaymentMethodLiveData;
    public SelectedPaymentMethodWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectedPaymentMethodWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_selected_payment_method, this);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        translation = TranslationFactory.getInstance();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindViews();
        setupStyles();
        initializePresenter();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupListener();
        setupBinding();
    }

    private void initializePresenter() {
        SelectedPaymentMethodConverter selectedPaymentMethodConverter = new SelectedPaymentMethodConverter(translation);
        presenter = new SelectedPaymentMethodPresenter(PaymentMethodStaticHolder.getInstance(getContext()).getPaymentMethodRepository(),
                selectedPaymentMethodConverter);
        presenter.takeView(this);
    }

    private void setupStyles() {
        LibraryStyleInfo libraryStyleInfo = LibraryStyleProvider.fromContext(getContext());
        logoContainer.setBackgroundColor(libraryStyleInfo.getAccentColor());
        new TextViewStyle(libraryStyleInfo.getTextStyleText(), new FontProvider(getContext())).applyTo(textSelectedPaymentHeader);
        new TextViewStyle(libraryStyleInfo.getTextStyleDescription(), new FontProvider(getContext())).applyTo(textSelectedPaymentContent);
    }

    private void bindViews() {
        textSelectedPaymentHeader = findViewById(R.id.selected_payment_header_textView);
        textSelectedPaymentContent = findViewById(R.id.selected_payment_content_textView);
        imageSelectedPaymentMethod = findViewById(R.id.payment_method_imageView);
        logoContainer = findViewById(R.id.logo_container_frameLayout);
    }

    private void setupListener() {
        setClickable(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSelectPaymentMethodClick();
            }
        });
    }

    private void setupBinding() {
        selectedPaymentMethodLiveData = presenter.getSelectedPaymentMethodLiveData();
        SelectedPaymentMethodModel selectedPaymentMethod = selectedPaymentMethodLiveData.getValue();
        if (selectedPaymentMethod != null) {
            setPaymentMethod(selectedPaymentMethod);
        } else {
            clearPaymentMethod();
        }
        selectedPaymentMethodLiveData.observeForever(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        selectedPaymentMethodLiveData.removeObserver(this);
        setOnClickListener(null);
    }

    @Override
    public void showPaymentSelector() {
        PaymentMethodActivity.start(getContext());
    }

    @Override
    public void onChanged(@Nullable SelectedPaymentMethodModel paymentMethod) {
        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        } else {
            clearPaymentMethod();
        }
        updateListener();
    }

    private void updateListener() {
        if (onSelectionPaymentMethodListener == null) {
            return;
        }
        if (getPaymentMethod() == null) {
            onSelectionPaymentMethodListener.onSelectionChange(null);
        } else {
            onSelectionPaymentMethodListener.onSelectionChange(getPaymentMethod().getValue());
        }
    }

    private void clearPaymentMethod() {
        textSelectedPaymentHeader.setText(translation.translate(TranslationKey.SELECT_PAYMENT_METHOD));
        textSelectedPaymentContent.setVisibility(GONE);
        imageSelectedPaymentMethod.setVisibility(GONE);
    }

    public LiveData<PaymentMethod> getPaymentMethod() {
        return presenter.getPaymentMethod();
    }

    private void setPaymentMethod(@NonNull SelectedPaymentMethodModel selectedPaymentMethod) {
        textSelectedPaymentHeader.setText(selectedPaymentMethod.getHeader());
        if (selectedPaymentMethod.getContent() != null) {
            textSelectedPaymentContent.setText(selectedPaymentMethod.getContent());
            textSelectedPaymentContent.setVisibility(VISIBLE);
        } else {
            textSelectedPaymentContent.setVisibility(GONE);
        }
        setLightBackgroundInNightMode();
        Glide.with(getContext().getApplicationContext())
                .load(selectedPaymentMethod.getImageUrl())
                .override(imageSelectedPaymentMethod.getWidth())
                .into(imageSelectedPaymentMethod);
        imageSelectedPaymentMethod.setVisibility(VISIBLE);
    }

    private void setLightBackgroundInNightMode() {
        ViewGroup.LayoutParams layoutParams = imageSelectedPaymentMethod.getLayoutParams();
        if (shouldShowLightBackground()) {
            imageSelectedPaymentMethod.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.logo_background_night_theme));
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            imageSelectedPaymentMethod.setPadding(0, 0, 0, 0);
            imageSelectedPaymentMethod.setBackgroundColor(Color.TRANSPARENT);
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.payu_styles_payment_method_icon_height);
        }
    }

    private boolean shouldShowLightBackground() {
        PaymentMethod paymentMethod = getPaymentMethod().getValue();
        return ThemeUtils.isInNightMode(getContext()) && paymentMethod != null
                && (paymentMethod.getPaymentType() == PaymentType.PBL || paymentMethod.getPaymentType() == PaymentType.PEX);
    }

    public void cleanPaymentMethods() {
        presenter.cleanPaymentMethods();
    }

    public void cleanPaymentMethodsWithoutSelectedPayment(){
        presenter.cleanPaymentMethodWithoutRemovingSelectedMethod();
    }
    public void setSelectionPaymentListener(@Nullable OnSelectionPaymentMethodListener listener) {
        this.onSelectionPaymentMethodListener = listener;
    }
}
