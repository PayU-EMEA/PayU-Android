package com.payu.android.front.sdk.payment_library_api_client.internal.rest.client.ssl;

import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class AndroidTrustManagerProviderTest {

    AndroidTrustManagerProvider objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = spy(new AndroidTrustManagerProvider());
    }

    @Test
    public void shouldReturnAbsentInCaseOfSecurityError() throws KeyStoreException, NoSuchAlgorithmException {
        // given
        when(objectUnderTest.getAndroidTrustManagers(null)).thenThrow(new NoSuchAlgorithmException());

        // when
        Optional<X509TrustManager> androidTrustManager = objectUnderTest.create(null);

        // then
        assertThat(androidTrustManager.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnAbsentInCaseOfX509TrustManagerIsNotAvailable() throws KeyStoreException, NoSuchAlgorithmException {
        // given
        when(objectUnderTest.getAndroidTrustManagers(null))
                .thenReturn(Collections.singletonList(mock(TrustManager.class)).toArray(new TrustManager[1]));

        // when
        Optional<X509TrustManager> androidTrustManager = objectUnderTest.create(null);

        // then
        assertThat(androidTrustManager.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnAndroidsTrustManagerX509TrustManager() {
        // when
        Optional<X509TrustManager> androidTrustManager = objectUnderTest.create(null);

        // then
        assertThat(androidTrustManager.get()).isInstanceOf(X509TrustManager.class);
    }
}