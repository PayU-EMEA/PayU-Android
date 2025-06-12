package com.payu.android.front.sdk.payment_library_webview_module.web.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;

import com.payu.android.front.sdk.payment_library_core_android.ConfigurationEnvironmentProvider;
import com.payu.android.front.sdk.payment_environment_resolver.rest.environment.SandboxRestEnvironment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 30)
public class QrCodeTest {
    private Activity spyActivity;

    QrCode objectUnderTest;

    @Mock
    WebView mockWebView;

    private MockedStatic<ConfigurationEnvironmentProvider> mockedStatic;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Activity realActivity = Robolectric.buildActivity(Activity.class)
                .create().start().resume().get();
        spyActivity = Mockito.spy(realActivity);

        mockedStatic = mockStatic(ConfigurationEnvironmentProvider.class);
        mockedStatic.when(() -> ConfigurationEnvironmentProvider.provideEnvironment(any()))
                .thenReturn(new SandboxRestEnvironment());
        objectUnderTest = new QrCode(spyActivity, mockWebView);
    }


    @After
    public void tearDown() {
        mockedStatic.close();
    }

    @Test
    public void shouldShareQrCode() {
        // given
        ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
        when(mockWebView.getUrl())
                .thenReturn("https://merch-test.snd.payu.com/?orderId=ORDER_IR");

        // when
        objectUnderTest.share("fakeBase64String", "testFile.png");

        // then
        verify(spyActivity).startActivity(intentCaptor.capture());

        Intent chooserIntent = intentCaptor.getValue();
        assertEquals(Intent.ACTION_CHOOSER, chooserIntent.getAction());

        Intent innerIntent = chooserIntent.getParcelableExtra(Intent.EXTRA_INTENT);

        assertThat(innerIntent.getAction()).isEqualTo(Intent.ACTION_SEND);
        assertThat(innerIntent.getType()).isEqualTo("image/png");
    }

    @Test
    public void shouldNotShareQrCodeWhenNoMatchUrl() {
        // given
        when(mockWebView.getUrl())
                .thenReturn("https://test.payu.com/?orderId=ORDER_IR");

        // when
        objectUnderTest.share("fakeBase64String", "testFile.png");

        // then
        verify(spyActivity, after(500).never()).startActivity(any());
    }

    @Test
    public void shouldNotShareQrCodeWhenNoImage() {
        // given
        when(mockWebView.getUrl())
                .thenReturn("https://merch-test.snd.payu.com/?orderId=ORDER_IR");

        // when
        objectUnderTest.share(null, "testFile.png");

        // then
        verify(spyActivity, after(500).never()).startActivity(any());
    }
}
