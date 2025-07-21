package com.payu.android.front.sdk.payment_environment_resolver.rest.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.model.google_pay.Environment;

import org.junit.Before;
import org.junit.Test;

import okhttp3.logging.HttpLoggingInterceptor;

public class SandboxRestEnvironmentTest {

    private RestEnvironment objectUnderTests;

    @Before
    public void setUp() {
        objectUnderTests = new SandboxRestEnvironment();
    }


    @Test
    public void shouldSandboxEnvReturnProperEndpoints() {
        assertEquals("https://secure.snd.payu.com", objectUnderTests.getCardEndpointUrl());
        assertEquals("https://static.payu.com", objectUnderTests.getStaticContentUrl());
    }


    @Test
    public void shouldSandboxEnvBeProperConfigured() {
        assertEquals(HttpLoggingInterceptor.Level.BODY, objectUnderTests.getLogLevel());
        assertTrue(objectUnderTests.isPinningEnabled());
        assertEquals("sandbox", objectUnderTests.getStringRepresentation());
        assertEquals(Environment.TEST, objectUnderTests.getGooglePayEnvironment());
    }
}
