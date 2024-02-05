package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class AllOfListedUriMatcherTest {
    AllOfListedUriMatcher objectUnderTest;

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
    public void shouldMatchMatcherIfMatchesReturnTrue() {
        //given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(true);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(true);
        objectUnderTest = new AllOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        //when
        boolean matches = objectUnderTest.matches(sampleUri);

        //then
        verify(mUriMatcher).matches(eq(sampleUri));
        verify(mUriMatcher2).matches(eq(sampleUri));
        assertTrue(matches);
    }

    @Test
    public void shouldNotMatchMatcherFirstIfMatchesReturnFalse() {
        //given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(false);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(true);
        objectUnderTest = new AllOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        //when
        boolean matches = objectUnderTest.matches(sampleUri);

        //then
        verify(mUriMatcher).matches(eq(sampleUri));
        verifyNoInteractions(mUriMatcher2);
        assertFalse(matches);
    }


    @Test
    public void shouldNotMatchMatcherSecondIfMatchesReturnFalse() {
        //given
        when(mUriMatcher.matches(eq(sampleUri))).thenReturn(true);
        when(mUriMatcher2.matches(eq(sampleUri))).thenReturn(false);
        objectUnderTest = new AllOfListedUriMatcher(mUriMatcher, mUriMatcher2);

        //when
        boolean matches = objectUnderTest.matches(sampleUri);

        //then
        verify(mUriMatcher).matches(eq(sampleUri));
        verify(mUriMatcher2).matches(eq(sampleUri));
        assertFalse(matches);
    }


    @Test(expected = Test.None.class)/*No exception expected*/
    public void shouldNotCrashWhenProvidedMatcherIsNull() {
        // given
        objectUnderTest = new AllOfListedUriMatcher(null);

        // when
        objectUnderTest.matches(sampleUri);

        // then
    }

}