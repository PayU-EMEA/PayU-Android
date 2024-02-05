package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import org.junit.Before;
import org.junit.Test;

import okhttp3.logging.HttpLoggingInterceptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SandboxRestEnvironmentTest {

    private RestEnvironment objectUnderTests;


    @Before
    public void setUp() {
        objectUnderTests = new SandboxRestEnvironment();
    }


    @Test
    public void shouldSandboxEnvReturnProperEndpoints() throws Exception {
        assertThat(objectUnderTests.getCardEndpointUrl(), is("https://secure.snd.payu.com"));
        assertThat(objectUnderTests.getStaticContentUrl(), is("https://static.payu.com"));
    }


    @Test
    public void shouldSandboxEnvBeProperConfigured() throws Exception {
        assertThat(objectUnderTests.getLogLevel(), is(HttpLoggingInterceptor.Level.BODY));
        assertTrue(objectUnderTests.isPinningEnabled());
        assertThat(objectUnderTests.getStringRepresentation(), is("sandbox"));
        assertThat(objectUnderTests.getGooglePayEnvironment(), is(Environment.TEST));
    }
}
