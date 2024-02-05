package com.payu.android.front.sdk.payment_library_core_android.configuration;

import android.content.res.Resources;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core_android.configuration.resource.StringResourceIdProvider;

import java.util.HashMap;
import java.util.Map;

public class XmlConfigurationDataProvider implements ConfigurationDataProvider {
    static final String PAYU_ENVIRONMENT_CONFIG_KEY = "payu_environment";
    static final String LANGUAGE_KEY = "payu_language";
    static final String STYLE_KEY = "payu_style_class_fully_qualified_name";
    static final String PAYMENT_METHODS_KEY = "payu_payment_methods_fully_qualified_name";
    static final String PAYMENT_DYNAMIC_CARD_CONFIGURATION_KEY = "payu_payment_dynamic_card_configuration_qualified_name";
    static final String PAYMENT_CHOOSER_ADD_CARD_POSSIBLE = "payu_add_card_option";
    static final String PAYMENT_CHOOSER_PBL_POSSIBLE = "payu_pbl_option";
    static final String PAYMENT_CHOOSER_SAVE_AND_USE_CARD = "payu_save_and_use_card";
    static final String PAYMENT_CHOOSER_CARD_SCANNER = "payu_card_scanner";
    static final String PAYMENT_CHOOSER_CARD_SCANNER_SHOULD_SCAN_DATE = "payu_card_scanner_scan_date";
    static final String PAYMENT_BLIK_METHOD = "payu_blik_payment";
    private Map<String, String> localCache;
    private Resources resources;
    private StringResourceIdProvider stringResourceIdProvider;

    public XmlConfigurationDataProvider(Resources resource, StringResourceIdProvider resourceIdProvider) {
        resources = resource;
        stringResourceIdProvider = resourceIdProvider;
        localCache = new HashMap<>();
    }

    @Override
    public String getEnvironment() {
        return getEnvironmentFromXml();
    }

    @Override
    public String getPaymentMethodsClassName() {
        return getPaymentMethodsClassFromXml();
    }

    @Override
    public Optional<String> getDynamicCardConfigurationClassName() {
        return getDynamicCardConfigurationKey();
    }

    @Override
    public boolean isAddCardPossible() {
        return getBooleanFromXml(PAYMENT_CHOOSER_ADD_CARD_POSSIBLE, true);
    }

    @Override
    public boolean isPBLPossible() {
        return getBooleanFromXml(PAYMENT_CHOOSER_PBL_POSSIBLE, true);
    }

    @Override
    public boolean isSaveAndUsePossible() {
        return getBooleanFromXml(PAYMENT_CHOOSER_SAVE_AND_USE_CARD, true);
    }

    @Override
    public boolean isBlikPaymentPossible() {
        return getBooleanFromXml(PAYMENT_BLIK_METHOD, false);
    }

    @Override
    public boolean isScanCardPossible() {
        return getBooleanFromXml(PAYMENT_CHOOSER_CARD_SCANNER, false);
    }

    @Override
    public boolean isScanCardDatePossible() {
        return getBooleanFromXml(PAYMENT_CHOOSER_CARD_SCANNER_SHOULD_SCAN_DATE, true);
    }

    @Override
    public String getStyleClassName() {
        return getStyleFromXml();
    }

    private String getStyleFromXml() {
        Optional<String> className = getOptionalConfigurationValue(STYLE_KEY);
        if (!className.isPresent()) {
            String defaultClassName = DefaultStyleConfiguration.class.getName();
            localCache.put(STYLE_KEY, defaultClassName);
            return defaultClassName;
        } else {
            return className.get();
        }
    }

    private String getPaymentMethodsClassFromXml() {
        return getConfigurationValue(PAYMENT_METHODS_KEY);
    }

    @Override
    public Locale getLocale() {
        return getLanguageFromXml();
    }

    private String getEnvironmentFromXml() {
        return getConfigurationValue(PAYU_ENVIRONMENT_CONFIG_KEY);
    }

    private Optional<String> getDynamicCardConfigurationKey(){
        return getOptionalConfigurationValue(PAYMENT_DYNAMIC_CARD_CONFIGURATION_KEY);
    }
    private String getConfigurationValue(String configKey) {
        Optional<String> optionalConfigurationValue = getOptionalConfigurationValue(configKey);
        if (optionalConfigurationValue.isPresent()) {
            return optionalConfigurationValue.get();
        } else {
            throw new IllegalConfigurationException(String.format("Required xml key (%s) not found", configKey));
        }
    }

    private Optional<String> getOptionalConfigurationValue(String configKey) {
        try {
            String res = localCache.get(configKey);
            if (res == null) {
                res = resources.getString(stringResourceIdProvider.getStringIdByName(configKey));
                localCache.put(configKey, res);
            }
            return Optional.of(res);
        } catch (Resources.NotFoundException e) {
            return Optional.absent();
        }
    }

    private Optional<Boolean> getOptionalConfigurationBooleanValue(String configKey) {
        try {
            String val = localCache.get(configKey);
            Boolean res = val == null ? null : Boolean.valueOf(val);
            if (res == null) {
                res = resources.getBoolean(stringResourceIdProvider.getBooleanIdByName(configKey));
                localCache.put(configKey, res.toString());
            }
            return Optional.of(res);
        } catch (Resources.NotFoundException e) {
            return Optional.absent();
        }
    }

    private boolean getBooleanFromXml(String value, boolean defaultValue) {
        Optional<Boolean> xmlValue = getOptionalConfigurationBooleanValue(value);
        return xmlValue.or(defaultValue);
    }

    private Locale getLanguageFromXml() {
        String configurationValue = getConfigurationValue(LANGUAGE_KEY);
        return configurationValue == null ? Locale.AUTO : Enums.getIfPresent(Locale.class, configurationValue.toUpperCase()).or(Locale.AUTO);
    }
}
