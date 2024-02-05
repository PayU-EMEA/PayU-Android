package com.payu.android.front.sdk.payment_add_card_module.view;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_add_card_module.formatter.DateFormattingStrategy;
import com.payu.android.front.sdk.payment_add_card_module.formatter.EditTextFormattingTextWatcher;
import com.payu.android.front.sdk.payment_add_card_module.validation.ValidationErrorCleanerTextWatcher;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.styles.EditTextStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_core_android.util.MaxLengthSetter;

import java.util.Locale;

public class CardDateView extends LinearLayout implements CardDate {
    private static final int DATE_MAX_LENGTH = 7; // MM/RRRR
    private static final int TWO_DIGIT_MONTH = 10;
    private TextInputLayout dateExpirationTextInputLayout;

    private Translation translation;

    public CardDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardDateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dateExpirationTextInputLayout = findViewById(R.id.expirationDate_textInputLayout);

        translation = TranslationFactory.getInstance();

        configureDateEditText();

        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());

        EditTextStyle editTextStyle = createEditTextStaleFromInfo(applicationStyleInfo.getTextStyleInput());
        editTextStyle.applyTo(dateExpirationTextInputLayout.getEditText());
    }

    private void init() {
        inflate(getContext(), R.layout.payu_view_card_expiration_date, this);
    }

    private void setError(boolean errorState) {
        setSelected(errorState);
    }

    private void configureDateEditText() {
        dateExpirationTextInputLayout.setHint(translation.translate(TranslationKey.EXPIRATION_DATE_HINT_TEXT));
        EditText editText = dateExpirationTextInputLayout.getEditText();
        if (editText != null) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.addTextChangedListener(new ValidationErrorCleanerTextWatcher(dateExpirationTextInputLayout));
            editText.addTextChangedListener(new EditTextFormattingTextWatcher(editText, new DateFormattingStrategy()));
            MaxLengthSetter.setMaxLength(editText, DATE_MAX_LENGTH);
        }
    }

    public void clear() {
        setSelected(false);
        setError(false);
        EditText editText = dateExpirationTextInputLayout.getEditText();
        if (editText != null) {
        editText.setText("");
        }
    }

    @Override
    public void setExpirationDate(int monthOfYear, int year) {
        String textFormat = "%d/%d";
        if (monthOfYear < TWO_DIGIT_MONTH) {
            textFormat = "%02d/%d";
        }
        dateExpirationTextInputLayout.getEditText().setText(String.format(Locale.getDefault(), textFormat, monthOfYear, year));
        setError(false);
    }

    @Override
    public void addValidateOnFocusChangeListener(@Nullable final ValidateOnFocusChangeListener onFocusChangeListener) {
        EditText editText = dateExpirationTextInputLayout.getEditText();
        if (editText != null) {
            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (onFocusChangeListener != null) {
                        onFocusChangeListener.validateOnFocusChange(hasFocus);
                    }
                }
            });
        }
    }

    @Override
    public void setErrorState(boolean isError) {
        setError(isError);
    }

    @Override
    public void setDateError(String error) {
        dateExpirationTextInputLayout.setError(error);
    }

    @Override
    public String getDate() {
        return dateExpirationTextInputLayout.getEditText().getText().toString();
    }

    @Override
    public void setEnabled(boolean enabled) {
        dateExpirationTextInputLayout.setEnabled(enabled);
        super.setEnabled(enabled);
    }

    @NonNull
    private EditTextStyle createEditTextStaleFromInfo(TextStyleInfo editTextStyleInfo) {
        return new EditTextStyle(editTextStyleInfo, new FontProvider(getContext()));
    }
}

