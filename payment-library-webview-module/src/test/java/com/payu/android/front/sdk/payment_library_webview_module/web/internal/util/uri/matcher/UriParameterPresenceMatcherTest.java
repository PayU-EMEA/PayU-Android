package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriParameterPresenceMatcherTest {

    UriParameterPresenceMatcher objectUnderTest;

    @Test
    public void shouldReturnFalseIfParameterIsAbsent() {
        // given
        objectUnderTest = new UriParameterPresenceMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someOtherParam=1"));

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldReturnTrueIfParameterIsPresent() {
        // given
        objectUnderTest = new UriParameterPresenceMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someParam=1"));

        // then
        assertThat(matches).isTrue();
    }
}