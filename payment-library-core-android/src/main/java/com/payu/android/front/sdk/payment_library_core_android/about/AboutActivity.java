package com.payu.android.front.sdk.payment_library_core_android.about;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_core_android.BuildConfig;
import com.payu.android.front.sdk.payment_library_core_android.R;
import com.payu.android.front.sdk.payment_library_core_android.base.BaseActivity;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.LibraryStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.LibraryStyleProvider;

public class AboutActivity extends BaseActivity {
    private View mainView;
    private ImageView imageIcon;
    private TextView labelPublisher;
    private TextView labelVersion;
    private TextView textVersion;
    private TextView textPublisher;
    private TextView textTitle;
    private Translation translations;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translations = TranslationFactory.getInstance();
        setupTranslations();
        applyStyles();
        setupData();
    }

    protected Toolbar provideToolbar() {
        return toolbar;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.payu_activity_about;
    }

    private void setupData() {
        textVersion.setText(BuildConfig.APP_VERSION);
        textTitle.setText(translations.translate(TranslationKey.INFORMATIONS));
        imageIcon.setImageResource(R.drawable.payu_logo);
    }

    @Override
    protected void bindViews() {
        mainView = findViewById(R.id.main_view);
        imageIcon = findViewById(R.id.logo_imageView);
        labelPublisher = findViewById(R.id.label_publisher_textView);
        labelVersion = findViewById(R.id.label_version_textView);
        textVersion = findViewById(R.id.version_textView);
        toolbar = findViewById(R.id.payu_toolbar);
        textTitle = findViewById(R.id.title_payu_toolbar_textView);
        textPublisher = findViewById(R.id.publisher_textView);
    }

    private void applyStyles() {
        LibraryStyleInfo applicationStyleInfo = LibraryStyleProvider.fromContext(this);
        mainView.setBackgroundColor(applicationStyleInfo.getBackgroundColor());
        createStyleFromInfo(applicationStyleInfo.getTextStyleDescription()).applyTo(labelPublisher);
        createStyleFromInfo(applicationStyleInfo.getTextStyleDescription()).applyTo(labelVersion);
        createStyleFromInfo(applicationStyleInfo.getTextStyleText()).applyTo(textPublisher);
        createStyleFromInfo(applicationStyleInfo.getTextStyleText()).applyTo(textVersion);
        createStyleFromInfo(applicationStyleInfo.getTextStyleTitle()).applyTo(textTitle);
        findViewById(R.id.separator_view1).setBackgroundColor(applicationStyleInfo.getSeparatorColor());
        findViewById(R.id.separator_view2).setBackgroundColor(applicationStyleInfo.getSeparatorColor());
        int windowContentPadding = (int) applicationStyleInfo.getWindowContentPadding();
        findViewById(R.id.body_linearLayout).setPadding(windowContentPadding, windowContentPadding, windowContentPadding, windowContentPadding);
        findViewById(R.id.body_linearLayout).setBackgroundColor(applicationStyleInfo.getBackgroundColor());
        toolbar.setBackgroundColor(applicationStyleInfo.getToolbarColor());
    }

    private void setupTranslations() {
        labelPublisher.setText(translations.translate(TranslationKey.PUBLISHER));
        textPublisher.setText(translations.translate(TranslationKey.PAYU_COMPANY_NAME));
        labelVersion.setText(translations.translate(TranslationKey.APPLICATION_VERSION));
    }
}
