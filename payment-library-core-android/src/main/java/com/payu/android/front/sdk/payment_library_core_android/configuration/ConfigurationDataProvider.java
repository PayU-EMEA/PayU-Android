package com.payu.android.front.sdk.payment_library_core_android.configuration;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;

/**
 * Base interface for providing in code configuration.
 *
 * @author PayU S.A.
 */
public interface ConfigurationDataProvider {

    String getEnvironment();

    Locale getLocale();

    String getStyleClassName();

    String getPaymentMethodsClassName();

    Optional<String> getDynamicCardConfigurationClassName();

    boolean isAddCardPossible();

    boolean isPBLPossible();

    boolean isSaveAndUsePossible();

    boolean isBlikPaymentPossible();

    boolean isScanCardPossible();

    boolean isScanCardDatePossible();
}
