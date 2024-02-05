package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.ui.pay_by_link.presentation;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.model.PayByLinkModel;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPaymentMethod;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class PblModelConverterTest {

    private PblModelConverter objectUnderTest;

    @Before
    public void setup() {
        objectUnderTest = new PblModelConverter();
    }

    @Test
    public void shouldParsePayByLinkPaymentMethod() {
        //given
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(createPblPaymentMethod());

        //when
        List<PayByLinkModel> payByLinkModels = objectUnderTest.convert(paymentMethodList);

        //then
        assertThat(payByLinkModels.size()).isEqualTo(1);
    }

    @Test
    public void shouldParseMultiplePayByLinkPaymentMethods() {
        //given
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(createPblPaymentMethod());
        paymentMethodList.add(createPblPaymentMethod());

        //when
        List<PayByLinkModel> payByLinkModels = objectUnderTest.convert(paymentMethodList);

        //then
        assertThat(payByLinkModels.size()).isEqualTo(2);
    }

    @Test
    public void shouldSkipCardPaymentMethod() {
        //given
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(createPblPaymentMethod());
        paymentMethodList.add(createCardPaymentMethod());

        //when
        List<PayByLinkModel> payByLinkModels = objectUnderTest.convert(paymentMethodList);

        //then
        assertThat(payByLinkModels.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyListProvided() {
        //given
        List<PaymentMethod> paymentMethodList = new ArrayList<>();

        //when
        List<PayByLinkModel> payByLinkModels = objectUnderTest.convert(paymentMethodList);

        //then
        assertThat(payByLinkModels.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnEmptyListWhenOnlyCardPaymentMethodsProvided() {
        //given
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(createCardPaymentMethod());
        paymentMethodList.add(createCardPaymentMethod());
        paymentMethodList.add(createCardPaymentMethod());

        //when
        List<PayByLinkModel> payByLinkModels = objectUnderTest.convert(paymentMethodList);

        //then
        assertThat(payByLinkModels.size()).isEqualTo(0);
    }
}
