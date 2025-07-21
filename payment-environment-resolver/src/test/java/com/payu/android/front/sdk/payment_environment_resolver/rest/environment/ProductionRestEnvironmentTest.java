package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import org.junit.Before;
import org.junit.Test;

import okhttp3.logging.HttpLoggingInterceptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProductionRestEnvironmentTest {
    private RestEnvironment productionRestEnv;

    @Before
    public void setUp() {
        productionRestEnv = new ProductionRestEnvironment();
    }

    @Test
    public void shouldProductionEnvReturnProperEndpoints() {
        assertEquals("https://mobilesdk.secure.payu.com", productionRestEnv.getCardEndpointUrl());
        assertEquals("https://static.payu.com", productionRestEnv.getStaticContentUrl());
    }

    @Test
    public void shouldProductionEnvBeProperConfigured() {
        assertEquals(HttpLoggingInterceptor.Level.NONE, productionRestEnv.getLogLevel());
        assertTrue(productionRestEnv.isPinningEnabled());
        assertEquals("production", productionRestEnv.getStringRepresentation());
        assertEquals(Environment.PROD, productionRestEnv.getGooglePayEnvironment());
    }
}