package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPaymentMethod;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPayment;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.emptyObserver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RetrievePblPaymentMethodsTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private RetrievePblPaymentMethods objectUnderTest;
    @Mock
    private
    PaymentMethodRepository repository;
    @Mock
    private MutableLiveData<List<PaymentMethod>> paymentMethods;

    @Before
    public void setUp() {
        openMocks(this);
        objectUnderTest = new RetrievePblPaymentMethods(repository);
    }

    @Test
    public void shouldReturnPaymentMethodsFromRepository() {
        //given
        when(repository.getPayments()).thenReturn(paymentMethods);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();

        //when
        paymentMethods.observeForever(emptyObserver());

        //then
        verify(repository, times(1)).getPayments();
    }

    @Test
    public void shouldReturnOnlyPblPaymentMethods() {
        //given
        MutableLiveData<List<PaymentMethod>> paymentMethodsLiveData = new MutableLiveData<>();
        when(repository.getPayments()).thenReturn(paymentMethodsLiveData);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();
        paymentMethodsLiveData.setValue(
                Arrays.asList(createCardPaymentMethod(), createPblPayment("ENABLED", "t"), createPblPayment("ENABLED", "cs")));
        final List<PaymentMethod> paymentMethodList = new ArrayList<>();

        //when
        paymentMethods.observeForever(new Observer<List<PaymentMethod>>() {
            @Override
            public void onChanged(@Nullable List<PaymentMethod> paymentMethods) {
                paymentMethodList.addAll(paymentMethods);
            }
        });

        //then
        assertThat(paymentMethodList.size()).isEqualTo(2);
        assertThat(paymentMethodList.get(0).getValue()).isEqualTo("t");
        assertThat(paymentMethodList.get(1).getValue()).isEqualTo("cs");
    }

    @Test
    public void shouldFilterOutNotWhitelistedMethods() {
        //given
        MutableLiveData<List<PaymentMethod>> paymentMethodsLiveData = new MutableLiveData<>();
        when(repository.getPayments()).thenReturn(paymentMethodsLiveData);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();
        paymentMethodsLiveData.setValue(
                Arrays.asList(createPblPayment("ENABLED", "t"), createPblPayment("ENABLED", "INVALID")));
        final List<PaymentMethod> paymentMethodList = new ArrayList<>();

        //when
        paymentMethods.observeForever(new Observer<List<PaymentMethod>>() {
            @Override
            public void onChanged(@Nullable List<PaymentMethod> paymentMethods) {
                paymentMethodList.addAll(paymentMethods);
            }
        });

        //then
        assertThat(paymentMethodList.size()).isEqualTo(1);
        assertThat(paymentMethodList.get(0).getValue()).isEqualTo("t");
    }
}