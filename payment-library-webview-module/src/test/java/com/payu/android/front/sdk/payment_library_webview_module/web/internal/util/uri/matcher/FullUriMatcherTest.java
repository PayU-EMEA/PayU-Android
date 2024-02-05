package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class FullUriMatcherTest {

    FullUriMatcher objectUnderTest;

    @Test
    public void shouldMatchEqualUris() {
        // given
        Uri uri = Uri.parse("http://www.payu.com?test=1");
        objectUnderTest = new FullUriMatcher(uri);

        // expect
        assertThat(objectUnderTest.matches(uri)).isTrue();
    }

    @Test
    public void shouldNotMatchNotEqualUris() {
        // given
        Uri uri = Uri.parse("http://www.payu.com?test=1");
        Uri differentUri = Uri.parse("http://www.payu.com?test=2");
        objectUnderTest = new FullUriMatcher(uri);

        // expect
        assertThat(objectUnderTest.matches(differentUri)).isFalse();
    }
}