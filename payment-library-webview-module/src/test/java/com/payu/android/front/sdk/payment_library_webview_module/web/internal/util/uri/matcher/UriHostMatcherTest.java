package com.payu.android.front.sdk.payment_library_webview_module.web.internal.util.uri.matcher;

import android.net.Uri;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UriHostMatcherTest {

    String HTTP_DOMAIN_NAME = "http://domain.name/resource.html";
    UriHostMatcher objectUnderTest;

    @Test
    public void shouldMatchIfAtLeastOneIsFound() {
        // given
        objectUnderTest = new UriHostMatcher(Lists.newArrayList("domain_other.name", "domain_other2.name"));

        // when
        boolean matches = objectUnderTest.matches(Uri.parse("http://domain_other2.name/resource.html"));

        // then
        assertThat(matches).isTrue();
    }

    @Test
    public void shouldNotMatchIfHostArrayIsEmpty() {
        // given
        objectUnderTest = new UriHostMatcher(Collections.<String>emptyList());

        // when
        boolean matches = objectUnderTest.matches(Uri.parse(HTTP_DOMAIN_NAME));

        // then
        assertThat(matches).isFalse();
    }

    @Test
    public void shouldNotMatchIfHostNameNotFound() {
        // given
        objectUnderTest = new UriHostMatcher(Lists.newArrayList("domain_other.name", "domain_other2.name"));

        // when
        boolean matches = objectUnderTest.matches(Uri.parse(HTTP_DOMAIN_NAME));

        // then
        assertThat(matches).isFalse();
    }
}