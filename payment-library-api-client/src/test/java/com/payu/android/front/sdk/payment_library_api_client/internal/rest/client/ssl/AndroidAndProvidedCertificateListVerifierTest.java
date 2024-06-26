package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static java.util.Arrays.asList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.X509TrustManager;

@RunWith(RobolectricTestRunner.class)
public class AndroidAndProvidedCertificateListVerifierTest {

    AndroidAndProvidedCertificateListVerifier objectUnderTest;
    @Mock
    X509Certificate certificateMock;
    @Mock
    X509Certificate caCertificateMock;
    @Mock
    X509TrustManager trustManagerMock;
    @Mock
    PublicKey publicKeyMock;
    @Mock
    PublicKey caPublicKeyMock;
    List<SslCertificate> fakeCertificates;

    @Before
    public void setUp() {
        openMocks(this);
        fakeCertificates = newArrayList();
        objectUnderTest = new AndroidAndProvidedCertificateListVerifier(trustManagerMock, fakeCertificates);
        configureFirstCertificateInChain("5678", "otherThanOther");
        configureSecondCertificateInChain("1234", "anyOther");
    }

    @Test
    public void shouldAcceptTrustedCertificateThatMatchesFirstCertificateInChain() throws CertificateException {
        // given
        String knownPublicKeyHash = "knownPublicKeyHash";
        String serialNumber = "36122296c5e338a520a1d25f4cd70954";
        configureFirstCertificateInChain(serialNumber, knownPublicKeyHash);

        fakeCertificates.add(new SslCertificate(knownPublicKeyHash, serialNumber));
        objectUnderTest = new AndroidAndProvidedCertificateListVerifier(trustManagerMock, fakeCertificates);

        // when + then
        assertThatNoException().isThrownBy(() -> objectUnderTest.checkServerTrusted(asList(certificateMock, caCertificateMock).toArray(new X509Certificate[2]), null));
    }

    @Test
    public void shouldAcceptTrustedCertificateThatMatchesSecondCertificateInChain() {
        // given
        String knownPublicKeyHash = "knownPublicKeyHash";
        String serialNumber = "36122296c5e338a520a1d25f4cd70954";
        configureSecondCertificateInChain(serialNumber, knownPublicKeyHash);

        fakeCertificates.add(new SslCertificate(knownPublicKeyHash, serialNumber));
        objectUnderTest = new AndroidAndProvidedCertificateListVerifier(trustManagerMock, fakeCertificates);

        // when + then
        assertThatNoException().isThrownBy(() -> objectUnderTest.checkServerTrusted(asList(certificateMock, caCertificateMock).toArray(new X509Certificate[2]), null));
    }


    @Test
    public void shouldCheckClientPositivelyWithAndroidVerifier() {
        // when + then
        assertThatNoException().isThrownBy(() -> objectUnderTest.checkClientTrusted(null, null));
    }

    @Test
    public void shouldGetNullIssuersIfAndroidTrustManagerIsNotSet() {
        // given
        objectUnderTest = new AndroidAndProvidedCertificateListVerifier(null, fakeCertificates);

        // when
        X509Certificate[] acceptedIssuers = objectUnderTest.getAcceptedIssuers();

        // then
        assertThat(acceptedIssuers).isNull();
    }

    @Test
    public void shouldNotThrowCertificateExceptionIfAndroidVerifierIsNotAvailable() {
        // given
        objectUnderTest = new AndroidAndProvidedCertificateListVerifier(null, fakeCertificates);

        // when + then
        assertThatNoException().isThrownBy(() -> objectUnderTest.checkClientTrusted(null, null));
    }

    @Test(expected = CertificateException.class)
    public void shouldRejectUntrustedCertificatesIfNoCertificatesConfigured() throws CertificateException {
        // when
        objectUnderTest.checkServerTrusted(asList(certificateMock, caCertificateMock).toArray(new
                X509Certificate[2]), null);
    }

    @Test(expected = CertificateException.class)
    public void shouldRejectUntrustedCertificatesIfUnknownCertificatesConfigured() throws CertificateException {
        // given
        fakeCertificates.add(new SslCertificate("anypublickeyhash", "serialnumber"));

        // when
        objectUnderTest.checkServerTrusted(asList(certificateMock, caCertificateMock).toArray(new
                X509Certificate[2]), null);
    }

    private void configureCertificateAndPublicKeyMocks(String firstCertificateSerialNumber, String knownPublicKeyHash,
                                                       X509Certificate certificateMock, PublicKey keyMock) {
        when(certificateMock.getSerialNumber()).thenReturn(new BigInteger(firstCertificateSerialNumber, 16));
        when(certificateMock.getPublicKey()).thenReturn(keyMock);
        when(keyMock.getEncoded()).thenReturn(knownPublicKeyHash.getBytes());
    }

    private void configureFirstCertificateInChain(String firstCertificateSerialNumber, String knownPublicKeyHash) {
        configureCertificateAndPublicKeyMocks(firstCertificateSerialNumber, knownPublicKeyHash, certificateMock, publicKeyMock);
    }

    private void configureSecondCertificateInChain(String firstCertificateSerial, String knownPublicKeyHash) {
        configureCertificateAndPublicKeyMocks(firstCertificateSerial, knownPublicKeyHash, caCertificateMock, caPublicKeyMock);
    }
}