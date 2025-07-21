package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import javax.net.ssl.X509TrustManager;

@RunWith(RobolectricTestRunner.class)
public class SslConfigurationTest {
    List<String> acceptedHosts = newArrayList();

    @Mock
    X509TrustManager trustManagerMock;
    SslConfiguration objectUnderTest;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new SslConfiguration(trustManagerMock, acceptedHosts);
    }

    @Test
    public void shouldPassFalsePinningStateFromEnvironment() {
        // given
        when(trustManagerMock.getAcceptedIssuers()).thenReturn(new java.security.cert.X509Certificate[0]);

        // when
        boolean pinningEnabled = objectUnderTest.isPinningEnabled();

        // then
        assertThat(pinningEnabled).isFalse();
    }

    @Test
    public void shouldPassTruePinningEnabledIfHasAllowedCertificated() {
        // given
        when(trustManagerMock.getAcceptedIssuers()).thenReturn(new java.security.cert.X509Certificate[1]);

        // when
        boolean pinningEnabled = objectUnderTest.isPinningEnabled();

        // then
        assertThat(pinningEnabled).isTrue();
    }
}
