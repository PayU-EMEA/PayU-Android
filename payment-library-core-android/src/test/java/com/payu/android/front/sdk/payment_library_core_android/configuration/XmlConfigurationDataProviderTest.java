package com.payu.android.front.sdk.payment_library_core_android.configuration;

import android.content.res.Resources;

import com.payu.android.front.sdk.payment_library_core.payment.configuration.Locale;
import com.payu.android.front.sdk.payment_library_core_android.configuration.resource.StringResourceIdProvider;
import com.payu.android.front.sdk.payment_library_core_android.util.MockStyle;
import com.payu.android.front.sdk.payment_library_core_android.util.StringResourceMockConfigurator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.LANGUAGE_KEY;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYMENT_BLIK_METHOD;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYMENT_CHOOSER_ADD_CARD_POSSIBLE;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYMENT_CHOOSER_CARD_SCANNER;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYMENT_CHOOSER_PBL_POSSIBLE;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYMENT_CHOOSER_SAVE_AND_USE_CARD;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.PAYU_ENVIRONMENT_CONFIG_KEY;
import static com.payu.android.front.sdk.payment_library_core_android.configuration.XmlConfigurationDataProvider.STYLE_KEY;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class XmlConfigurationDataProviderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    StringResourceMockConfigurator resourceMockConfigurator;
    @Mock
    Resources resourcesMock;
    @Mock
    StringResourceIdProvider idProviderMock;
    XmlConfigurationDataProvider objectUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new XmlConfigurationDataProvider(resourcesMock, idProviderMock);
        resourceMockConfigurator = new StringResourceMockConfigurator(resourcesMock, idProviderMock);
    }

    @Test
    public void shouldConfigureWithValidEnvironment() {
        // given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(PAYU_ENVIRONMENT_CONFIG_KEY, "production");

        // when
        String environment = objectUnderTest.getEnvironment();

        // then
        assertThat(environment).isEqualTo("production");
    }

    @Test
    public void shouldReturnAutoLocaleIfConfigurationIsInvalid() {
        // given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(LANGUAGE_KEY, "invalid_language");

        // when
        Locale locale = objectUnderTest.getLocale();

        // then
        assertThat(locale).isSameAs(Locale.AUTO);
    }

    @Test
    public void shouldReturnGermanLocaleIfGermanLanguageIsProvided() {
        //given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(LANGUAGE_KEY, "german");
        //when
        Locale locale = objectUnderTest.getLocale();
        //then
        assertThat(locale).isSameAs(Locale.GERMAN);
    }

    @Test
    public void shouldReturnPolishLocaleIfPolishLanguageIsProvided() {
        // given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(LANGUAGE_KEY, "polish");

        // when
        Locale locale = objectUnderTest.getLocale();

        // then
        assertThat(locale).isSameAs(Locale.POLISH);
    }

    @Test
    public void shouldReturnCzechLocaleIfCzechLanguageIsProvided() {
        // given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(LANGUAGE_KEY, "czech");

        // when
        Locale locale = objectUnderTest.getLocale();

        // then
        assertThat(locale).isSameAs(Locale.CZECH);
    }

    @Test
    public void shouldReturnDefaultStyleConfigurationWhenNoneIsProvided() {
        //given
        resourceMockConfigurator.whenKeyRequestedThrowNotFound(STYLE_KEY);

        //when
        String styleConfiguration = objectUnderTest.getStyleClassName();

        //then
        assertThat(styleConfiguration).isSameAs(DefaultStyleConfiguration.class.getName());
    }

    @Test
    public void shouldReturnProvidedClassInstance() {
        //given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(STYLE_KEY, MockStyle.class.getName());

        //when
        String styleConfiguration = objectUnderTest.getStyleClassName();

        //then
        assertThat(styleConfiguration).isSameAs(MockStyle.class.getName());
    }

    @Test
    public void shouldReturnCachedLocaleWhenCalledSecondTime() {
        //given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(LANGUAGE_KEY, "polish");

        //when
        Locale locale = objectUnderTest.getLocale();
        locale = objectUnderTest.getLocale();

        //then
        verify(resourcesMock, times(1)).getString(anyInt());
    }

    @Test
    public void shouldReturnCachedEnvironmentWhenCalledSecondTime() {
        //given
        resourceMockConfigurator.whenStringForKeyIsRequestedReturn(PAYU_ENVIRONMENT_CONFIG_KEY, "sandbox");

        //when
        String ignored = objectUnderTest.getEnvironment();
        ignored = objectUnderTest.getEnvironment();

        //then
        verify(resourcesMock, times(1)).getString(anyInt());
    }

    @Test
    public void shouldReturnCachedDefaultStyleConfigWhenCalledSecondTime() {
        //given
        resourceMockConfigurator.whenKeyRequestedThrowNotFound(STYLE_KEY);

        //when
        String styleConfiguration = objectUnderTest.getStyleClassName();
        styleConfiguration = objectUnderTest.getStyleClassName();

        //then
        verify(resourcesMock, times(1)).getString(anyInt());
        assertThat(styleConfiguration).isSameAs(DefaultStyleConfiguration.class.getName());
    }

    @Test
    public void shouldConfigureAddCardActivityWithPositiveValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_ADD_CARD_POSSIBLE, true);

        // when
        Boolean value = objectUnderTest.isAddCardPossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldNotConfigureAddCardActivityWithNegativeValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_ADD_CARD_POSSIBLE, false);

        // when
        Boolean value = objectUnderTest.isAddCardPossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldConfigureAddCardActivityWithNoValue() {
        // given
        resourceMockConfigurator.whenKeyRequestedThenReturnTrue(PAYMENT_CHOOSER_ADD_CARD_POSSIBLE);

        // when
        Boolean value = objectUnderTest.isAddCardPossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldConfigurePBLActivityWithPositiveValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_PBL_POSSIBLE, true);

        // when
        boolean value = objectUnderTest.isPBLPossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldNotConfigurePBLActivityWithNegativeValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_PBL_POSSIBLE, false);

        // when
        boolean value = objectUnderTest.isPBLPossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldConfigurePBLActivityWithNoValue() {
        // given
        resourceMockConfigurator.whenKeyRequestedThenReturnTrue(PAYMENT_CHOOSER_PBL_POSSIBLE);

        // when
        boolean value = objectUnderTest.isPBLPossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldConfigureSaveAndUseWithPositiveValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_SAVE_AND_USE_CARD, true);

        // when
        Boolean value = objectUnderTest.isSaveAndUsePossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldNotConfigureSaveAndUseWithNegativeValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_SAVE_AND_USE_CARD, false);

        // when
        Boolean value = objectUnderTest.isSaveAndUsePossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldConfigureSaveAndUseWithNoValue() {
        // given
        resourceMockConfigurator.whenKeyRequestedThenReturnTrue(PAYMENT_CHOOSER_SAVE_AND_USE_CARD);

        // when
        Boolean value = objectUnderTest.isSaveAndUsePossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldReturnCacheSaveAndUseWhenCalledSecondTime() {
        //given
        resourceMockConfigurator.whenKeyRequestedThenReturnTrue(PAYMENT_CHOOSER_SAVE_AND_USE_CARD);
        Boolean value = objectUnderTest.isSaveAndUsePossible();
        //when
        Boolean value2 = objectUnderTest.isSaveAndUsePossible();

        //then
        verify(resourcesMock, times(1)).getBoolean(anyInt());
        assertThat(value).isSameAs(value2);
    }

    @Test
    public void shouldReturnCacheAddCardActivityWhenCalledSecondTime() {
        //given
        resourceMockConfigurator.whenKeyRequestedThenReturnTrue(PAYMENT_CHOOSER_ADD_CARD_POSSIBLE);
        Boolean value = objectUnderTest.isAddCardPossible();
        //when
        Boolean value2 = objectUnderTest.isAddCardPossible();

        //then
        verify(resourcesMock, times(1)).getBoolean(anyInt());
        assertThat(value).isSameAs(value2);
    }


    @Test
    public void shouldBlikPaymentBeAccessibleWithPositiveValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_BLIK_METHOD, true);

        // when
        Boolean value = objectUnderTest.isBlikPaymentPossible();

        // then
        assertTrue(value);
    }

    @Test
    public void shouldNotBlikPaymentBeAccessibleWithNegativeValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_BLIK_METHOD, false);

        // when
        Boolean value = objectUnderTest.isBlikPaymentPossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldNotConfigureBlikPaymentWithNoValue() {
        // given
        resourceMockConfigurator.whenKeyRequestedThenReturnFalse(PAYMENT_BLIK_METHOD);

        // when
        Boolean value = objectUnderTest.isBlikPaymentPossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldNotConfigureCardScannerWithNoValue() {
        //given
        resourceMockConfigurator.whenKeyRequestedThenReturnFalse(PAYMENT_CHOOSER_CARD_SCANNER);

        //when
        Boolean value = objectUnderTest.isScanCardPossible();

        //then
        assertFalse(value);
    }

    @Test
    public void shouldNotConfigureCardScannerWithNegativeValue() {
        // given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_CARD_SCANNER, false);

        // when
        Boolean value = objectUnderTest.isScanCardPossible();

        // then
        assertFalse(value);
    }

    @Test
    public void shouldConfigureCardScannerWithPositiveValue() {
        //given
        resourceMockConfigurator.whenBoolForKeyIsRequestedReturn(PAYMENT_CHOOSER_CARD_SCANNER, true);

        //when
        Boolean value = objectUnderTest.isScanCardPossible();

        //then
        assertTrue(value);
    }
}
