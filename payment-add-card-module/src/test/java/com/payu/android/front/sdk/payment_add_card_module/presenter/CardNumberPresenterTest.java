package com.payu.android.front.sdk.payment_add_card_module.presenter;

import com.payu.android.front.sdk.payment_add_card_module.issuer.CardIssuer;
import com.payu.android.front.sdk.payment_add_card_module.textwatcher.OnCardIssuerChangedListener;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectNumber;
import com.payu.android.front.sdk.payment_add_card_module.view.ValidableView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CardNumberPresenterTest {

    CardNumberPresenter objectUnderTest;

    @Mock
    SelectNumber view;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new CardNumberPresenter(new OnCardIssuerChangedListener() {
            @Override
            public void onCardIssuerChanged(CardIssuer newCardProvider) {
                // no-op
            }
        });
    }

    @Test
    public void shouldSetOnFocusChangeListenerOnInit() {
        objectUnderTest.takeView(view);

        verify(view).addValidateOnFocusChangeListener(any(ValidableView.ValidateOnFocusChangeListener.class));
    }
}