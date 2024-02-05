package com.payu.android.front.sdk.payment_add_card_module.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_add_card_module.formatter.EditTextFormattingTextWatcher;
import com.payu.android.front.sdk.payment_add_card_module.formatter.FormattingStrategy;
import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuerProvider;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.CardIssuerLogoTextWatcher;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.OnCardIssuerChangedListener;
import com.payu.android.front.sdk.payment_add_card_module.validation.LuhnValidator;
import com.payu.android.front.sdk.payment_add_card_module.validation.ValidationErrorCleanerTextWatcher;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.util.MaxLengthSetter;

public class CardNumberView extends TextInputLayout implements SelectNumber {
    private static final String ACCEPTED_CARD_NUMBER_CHARACTERS = "0123456789 ";
    private static final int MAX_FORMATTED_CARD_LENGTH = 23;
    private EditText cardNumberEditText;
    private Translation translation;

    public CardNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        translation = TranslationFactory.getInstance();
        cardNumberEditText = getEditText();
        configureEditText();
        setHint();
    }

    void init() {
        inflate(getContext(), R.layout.payu_view_card_number, this);
    }

    private void configureEditText() {
        cardNumberEditText.addTextChangedListener(new ValidationErrorCleanerTextWatcher(this));
        formatEditTextValues();
    }

    private void formatEditTextValues() {
        cardNumberEditText.setFilters(new InputFilter[]{DigitsKeyListener.getInstance(ACCEPTED_CARD_NUMBER_CHARACTERS)});
        cardNumberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        MaxLengthSetter.setMaxLength(cardNumberEditText, MAX_FORMATTED_CARD_LENGTH);
    }

    public void clear() {
        setError(null);
        cardNumberEditText.setText("");
    }

    private void setHint() {
        setHint(translation.translate(TranslationKey.CARD_NUMBER));
    }

    @Override
    public String getText() {
        return cardNumberEditText.getText().toString().trim();
    }

    @Override
    public void addValidateOnFocusChangeListener(@Nullable final ValidateOnFocusChangeListener onFocusChangeListener) {
        cardNumberEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeListener != null) {
                    onFocusChangeListener.validateOnFocusChange(hasFocus);
                }
            }
        });
    }

    @Override
    public void setNumberError(String error) {
        setError(error);
    }


    @Override
    public void addFormattingStrategy(@NonNull FormattingStrategy formattingStrategy) {
        cardNumberEditText.addTextChangedListener(new EditTextFormattingTextWatcher(getEditText(), formattingStrategy));
    }

    @Override
    public void addCardIssuerLogoStrategy(@NonNull CardIssuerProvider cardIssuerProvider, @NonNull LuhnValidator luhnValidator, @NonNull OnCardIssuerChangedListener onCardIssuerChanged) {
        cardNumberEditText.addTextChangedListener(new CardIssuerLogoTextWatcher(cardIssuerProvider, luhnValidator, onCardIssuerChanged));
    }

    @Override
    public void setCardNumber(@NonNull String cardNumber) {
        cardNumberEditText.setText(cardNumber);
    }
}
