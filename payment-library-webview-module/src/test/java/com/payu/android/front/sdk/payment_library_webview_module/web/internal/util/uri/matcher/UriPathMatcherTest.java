package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriPathMatcherTest {

    @Test
    public void shouldMatchPathWithParameters() {
        // given
        UriPathMatcher objectUnderTest = new UriPathMatcher("/pl/standard/co/summary");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("http://secure.avo.payudc.net/pl/standard/co/summary;"
                + "jsessionid=A1D39BB47384018EFED3212F0473D9BD.01?oauth_token=c0392cd1"));

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldMatchPathWithoutParameters() {
        // given
        UriPathMatcher objectUnderTest = new UriPathMatcher("/pl/standard/co/summary");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse(
                "http://secure.avo.payudc.net/pl/standard/co/summary?oauth_token=c0392cd1"));

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldNotMatchPathThatIsContainingOnlyPartOfThePattern() {
        // given
        UriPathMatcher objectUnderTest = new UriPathMatcher("/pl/standard/co/summary");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse(
                "http://secure.avo.payudc.net/pl/standard/co/summary/return?oauth_token=c0392cd1"));

        // then
        assertThat(matches).isFalse();
    }
}