package com.payu.android.front.sdk.marketplace_terms_condition.marketplace.view;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.payu.android.front.sdk.marketplace_terms_condition.R;
import com.payu.android.front.sdk.payment_library_core_android.styles.ButtonStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.ButtonStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;

public class TermsMarketPlaceView extends ConstraintLayout implements SelectTerms {
    private TextView titleTermsTextView;
    private MaterialCheckBox termsCheckBox;
    private TextView linkTextView;
    private final MutableLiveData<Boolean> checkBoxStateLiveData = new MutableLiveData<>();

    public TermsMarketPlaceView(@NonNull Context context) {
        super(context);
        init();
    }

    public TermsMarketPlaceView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TermsMarketPlaceView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TermsMarketPlaceView(@NonNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_marketplace_terms, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        titleTermsTextView = findViewById(R.id.payu_marketplace_title_textView);
        termsCheckBox = findViewById(R.id.payu_marketplace_terms_checkBox);
        linkTextView = findViewById(R.id.payu_marketplace_terms_link);
        termsCheckBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxStateLiveData.postValue(TermsMarketPlaceView.this.isCheckBoxSet());
            }
        });
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());

        createStyleFromInfo(applicationStyleInfo.getTextStyleTitle()).applyTo(titleTermsTextView);
        createButtonStyleInfo(applicationStyleInfo.getTextStyleButtonPrimary()).applyTo(termsCheckBox);
        createStyleFromInfo(applicationStyleInfo.getTextStyleText()).applyTo(linkTextView);
        this.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
    }

    @NonNull
    protected ButtonStyle createButtonStyleInfo(ButtonStyleInfo buttonStyleInfo) {
        return new ButtonStyle(buttonStyleInfo);
    }

    @NonNull
    protected TextViewStyle createStyleFromInfo(TextStyleInfo styleInfo) {
        return new TextViewStyle(styleInfo, new FontProvider(getContext()));
    }

    @Override
    public void setInformation(@NonNull String title, @NonNull String checkboxDescription, @NonNull String linkWithText) {
        titleTermsTextView.setText(title);
        termsCheckBox.setText(checkboxDescription);
        SpannableString spanText = new SpannableString(Html.fromHtml(linkWithText));
        linkTextView.setOnTouchListener(new TouchTextView(spanText));
        linkTextView.setText(spanText);


    }

    @Override
    public LiveData<Boolean> checkboxState() {
        return checkBoxStateLiveData;
    }

    @Override
    public boolean isCheckBoxSet() {
        return termsCheckBox.isChecked();
    }
}
