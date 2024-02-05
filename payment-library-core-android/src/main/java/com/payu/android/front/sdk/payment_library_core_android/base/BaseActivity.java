package com.payu.android.front.sdk.payment_library_core_android.base;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationFactory;
import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;
import com.payu.android.front.sdk.payment_library_core_android.R;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationFactory;
import com.payu.android.front.sdk.payment_library_core_android.configuration.StyleClassConfigurationProvider;
import com.payu.android.front.sdk.payment_library_core_android.styles.TextViewStyle;
import com.payu.android.front.sdk.payment_library_core_android.styles.model.TextStyleInfo;
import com.payu.android.front.sdk.payment_library_core_android.styles.providers.FontProvider;

import static com.payu.android.front.sdk.payment_library_core_android.configuration.StyleConfiguration.NO_ICON;

public abstract class BaseActivity extends AppCompatActivity {
    protected Translation translation;
    private Toolbar appToolbar;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getTheme().applyStyle(R.style.Theme_PayU_Fronts_NoActionBar, true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        translation = TranslationFactory.getInstance();
        setContentView(getLayoutResource());
        bindViews();
        setupToolbar();
    }

    private void setupToolbar() {
        appToolbar = provideToolbar();
        setSupportActionBar(appToolbar);
        loadLogoFromDrawable();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected abstract Toolbar provideToolbar();

    protected abstract void bindViews();

    protected abstract int getLayoutResource();

    private void loadLogoFromDrawable() {
        if (appToolbar != null) {
            ImageView toolbarLogo = appToolbar.findViewById(R.id.icon_payu_toolbar);
            StyleClassConfigurationProvider styleClassConfigurationProvider = StyleClassConfigurationFactory.createStyleClassProvider(this);
            int res = styleClassConfigurationProvider.getStyleFromConfiguration().payuLibraryIcon();
            toolbarLogo.setImageResource(getLogoResource(res));
        }
    }

    private int getLogoResource(int res) {
        return res != NO_ICON
                ? res
                : R.drawable.payu_logo;
    }

    protected RestEnvironment getCurrentRestEnvironment(Context activityContext) {
        return ConfigurationEnvironmentProvider.provideEnvironment(activityContext);
    }

    @NonNull
    protected TextViewStyle createStyleFromInfo(TextStyleInfo styleInfo) {
        return new TextViewStyle(styleInfo, new FontProvider(this));
    }
}
