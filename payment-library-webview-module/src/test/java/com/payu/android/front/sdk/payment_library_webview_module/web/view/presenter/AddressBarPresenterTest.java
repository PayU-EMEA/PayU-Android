package com.payu.android.front.sdk.payment_library_webview_module.web.view.presenter;

import com.payu.android.front.sdk.payment_library_core.resource.Image;
import com.payu.android.front.sdk.payment_library_webview_module.R;
import com.payu.android.front.sdk.payment_library_webview_module.web.formatter.SslAddressFormatter;
import com.payu.android.front.sdk.payment_library_webview_module.web.view.AddressBarView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressBarPresenterTest {
    private static final String FORMATTED_ADDRESS = "formatted_address";
    private AddressBarPresenter objectUnderTests;
    @Mock
    private SslAddressFormatter sslAddressFormatter;
    @Mock
    private AddressBarView addressView;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sslAddressFormatter.getFormattedAddress(anyString(), anyBoolean())).thenReturn(FORMATTED_ADDRESS);
        objectUnderTests = new AddressBarPresenter(sslAddressFormatter);
        objectUnderTests.takeView(addressView);
    }

    @Test
    public void shouldCallSslAddressFormatterWhenNewAddressWasSet() {
        // given

        // when
        String address = "address";
        objectUnderTests.setAddress(address);

        // then
        verify(sslAddressFormatter, times(1)).isHttps(address);
        verify(addressView, times(1)).setPadlockIcon(anyInt());
        verify(addressView, times(1)).setFormattedAddress(FORMATTED_ADDRESS);
    }

    @Test
    public void shouldNotCallTwiceSslAddressFormatterWhenNewAddressWasSet() {
        // given
        objectUnderTests.setAddress("test");

        // when
        objectUnderTests.setAddress("test");

        // then
        verify(sslAddressFormatter, times(1)).isHttps("test");
    }

    @Test
    public void shouldCallTwiceSslAddressFormatterWhenNewAddressWasSet() {
        // given
        objectUnderTests.setAddress("test");

        // when
        objectUnderTests.setAddress("test2");

        // then
        verify(sslAddressFormatter, times(2)).isHttps(anyString());
    }

    @Test
    public void shouldReturnSafePadlockIconWhenAddressIsVerified() {
        //given
        String image_path = "www.path.com/" + Image.PADLOCK_SAFE.getPath();

        //when
        objectUnderTests.setIsAddressVerified(true);

        //then
        verify(addressView, times(1)).setPadlockIcon(R.drawable.ic_lock_safe);
    }

    @Test
    public void shouldReturnUnsafePadlockIconWhenAddressIsNotVerified() {
        //given
        String image_path = "www.path.com/" + Image.PADLOCK_UNSAFE.getPath();

        //when
        objectUnderTests.setIsAddressVerified(false);

        //then
        verify(addressView, times(1)).setPadlockIcon(R.drawable.ic_lock_unsafe);
    }

    @Test
    public void shouldReturnUnsafePadlockIconWhenSettingHttpAddress() {
        //given
        String image_path = "www.path.com/" + Image.PADLOCK_UNSAFE.getPath();
        String url = "url";
        when(sslAddressFormatter.isHttps(url)).thenReturn(false);

        //when
        objectUnderTests.setAddress(url);

        //then
        verify(addressView, times(1)).setPadlockIcon(R.drawable.ic_lock_unsafe);
    }

    @Test
    public void shouldReturnSafePadlockIconWhenSettingHttpsAddress() {
        //given
        String image_path = "www.path.com/" + Image.PADLOCK_SAFE.getPath();
        String url = "url";
        when(sslAddressFormatter.isHttps(url)).thenReturn(true);

        //when
        objectUnderTests.setAddress(url);

        //then
        verify(addressView, times(1)).setPadlockIcon(R.drawable.ic_lock_safe);
    }
}
