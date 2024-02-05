package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.parser;

import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

public class CvvRequestModelSerializerTest {
    private CvvRequestModelSerializer objectUnderTest;
    @Mock
    private RedirectLinkParser redirectLinkParser;
    private String cvv = "cvv";
    private Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        openMocks(this);
        objectUnderTest = new CvvRequestModelSerializer(gson, redirectLinkParser, cvv);
    }

    @Test
    public void shouldCreateJsonStringProperly() {
        //given

        //when
        String jsonString = objectUnderTest.getFormattedJson();

        //then
        assertThat(jsonString).isEqualTo("{\"request\":\"auth.cvv\",\"data\":{\"cvv\":\"cvv\"}}");
    }
}