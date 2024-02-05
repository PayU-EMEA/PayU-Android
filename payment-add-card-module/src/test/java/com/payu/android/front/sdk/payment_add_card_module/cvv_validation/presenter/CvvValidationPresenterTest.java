package com.payu.android.front.sdk.payment_add_card_module.cvv_validation.presenter;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.payu.android.front.sdk.payment_add_card_module.cvv_validation.view.CvvValidationView;
import com.payu.android.front.sdk.payment_add_card_module.status.CvvPaymentStatus;
import com.payu.android.front.sdk.payment_add_card_module.validation.cvv.CvvValidator;
import com.payu.android.front.sdk.payment_add_card_module.view.SelectorCvv;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.parser.RedirectLinkParser;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.request.OpenPayuResult;
import com.payu.android.front.sdk.payment_library_api_client.internal.rest.service.CvvRestService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import retrofit2.Call;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CvvValidationPresenterTest {
    private CvvValidationPresenter objectUnderTest;
    private Gson gson = new Gson();
    @Mock
    private SelectorCvv selectorCvv;
    @Mock
    private CvvValidator cvvValidator;
    @Mock
    private RedirectLinkParser redirectLinkParser;
    @Mock
    private CvvRestService cvvRestService;
    @Mock
    private CvvValidationView validationView;
    @Mock
    private Call<OpenPayuResult> call;

    @Before
    public void setUp() {
        openMocks(this);
        when(cvvRestService.sendCvv(anyString())).thenReturn(call);
        objectUnderTest = new CvvValidationPresenter(gson, selectorCvv, cvvValidator, redirectLinkParser, cvvRestService);
        objectUnderTest.takeView(validationView);
    }

    @Test
    public void shouldReturnCanceledPaymentWhenOnCanceledCalled() {
        //given

        //when
        objectUnderTest.onCanceled();

        //then
        verify(validationView).onValidationCompleted(CvvPaymentStatus.CANCEL_PAYMENT);
    }

    @Test
    public void shouldValidateCvvWhenOnAcceptedCalled() {
        //given
        String cvv = "222";
        when(selectorCvv.getCvvCode()).thenReturn(cvv);
        when(cvvValidator.getErrorString(cvv)).thenReturn(Optional.<String>absent());

        //when
        objectUnderTest.onAccepted();

        //then
        verify(selectorCvv).getCvvCode();
        verify(cvvValidator).getErrorString(cvv);
        verify(selectorCvv).setCvvError(null);
    }

    @Test
    public void shouldSetErrorWhenCvvNotValid() {
        //given
        String cvv = "22";
        when(selectorCvv.getCvvCode()).thenReturn(cvv);
        String error = "ERROR";
        when(cvvValidator.getErrorString(cvv)).thenReturn(Optional.of(error));

        //when
        objectUnderTest.onAccepted();

        //then
        verify(selectorCvv).getCvvCode();
        verify(cvvValidator).getErrorString(cvv);
        verify(selectorCvv).setCvvError(error);
    }

    @Test
    public void shouldCallCvvValidateService() {

        //given
        String cvv = "222";
        when(selectorCvv.getCvvCode()).thenReturn(cvv);
        when(cvvValidator.getErrorString(cvv)).thenReturn(Optional.<String>absent());

        //when
        objectUnderTest.onAccepted();

        //then
        verify(cvvRestService).sendCvv(eq("{\"request\":\"auth.cvv\",\"data\":{\"cvv\":\"222\"}}"));
    }

    @Test
    public void shouldStartLoadingAfterCallStarted() {
        //given
        String cvv = "222";
        when(selectorCvv.getCvvCode()).thenReturn(cvv);
        when(cvvValidator.getErrorString(cvv)).thenReturn(Optional.<String>absent());

        //when
        objectUnderTest.onAccepted();

        //then
        verify(validationView).setViewLoading(eq(true));
    }
}