package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.verify;

import android.view.View;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class WebAuthorizationViewChromeClientTest {

    WebAuthorizationViewChromeClient objectUnderTest;
    @Mock
    ProgressBar progressBar;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new WebAuthorizationViewChromeClient(progressBar, null);
    }

    @Test
    public void shouldMakeProgressVisibleIfProgressIsActive() {
        // given
        int progress = 50;

        // when
        objectUnderTest.onProgressChanged(null, progress);

        // then
        verify(progressBar).setVisibility(View.VISIBLE);
    }

    @Test
    public void shouldReactForLostViewReference() {
        // given
        objectUnderTest = new WebAuthorizationViewChromeClient(null, null);

        // when + then
        assertThatNoException().isThrownBy(() -> objectUnderTest.onProgressChanged(null, 64));
    }

    @Test
    public void shouldSetSelectedProgress() {
        // given
        int progress = 50;

        // when
        objectUnderTest.onProgressChanged(null, progress);

        // then
        verify(progressBar).setProgress(progress);
    }

    @Test
    public void shouldShouldHideProgressWhenFinished() {
        // given
        int progress = 100;

        // when
        objectUnderTest.onProgressChanged(null, progress);

        // then
        verify(progressBar).setVisibility(View.GONE);
    }
}
