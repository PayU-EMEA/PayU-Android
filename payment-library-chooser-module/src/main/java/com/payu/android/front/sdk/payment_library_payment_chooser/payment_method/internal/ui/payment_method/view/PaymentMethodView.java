package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.payment_method.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.payu.android.front.sdk.payment_library_core_android.styles.ButtonStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;
import com.payu.android.front.sdk.payment_library_core_android.util.ThemeUtils;
import com.payu.android.front.sdk.payment_library_payment_chooser.R;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodModel;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PaymentMethodType;

public class PaymentMethodView extends LinearLayout {
    private static final String TAG = "PaymentMethodView";
    private PaymentMethodModel methodModel;
    private TextView textTitleLabel;
    private TextView textTitleContent;
    private TextView textDescriptionLabel;
    private TextView textDescriptionContent;
    private ImageView imageIcon;
    private LibraryStyleInfo applicationStyleInfo;

    public PaymentMethodView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.payu_view_payment_method, this);
        setGravity(Gravity.START);

    }

    private static void setOrHide(@NonNull TextView textView, @Nullable String content) {
        setOrHide(textView, content, true);
    }

    private static void setOrHide(@NonNull TextView textView, @Nullable String content, boolean enabled) {
        if (content == null || content.isEmpty()) {
            textView.setVisibility(GONE);
        } else {
            textView.setVisibility(VISIBLE);
            textView.setText(content);
            textView.setEnabled(enabled);
        }
    }

    private static void setOrHide(@NonNull Context context, @NonNull LibraryStyleInfo applicationStyleInfo, @NonNull ImageView imageView, @NonNull PaymentMethodModel model) {
        if (model.hasImage()) {
            imageView.setVisibility(VISIBLE);
            if (model.hasImageUrl()) {
                setImageFromUrl(context, imageView, model);
            } else {
                imageView.setImageResource(model.getImageRes());
            }
            setImageViewColorFilter(applicationStyleInfo, imageView, model);
        } else {
            imageView.setVisibility(GONE);
        }
    }

    private static void setImageFromUrl(@NonNull Context context, @NonNull ImageView imageView, @NonNull PaymentMethodModel model) {
        boolean showBackground = shouldShowLightBackground(context, model);
        setBackgroundInNightMode(context, imageView, showBackground);
        Glide.with(context.getApplicationContext()).load(model.getImageUrl()).into(imageView);
    }

    private static void setImageViewColorFilter(@NonNull LibraryStyleInfo applicationStyleInfo, @NonNull ImageView imageView, @NonNull PaymentMethodModel model) {
        if (hasGeneralIcon(model)) {
            imageView.setColorFilter(applicationStyleInfo.getAccentColor());
        } else {
            imageView.clearColorFilter();
        }
    }

    private static boolean hasGeneralIcon(@Nullable PaymentMethodModel model) {
        return model != null && model.getPaymentMethodType() == PaymentMethodType.GENERAL_ICON;
    }

    private static void setBackgroundInNightMode(@NonNull Context context, @NonNull ImageView imageView, boolean showBackground) {
        if (showBackground) {
            imageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.logo_background_night_theme));
        } else {
            imageView.getLayoutParams().height = context.getResources().getDimensionPixelSize(R.dimen.payu_styles_payment_method_icon_height);
            imageView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static boolean shouldShowLightBackground(@NonNull Context context, @NonNull PaymentMethodModel model) {
        return ThemeUtils.isInNightMode(context) && (model.getPaymentMethodType() == PaymentMethodType.PBL || model.getPaymentMethodType() == PaymentMethodType.PEX);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textTitleLabel = findViewById(R.id.title_label_textView);
        textTitleContent = findViewById(R.id.title_content_textView);
        textDescriptionLabel = findViewById(R.id.description_label_textView);
        textDescriptionContent = findViewById(R.id.description_content_textView);
        imageIcon = findViewById(R.id.payment_method_imageView);
        applyStyles();
    }

    private void applyStyles() {
        applicationStyleInfo = LibraryStyleProvider.fromContext(getContext());
        FontProvider fontProvider = new FontProvider(getContext());
        TextViewStyle descriptionStyle = new TextViewStyle(applicationStyleInfo.getTextStyleDescription(), fontProvider);
        TextViewStyle headlineStyle = new TextViewStyle(applicationStyleInfo.getTextStyleHeadline(), fontProvider);
        descriptionStyle.applyTo(textTitleLabel);
        descriptionStyle.applyTo(textDescriptionLabel);
        headlineStyle.applyTo(textTitleContent);
        headlineStyle.applyTo(textDescriptionContent);
        new ButtonStyle(applicationStyleInfo.getTextStyleButtonBasic(), true).applyTo(this);
    }

    public void bindModel(@NonNull PaymentMethodModel model) {
        this.methodModel = model;

        setEnabled(model.isEnabled());

        setOrHide(textTitleLabel, methodModel.getTitleLabel());
        setOrHide(textTitleContent, methodModel.getTitleContent(), model.isEnabled());
        setOrHide(textDescriptionLabel, methodModel.getDescriptionLabel());
        setOrHide(textDescriptionContent, methodModel.getDescriptionContent(), model.isEnabled());
        setOrHide(getContext(), applicationStyleInfo, imageIcon, methodModel);
    }
}
