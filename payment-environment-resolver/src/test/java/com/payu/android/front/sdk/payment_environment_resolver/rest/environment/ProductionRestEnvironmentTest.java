package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import org.junit.Before;
import org.junit.Test;

import okhttp3.logging.HttpLoggingInterceptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProductionRestEnvironmentTest {
    private RestEnvironment productionRestEnv;

    @Before
    public void setUp() {
        productionRestEnv = new ProductionRestEnvironment();
    }

    @Test
    public void shouldProductionEnvReturnProperEndpoints() throws Exception {
        assertThat(productionRestEnv.getCardEndpointUrl(), is("https://mobilesdk.secure.payu.com"));
        assertThat(productionRestEnv.getStaticContentUrl(), is("https://static.payu.com"));

    }

    @Test
    public void shouldProductionEnvBeProperConfigured() throws Exception {
        assertThat(productionRestEnv.getLogLevel(), is(HttpLoggingInterceptor.Level.NONE));
        assertTrue(productionRestEnv.isPinningEnabled());
        assertThat(productionRestEnv.getStringRepresentation(), is("production"));
        assertThat(productionRestEnv.getGooglePayEnvironment(), is(Environment.PROD));
    }
}