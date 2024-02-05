package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class AnyOfListedUriMatcherTest {


    AnyOfListedUriMatcher objectUnderTest;
    @Mock
    UriMatcher mUriMatcher;
    @Mock
    UriMatcher mUriMatcher2;
    Uri sampleUri = Uri.parse("http://www.wp.pl");

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldMatchOnlyFirstMatcherIfMatches() {
        // given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(true);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(true);
        objectUnderTest = new AnyOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        // when
        objectUnderTest.matches(sampleUri);

        // then
        verify(mUriMatcher).matches(eq(sampleUri));
        verifyNoMoreInteractions(mUriMatcher);
    }

    @Test(expected = Test.None.class)/*No exception expected*/
    public void shouldNotCrashWhenProvidedMatcherIsNull() {
        // given
        objectUnderTest = new AnyOfListedUriMatcher(null);

        // when
        objectUnderTest.matches(sampleUri);

        // then
    }

    @Test
    public void shouldReturnTrueIfAllMatchersMatches() {
        // given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(true);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(true);
        objectUnderTest = new AnyOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        // when
        boolean matches = objectUnderTest.matches(sampleUri);

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldReturnTrueIfAllOneOfTheMatchersMatches() {
        // given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(true);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(false);
        objectUnderTest = new AnyOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        // when
        boolean matches = objectUnderTest.matches(sampleUri);

        // then
        assertThat(matches).isTrue();
    }
}