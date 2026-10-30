package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.R)
public class ExternalAppLinkOpenerTest {

    private static final String APP_LINK_URL = "https://bank.example.com/pay?id=1";

    private ExternalAppLinkOpener objectUnderTest;
    @Mock
    Context contextMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new ExternalAppLinkOpener();
    }

    @Test
    public void shouldStartNonBrowserActivityForHttpsUrl() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, APP_LINK_URL);

        // then
        assertThat(opened).isTrue();
        Intent intent = captureStartedIntent();
        assertThat(intent.getAction()).isEqualTo(Intent.ACTION_VIEW);
        assertThat(intent.getDataString()).isEqualTo(APP_LINK_URL);
        assertThat(intent.getPackage()).isNull();
    }

    @Test
    public void shouldStartIntentWithNonBrowserAndNewTaskFlags() {
        // when
        objectUnderTest.tryOpen(contextMock, APP_LINK_URL);

        // then
        Intent intent = captureStartedIntent();
        assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER).isNotZero();
        assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK).isNotZero();
    }

    @Test
    public void shouldStartOnlyBrowsableActivities() {
        // when
        objectUnderTest.tryOpen(contextMock, APP_LINK_URL);

        // then
        assertThat(captureStartedIntent().hasCategory(Intent.CATEGORY_BROWSABLE)).isTrue();
    }

    @Test
    public void shouldStartActivityForHttpUrl() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, "http://bank.example.com/pay");

        // then
        assertThat(opened).isTrue();
    }

    @Test
    public void shouldTreatSchemeCaseInsensitively() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, "HTTPS://bank.example.com/pay");

        // then
        assertThat(opened).isTrue();
    }

    @Test
    public void shouldNotOpenWhenNoNonBrowserAppHandlesUrl() {
        // given
        doThrow(new ActivityNotFoundException()).when(contextMock).startActivity(any(Intent.class));

        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, APP_LINK_URL);

        // then
        assertThat(opened).isFalse();
    }

    @Test
    public void shouldIgnoreNonWebScheme() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, "mailto:test@example.com");

        // then
        assertThat(opened).isFalse();
        verify(contextMock, never()).startActivity(any(Intent.class));
    }

    @Test
    public void shouldIgnoreCustomScheme() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, "bankapp://pay?id=1");

        // then
        assertThat(opened).isFalse();
        verify(contextMock, never()).startActivity(any(Intent.class));
    }

    @Test
    public void shouldIgnoreUrlWithoutScheme() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, "bank.example.com/pay");

        // then
        assertThat(opened).isFalse();
        verify(contextMock, never()).startActivity(any(Intent.class));
    }

    @Test
    @Config(sdk = Build.VERSION_CODES.Q)
    public void shouldNotOpenBelowApi30() {
        // when
        boolean opened = objectUnderTest.tryOpen(contextMock, APP_LINK_URL);

        // then
        assertThat(opened).isFalse();
        verify(contextMock, never()).startActivity(any(Intent.class));
    }

    private Intent captureStartedIntent() {
        ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(contextMock).startActivity(intentCaptor.capture());
        return intentCaptor.getValue();
    }
}
