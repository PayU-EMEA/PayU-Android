package com.payu.android.front.sdk.payment_library_core_android.configuration;

import com.payu.android.front.sdk.payment_library_core_android.util.MockStyle;
import com.payu.android.front.sdk.payment_library_core_android.util.PrivateConstructorMockStyle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class StyleClassConfigurationProviderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    StyleClassConfigurationProvider provider;
    @Mock
    ConfigurationDataProvider mockConfigProvider;

    @Before
    public void setUp() {
        openMocks(this);
        provider = new StyleClassConfigurationProvider(mockConfigProvider);
    }

    @Test
    public void shouldThrowStyleConfigurationExceptionWhenInternalClassProvided() {
        //given
        String className = MockStyle.InternalStyle.class.getName();
        when(mockConfigProvider.getStyleClassName()).thenReturn(className);
        expectedException.expect(ClassConfigurationException.class);
        expectedException.expectMessage(String.format(
                "Provided class %s cannot be instantiated. Make sure that the class you provided is proper java class in separate file.", className));

        //when
        StyleConfiguration styleConfiguration = provider.getStyleFromConfiguration();

        //then
        //Exception should be thrown
    }

    @Test
    public void shouldThrowStyleConfigurationExceptionWhenNotProperClassName() {
        //given
        String invalidClassName = "invalidClassName";
        when(mockConfigProvider.getStyleClassName()).thenReturn(invalidClassName);
        expectedException.expect(ClassConfigurationException.class);
        expectedException.expect(ClassConfigurationException.class);
        expectedException.expectMessage(
                String.format("Provided class %s cannot be found. Make sure that class has been provided with proper package name.",
                        invalidClassName));

        //when
        StyleConfiguration styleConfiguration = provider.getStyleFromConfiguration();

        //then
        //Exception should be thrown
    }

    @Test
    public void shouldThrowStyleConfigurationExceptionWhenNoProperConstructorAvailable() {
        //given
        String className = PrivateConstructorMockStyle.class.getName();
        when(mockConfigProvider.getStyleClassName()).thenReturn(className);
        expectedException.expect(ClassConfigurationException.class);
        expectedException.expectMessage(
                String.format("We cannot access constructor for the provided class: %s. Make sure that the class has public constructor.",
                        className));

        //when
        StyleConfiguration styleConfiguration = provider.getStyleFromConfiguration();

        //then
        //Exception should be thrown
    }

    @Test
    public void shouldReturnDefaultStyleConfigInstanceWhenNoConfigurationIsSet() {
        //given
        String className = DefaultStyleConfiguration.class.getName();
        when(mockConfigProvider.getStyleClassName()).thenReturn(className);

        //when
        StyleConfiguration styleConfiguration = provider.getStyleFromConfiguration();

        //then
        assertThat(styleConfiguration).isExactlyInstanceOf(DefaultStyleConfiguration.class);
    }

    @Test
    public void shouldReturnProperInstanceWhenConfigurationIsSetProperly() {
        //given
        String className = MockStyle.class.getName();
        when(mockConfigProvider.getStyleClassName()).thenReturn(className);

        //when
        StyleConfiguration styleConfiguration = provider.getStyleFromConfiguration();

        //then
        assertThat(styleConfiguration).isExactlyInstanceOf(MockStyle.class);
    }
}