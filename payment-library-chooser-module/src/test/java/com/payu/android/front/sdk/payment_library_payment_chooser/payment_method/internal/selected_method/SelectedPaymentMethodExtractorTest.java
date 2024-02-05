package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.selected_method;

import com.google.common.base.Optional;
import com.payu.android.front.sdk.payment_library_core.hashing.TokenHasher;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createCardPayment;
import static com.payu.android.front.sdk.payment_library_payment_chooser.utils.PaymentMethodsModelTestDataProvider.createPblPayment;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class SelectedPaymentMethodExtractorTest {

    @Mock
    private SelectedPaymentMethodPersistenceStorage selectedPaymentMethodPersistenceStorage;
    private TokenHasher tokenHasher;
    private SelectedPaymentMethodExtractor objectUnderTest;

    @Before
    public void setUp() {
        openMocks(this);
        tokenHasher = new TokenHasher();
        objectUnderTest = new SelectedPaymentMethodExtractor(selectedPaymentMethodPersistenceStorage, tokenHasher);
    }

    @Test
    public void shouldNotReturnSelectedPblIfItBecameInactive() {
        //given
        PaymentMethod disabled = createPblPayment("DISABLED");
        String disabledPblValue = disabled.getValue();
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.of(disabledPblValue));

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(disabled));

        //then
        assertThat(selectedMethodOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnAbsentWhenMethodIsNotSelectedAndMoreThanOneMethodAvailable() {
        //given
        PaymentMethod card = createCardPayment("ACTIVE");
        PaymentMethod secondCard = createCardPayment("ACTIVE");
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.<String>absent());

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(card, secondCard));

        //then
        assertThat(selectedMethodOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnAbsentWhenNoCardIsSelectedThereAreNoCardsInTheList() {
        //given

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(Collections.<PaymentMethod>emptyList());

        //then
        assertThat(selectedMethodOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnAbsentWhenStoredHashCannotBeFoundOnTheList() {
        //given
        PaymentMethod card = createCardPayment("ACTIVE", "TOKEN1");
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.of("TOKEN2"));

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(card));

        //then
        assertThat(selectedMethodOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldReturnSelectedCard() {
        //given
        PaymentMethod card = createCardPayment("ACTIVE", "TOKEN");
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.of(tokenHasher.getHash("TOKEN")));

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(card));

        //then
        assertThat(selectedMethodOptional.get()).isEqualTo(card);
    }

    @Test
    public void shouldSetFirstMethodSelectedIfNoCardSelectedAndOnlyOneCardProvided() {
        //given
        PaymentMethod card = createCardPayment("ACTIVE", "TOKEN");
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.<String>absent());

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(card));

        //then
        assertThat(selectedMethodOptional.get()).isEqualTo(card);
    }

    @Test
    public void shouldSetPblMethodSelectedIfNoCardSelectedAndOnlyOneMethodProvided() {
        //given
        PaymentMethod pbl = createPblPayment("ENABLED");
        when(selectedPaymentMethodPersistenceStorage.getSelectedMethodHash()).thenReturn(Optional.<String>absent());

        //when
        Optional<PaymentMethod> selectedMethodOptional = objectUnderTest.getSelectedPaymentMethod(asPaymentMethodList(pbl));

        //then
        assertThat(selectedMethodOptional.get()).isEqualTo(pbl);
    }

    private List<PaymentMethod> asPaymentMethodList(PaymentMethod... methods) {
        return Arrays.asList(methods);
    }
}