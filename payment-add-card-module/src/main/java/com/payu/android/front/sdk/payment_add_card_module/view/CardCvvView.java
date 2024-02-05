package com.payu.android.front.sdk.payment_add_card_module.view;

import android.content.Context;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.google.common.base.MoreObjects;
import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_add_card_module.validation.ValidationErrorCleanerTextWatcher;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.util.MaxLengthSetter;

public class CardCvvView extends TextInputLayout implements SelectorCvv {
    private static final int CVV_MAX_LENGTH = 3;
    private static final String EMPTY_STRING = "";
    private Translation translation;

    @Nullable
    private EditText cvvEditText;

    public CardCvvView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardCvvView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        cvvEditText = getEditText();
        translation = TranslationFactory.getInstance();
        configureCvvEditText();
    }

    public void clear() {
        setError(null);
        if (cvvEditText != null) {
            cvvEditText.setText("");

        }
    }

    private void configureCvvEditText() {
        if (cvvEditText != null) {
            cvvEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            cvvEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            cvvEditText.addTextChangedListener(new ValidationErrorCleanerTextWatcher(this));
            MaxLengthSetter.setMaxLength(cvvEditText, CVV_MAX_LENGTH);
        }
        setHint(translation.translate(TranslationKey.CVV_CODE));
    }

    void init() {
        inflate(getContext(), R.layout.payu_view_card_cvv, this);
    }

    @Override
    public void addValidateOnFocusChangeListener(@Nullable final ValidateOnFocusChangeListener onFocusChangeListener) {
        if (cvvEditText != null) {
            cvvEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (onFocusChangeListener != null) {
                        onFocusChangeListener.validateOnFocusChange(hasFocus);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public String getCvvCode() {
        if (cvvEditText != null) {
            return MoreObjects.firstNonNull(cvvEditText.getText(), EMPTY_STRING).toString().trim();
        } else {
            return EMPTY_STRING;
        }
    }

    @Override
    public void setCvvError(@Nullable String error) {
        setError(error);
    }
}
