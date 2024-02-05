package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;


import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriWithNoQueryUriMatcherTest {

    UriWithNoQueryUriMatcher objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new UriWithNoQueryUriMatcher();
    }

    @Test
    public void shouldReturnFalseIfQueryParamsWasPresent() {
        // expect
        assertThat(objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someParam=1"))).isFalse();
    }

    @Test
    public void shouldReturnTrueIfUrlHasNoQueryParameters() {
        // expect
        assertThat(objectUnderTest.matches(Uri.parse("https://www.payu.com/test"))).isTrue();
    }
}