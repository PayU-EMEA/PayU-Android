package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.widget;

import com.payu.android.front.sdk.payment_library_core.translation.Translation;
import com.payu.android.front.sdk.payment_library_core.translation.TranslationKey;
import com.payu.android.front.sdk.payment_library_payment_methods.model.BlikPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.CardPaymentMethod;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PayByLinkPaymentMethod;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createBlikTokenPaymentMethod;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPaymentMethod;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SelectedPaymentMethodConverterTest {
    private SelectedPaymentMethodConverter objectUnderTest;
    @Mock
    private Translation translation;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new SelectedPaymentMethodConverter(translation);
    }

    @Test
    public void shouldParseCardPayment() {
        //given
        CardPaymentMethod cardPaymentMethod = createCardPaymentMethod();
        String cardName = "CARD_NAME";
        when(translation.translate(TranslationKey.CREDIT_CARD)).thenReturn(cardName);

        //when
        SelectedPaymentMethodModel converted = objectUnderTest.convert(cardPaymentMethod);

        //then
        assertThat(converted.getContent()).isEqualTo(cardPaymentMethod.getCardNumberMasked());
        assertThat(converted.getImageUrl()).isEqualTo(cardPaymentMethod.getBrandImageUrl());
        assertThat(converted.getHeader()).isEqualTo(cardName);
        assertThat(converted.getId()).isEqualTo(cardPaymentMethod.getValue());
    }

    @Test
    public void shouldParsePblPayment() {
        //given
        PayByLinkPaymentMethod cardPaymentMethod = createPblPaymentMethod();

        //when
        SelectedPaymentMethodModel converted = objectUnderTest.convert(cardPaymentMethod);

        //then
        assertThat(converted.getContent()).isNull();
        assertThat(converted.getImageUrl()).isEqualTo(cardPaymentMethod.getBrandImageUrl());
        assertThat(converted.getHeader()).isEqualTo(cardPaymentMethod.getName());
        assertThat(converted.getId()).isEqualTo(cardPaymentMethod.getValue());
    }

    @Test
    public void shouldParseBlikTokenPayment() {
        //given
        BlikPaymentMethod blikPaymentMethod = (BlikPaymentMethod) createBlikTokenPaymentMethod();
        String blikName = "BLIK_NAME";
        when(translation.translate(TranslationKey.BLIK_PAYMENT_NAME)).thenReturn(blikName);
        //when
        SelectedPaymentMethodModel converted = objectUnderTest.convert(blikPaymentMethod);

        //then
        assertThat(converted.getContent()).isNull();
        assertThat(converted.getImageUrl()).isEqualTo(blikPaymentMethod.getBrandImageUrl());
        assertThat(converted.getHeader()).isEqualTo(blikName);
        assertThat(converted.getId()).isEqualTo(blikPaymentMethod.getValue());
    }
}