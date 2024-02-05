package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriParameterValueMatcherTest {

    UriParameterValueMatcher objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new UriParameterValueMatcher(new UriParameter("parameter", "correct"));
    }

    @Test
    public void shouldMatchUriContainingParameterWithExpectedValue() {
        // given
        Uri correctUri = Uri.parse("http://url.at/test?parameter=correct");

        // when
        boolean matches = objectUnderTest.matches(correctUri);

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldNotMatchUriContainingParameterWithDifferentValue() {
        // given
        Uri incorrectUri = Uri.parse("http://url.at/test?parameter=notCorrect");

        // when
        boolean matches = objectUnderTest.matches(incorrectUri);

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldNotMatchUriWithoutExpectedParameter() {
        // given
        Uri incorrectUri = Uri.parse("http://url.at/test?differentParameter=correct");

        // when
        boolean matches = objectUnderTest.matches(incorrectUri);

        // then
        assertThat(matches).isFalse();
    }
}