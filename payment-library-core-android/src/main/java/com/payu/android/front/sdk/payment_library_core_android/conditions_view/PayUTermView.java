package com.payu.android.front.sdk.payment_library_core_android.conditions_view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.R;
import com.payu.android.front.sdk.payment_library_core_android.util.PDFUtils;

public class PayUTermView extends LinearLayout {
    private Translation translation;
    private TextView textPaymentInfo;

    public PayUTermView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        translation = TranslationFactory.getInstance();
        inflate(context, R.layout.payu_view_payu_terms, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textPaymentInfo = findViewById(R.id.payment_information_textView);
        setOrientation(HORIZONTAL);
        setBackgroundResource(R.color.payu_styles_color_secondary_background);
        int dimension = (int) getResources().getDimension(R.dimen.payu_styles_padding_xlarge);
        setPadding(dimension, dimension, dimension, dimension);
        setupText();
    }

    private void setupText() {
        final String translate = translation.translate(TranslationKey.SUPPORT_PAYMENT_INFORMATION);
        textPaymentInfo.setText(Html.fromHtml(translate));
        textPaymentInfo.setLinkTextColor(ContextCompat.getColor(getContext(), R.color.payu_styles_color_secondary_link));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PDFUtils.openPdfFileInExternalSourceWithType(getContext(), translation.translate(TranslationKey.COMPLIANCE_URL));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getContext(), TranslationFactory.getInstance().translate(TranslationKey.CANNOT_SHOW_COMPLIANCE_TEXT), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
