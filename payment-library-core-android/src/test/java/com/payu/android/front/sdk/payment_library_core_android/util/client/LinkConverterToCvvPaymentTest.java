package com.payu.android.front.sdk.payment_library_core_android.util.client;


import com.payu.android.front.sdk.payment_library_api_client.internal.rest.environment.RestEnvironment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class LinkConverterToCvvPaymentTest {
    private final static String BASE_TEST_URL = "http://test.payu.com/card";

    LinkConverterToCvvPayment objectUnderTest;
    @Mock
    RestEnvironment restEnvironment;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(restEnvironment.getCardEndpointUrl()).thenReturn(BASE_TEST_URL);
        objectUnderTest = new LinkConverterToCvvPayment(restEnvironment);
    }

    @Test
    public void shouldCreateCpmUrl() {
        // given
        String refReqId = "refReqId=123";

        // when
        String threeDsUrl = objectUnderTest.convert("http://some.three.ds/url?" + refReqId);

        // then
        assertThat(threeDsUrl).isEqualTo(BASE_TEST_URL + "/api/v2/token/token.json?" + refReqId);
    }
}