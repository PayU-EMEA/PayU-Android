package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import javax.net.ssl.X509TrustManager;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(RobolectricTestRunner.class)
public class SslConfigurationTest {
    List<String> acceptedHosts;
    List<SslCertificate> allowedCertificates;
    @Mock
    X509TrustManager trustManagerMock;
    SslConfiguration objectUnderTest;

    @Before
    public void setUp() {
        openMocks(this);
        acceptedHosts = newArrayList();
        allowedCertificates = newArrayList();
        objectUnderTest = new SslConfiguration(trustManagerMock, allowedCertificates, //
                acceptedHosts, null, null);
    }

    @Test
    public void shouldPassFalsePinningStateFromEnvironment() {
        // given
        allowedCertificates.clear();

        // when
        boolean pinningEnabled = objectUnderTest.isPinningEnabled();

        // then
        assertThat(pinningEnabled).isFalse();
    }

    @Test
    public void shouldPassTruePinningEnabledIfHasAllowedCertificated() {
        // given
        allowedCertificates.add(mock(SslCertificate.class));

        // when
        boolean pinningEnabled = objectUnderTest.isPinningEnabled();

        // then
        assertThat(pinningEnabled).isTrue();
    }
}
