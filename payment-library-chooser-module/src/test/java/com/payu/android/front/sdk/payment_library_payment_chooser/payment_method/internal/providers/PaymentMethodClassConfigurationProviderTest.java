package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers;

import android.app.Application;

import com.payu.android.front.sdk.payment_library_core_android.configuration.ClassConfigurationException;
import com.payu.android.front.sdk.payment_library_core_android.configuration.ConfigurationDataProvider;
import com.payu.android.front.sdk.payment_library_payment_chooser.utils.BadConstructorPaymentMethodActions;
import com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodActionsMock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PaymentMethodClassConfigurationProviderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private PaymentMethodClassConfigurationProvider objectUnderTest;
    @Mock
    private ConfigurationDataProvider mockConfigProvider;
    @Mock
    private Application application;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new PaymentMethodClassConfigurationProvider(mockConfigProvider, application);
    }

    @Test
    public void shouldThrowConfigurationExceptionWhenConstructorIsNotMatching() {
        //given
        String className = BadConstructorPaymentMethodActions.class.getName();
        when(mockConfigProvider.getPaymentMethodsClassName()).thenReturn(className);
        expectedException.expect(ClassConfigurationException.class);
        expectedException.expectMessage(String.format(
                "Provided class %s cannot be instantiated. Make sure it has constructor matching super class.", className));

        //when
        PaymentMethodActions actions = objectUnderTest.getPaymentMethodsActions();

        //then
        //Exception should be thrown
    }

    @Test
    public void shouldCreateProperInstanceWithMatchingConstructor() {
        //given
        String className = PaymentMethodActionsMock.class.getName();
        when(mockConfigProvider.getPaymentMethodsClassName()).thenReturn(className);

        //when
        PaymentMethodActions actions = objectUnderTest.getPaymentMethodsActions();

        //then
        assertThat(actions).isExactlyInstanceOf(PaymentMethodActionsMock.class);
    }
}