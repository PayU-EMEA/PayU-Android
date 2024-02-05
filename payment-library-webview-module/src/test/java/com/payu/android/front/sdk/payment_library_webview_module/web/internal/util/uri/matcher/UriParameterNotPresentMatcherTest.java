package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriParameterNotPresentMatcherTest {

    UriParameterNotPresentMatcher objectUnderTest;

    @Test
    public void shouldReturnTrueIfParameterIsAbsent() {
        // given
        objectUnderTest = new UriParameterNotPresentMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someOtherParam=1"));

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldReturnFalseIfParameterIsPresent() {
        // given
        objectUnderTest = new UriParameterNotPresentMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someParam=1"));

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldReturnFalseIfParameterAmongOthersIsPresent() {
        // given
        objectUnderTest = new UriParameterNotPresentMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?firstParam=1&someParam=2&otherParam=3"));

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldReturnFalseIfParameterIsPresentAtTheBeginning() {
        // given
        objectUnderTest = new UriParameterNotPresentMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?someParam=1&otherParam=2"));

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldReturnFalseIfParameterIsPresentAtTheEnd() {
        // given
        objectUnderTest = new UriParameterNotPresentMatcher("someParam");

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("https://www.payu.com/test?otherParam=1&someParam=2"));

        // then
        assertThat(matches).isFalse();
    }
}
