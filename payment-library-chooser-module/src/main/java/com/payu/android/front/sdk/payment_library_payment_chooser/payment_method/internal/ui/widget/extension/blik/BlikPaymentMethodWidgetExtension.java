package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.extension.blik;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.styles.EditTextStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget.OnSelectionPaymentMethodListener;

public class BlikPaymentMethodWidgetExtension extends LinearLayout implements BlikPaymentMethodView {
    private final static float ALPHA_VIEW_VISIBLE = 1f;
    private final static float ALPHA_VIEW_INVISIBLE = 0f;
    private final static int DURATION_OF_ANIMATION = 1200;

    private Translation translation;
    private BlikPaymentMethodPresenter blikPresenter;

    private TextView textBankInformation;
    private TextView textOpenCodeProvider;
    private TextInputLayout t6BlikPaymentInputLayout;
    private View viewContainer;
    private View authorizationCodeContainer;

    public BlikPaymentMethodWidgetExtension(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlikPaymentMethodWidgetExtension(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.payu_view_payment_blik_method, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindViews();
        translation = TranslationFactory.getInstance();
        setupStyles();
        setupData();
        blikPresenter = new BlikPaymentMethodPresenter();
        blikPresenter.takeView(this);
    }


    private void setupData() {
        textBankInformation.setText(translation.translate(TranslationKey.BLIK_BANK_INFORMATION_SAVE_PAYMENT));
        t6BlikPaymentInputLayout.setHint(translation.translate(TranslationKey.BLIK_HINT));
        textOpenCodeProvider.setText(Html.fromHtml(translation.translate(TranslationKey.BLIK_INPUT_NEW_CODE)));
        textOpenCodeProvider.setTextColor(ContextCompat.getColor(getContext(), com.payu.android.front.sdk.payment_library_core_android.R.color.payu_styles_color_secondary_link));
        textOpenCodeProvider.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideCodeAction();
            }
        });
    }

    private void setupStyles() {
        LibraryStyleInfo libraryStyleInfo = LibraryStyleProvider.fromContext(getContext());
        new TextViewStyle(libraryStyleInfo.getTextStyleDescription(), new FontProvider(getContext())).applyTo(textBankInformation);
        new TextViewStyle(libraryStyleInfo.getTextStyleDescription(), new FontProvider(getContext())).applyTo(textOpenCodeProvider);
        EditTextStyle editTextStyle = createEditTextStaleFromInfo(libraryStyleInfo.getTextStyleText());
        editTextStyle.applyTo(t6BlikPaymentInputLayout.getEditText());
        int windowContentPadding = ((int) libraryStyleInfo.getWindowContentPadding());
        viewContainer.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
    }

    private void bindViews() {
        viewContainer = findViewById(R.id.container);
        textBankInformation = findViewById(R.id.blik_bank_save_payment_information_textView);
        t6BlikPaymentInputLayout = findViewById(R.id.blik_textInputLayout);
        textOpenCodeProvider = findViewById(R.id.blik_open_code_provider_textView);
        authorizationCodeContainer = findViewById(R.id.input_container);
        hideInput();
    }

    private EditTextStyle createEditTextStaleFromInfo(TextStyleInfo editTextStyleInfo) {
        return new EditTextStyle(editTextStyleInfo, new FontProvider(getContext()));
    }

    @Override
    public void showInput() {
        viewContainer.setAlpha(ALPHA_VIEW_INVISIBLE);
        viewContainer.setVisibility(VISIBLE);
        viewContainer
                .animate()
                .alpha(ALPHA_VIEW_VISIBLE)
                .setDuration(DURATION_OF_ANIMATION)
                .setListener(null);
        authorizationCodeContainer.setVisibility(VISIBLE);
        textOpenCodeProvider.setVisibility(GONE);
    }

    @Override
    public void hideInput() {
        viewContainer.setVisibility(View.GONE);
        authorizationCodeContainer.setVisibility(GONE);
        textOpenCodeProvider.setVisibility(GONE);
        cleanInputText();
    }

    @Override
    public void showCodeAction() {
        viewContainer.setAlpha(ALPHA_VIEW_INVISIBLE);
        viewContainer.setVisibility(VISIBLE);
        viewContainer
                .animate()
                .alpha(ALPHA_VIEW_VISIBLE)
                .setDuration(DURATION_OF_ANIMATION)
                .setListener(null);
        authorizationCodeContainer.setVisibility(GONE);
        textOpenCodeProvider.setVisibility(VISIBLE);
        cleanInputText();
    }

    @Override
    public void hideCodeAction() {
        textOpenCodeProvider.setVisibility(GONE);
        authorizationCodeContainer.setAlpha(ALPHA_VIEW_INVISIBLE);
        authorizationCodeContainer.setVisibility(VISIBLE);
        authorizationCodeContainer
                .animate()
                .alpha(ALPHA_VIEW_VISIBLE)
                .setDuration(DURATION_OF_ANIMATION)
                .setListener(null);
        authorizationCodeContainer.requestFocus();
        viewContainer.setVisibility(VISIBLE);
        cleanInputText();
    }

    @Override
    public boolean isInputDisplayed() {
        return authorizationCodeContainer.getVisibility() == View.VISIBLE;
    }

    private void cleanInputText() {
        EditText editText = t6BlikPaymentInputLayout.getEditText();
        if (editText != null) {
            editText.setText("");
        }
    }

    @Nullable
    @Override
    public String getBlikCode() {
        if (!isInputDisplayed()) {
            return null;
        }
        EditText editText = t6BlikPaymentInputLayout.getEditText();
        Editable rawCode = editText != null ? editText.getText() : null;
        if (rawCode == null) {
            return null;
        }
        return rawCode.toString();
    }

    @NonNull
    public OnSelectionPaymentMethodListener getOnSelectionPaymentMethodListener() {
        return blikPresenter.getOnSelectionPaymentMethodListener();
    }

    @Nullable
    public String getAuthorizationCode() {
        return blikPresenter.getBlikCode();
    }

    public boolean isBlikAuthorizationCodeProvided() {
        return blikPresenter.checkProvidedCode();
    }

}
