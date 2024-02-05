package com.payu.android.front.sdk.payment_add_card_module.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_add_card_module.R;
import com.payu.android.front.sdk.payment_library_core_android.conditions_view.PayUTermView;
import com.payu.android.front.sdk.payment_library_core_android.styles.EditTextStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;

public class NewCardView extends LinearLayout {
    private static final float MIN_ALPHA = 0.35f;
    private static final float MAX_ALPHA = 1.0f;

    private CardNumberView cardNumberView;
    private CardDateView cardDateView;
    private CardCvvView cardCvvView;
    private CardSelectorView cardIssuerView;
    private PayUTermView payUTermView;

    public NewCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        cardNumberView = findViewById(R.id.cardNumber_view);
        cardDateView = findViewById(R.id.cardDate_view);
        cardCvvView = findViewById(R.id.cardCvv_view);
        cardIssuerView = findViewById(R.id.cardSelector_view);
        payUTermView = findViewById(R.id.payuTerms_view);
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());

        int windowContentPadding = (int) applicationStyleInfo.getWindowContentPadding();
        createEditTextStaleFromInfo(applicationStyleInfo.getTextStyleInput()).applyTo(cardNumberView.getEditText());
        createEditTextStaleFromInfo(applicationStyleInfo.getTextStyleInput()).applyTo(cardCvvView.getEditText());

        cardNumberView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
        cardDateView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding/2, windowContentPadding);
        cardCvvView.setPadding(windowContentPadding/2, windowContentPadding, windowContentPadding, windowContentPadding);
        cardIssuerView.setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);

        this.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            animateDisabled(cardNumberView);
            animateDisabled(cardDateView);
            animateDisabled(cardCvvView);
        } else {
            animateEnabled(cardNumberView);
            animateEnabled(cardDateView);
            animateEnabled(cardCvvView);
        }
        super.setEnabled(enabled);
    }

    @NonNull
    protected EditTextStyle createEditTextStaleFromInfo(TextStyleInfo inputTextStyleInfo) {
        return new EditTextStyle(inputTextStyleInfo, new FontProvider(getContext()));
    }


    private void init() {
        inflate(getContext(), R.layout.payu_view_new_card, this);
    }

    public SelectNumber getCardNumberView() {
        return cardNumberView;
    }

    public CardDate getCardDateView() {
        return cardDateView;
    }

    public SelectorCvv getCardCvvView() {
        return cardCvvView;
    }

    public SelectorView getSelectorView() {
        return cardIssuerView;
    }

    public void hidePayUTermsView() {
        payUTermView.setVisibility(GONE);
    }

    public void clearData() {
        cardNumberView.clear();
        cardDateView.clear();
        cardCvvView.clear();
        cardIssuerView.clear();
    }

    private void animateDisabled(View view) {
        view.setEnabled(false);
        view.animate().alpha(MIN_ALPHA);
    }

    private void animateEnabled(final View view) {
        view.animate().alpha(MAX_ALPHA).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setEnabled(true);
            }
        });
    }
}
