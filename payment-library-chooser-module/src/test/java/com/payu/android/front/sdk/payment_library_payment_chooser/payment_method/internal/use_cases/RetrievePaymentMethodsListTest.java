package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodsAdapter;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method.SelectedPaymentMethodExtractor;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.emptyObserver;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RetrievePaymentMethodsListTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    private MutableLiveData<List<PaymentMethod>> paymentMethods;
    private RetrievePaymentMethodsList objectUnderTest;
    @Mock
    private PaymentMethodRepository repository;
    @Mock
    private PaymentMethodsAdapter paymentMethodsAdapter;
    @Mock
    private SelectedPaymentMethodExtractor extractor;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void shouldReturnFreshValueWhenRefreshLiveDataChanges() {
        //given
        objectUnderTest = new RetrievePaymentMethodsList(repository, paymentMethodsAdapter, extractor);
        MutableLiveData<Boolean> refreshData = new MutableLiveData<>();
        refreshData.setValue(true);
        when(paymentMethodsAdapter.refreshData()).thenReturn(refreshData);
        when(repository.getPayments()).thenReturn(paymentMethods);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();
        paymentMethods.observeForever(emptyObserver());

        //when
        refreshData.setValue(true);

        //then
        verify(repository, times(2)).getPayments();
    }

    @Test
    public void shouldReturnValueWhenRefreshLiveDataSetup() {
        //given
        objectUnderTest = new RetrievePaymentMethodsList(repository, paymentMethodsAdapter, extractor);
        MutableLiveData<Boolean> refreshData = new MutableLiveData<>();
        refreshData.setValue(true);
        when(paymentMethodsAdapter.refreshData()).thenReturn(refreshData);
        when(repository.getPayments()).thenReturn(paymentMethods);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();

        //when
        paymentMethods.observeForever(emptyObserver());

        //then
        verify(repository, times(1)).getPayments();
    }

    @Test
    public void shouldReturnFilteredPaymentMethodsWhenNoAdapter() {
        //given
        objectUnderTest = new RetrievePaymentMethodsList(repository, null, extractor);
        when(repository.getPayments()).thenReturn(paymentMethods);
        LiveData<List<PaymentMethod>> paymentMethods = objectUnderTest.getPaymentMethods();

        //when
        paymentMethods.observeForever(emptyObserver());

        //then
        verify(repository, times(1)).getPayments();
    }
}