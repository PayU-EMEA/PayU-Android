package com.payu.android.front.sdk.payment_add_card_module.validation;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class ValidationErrorCleanerTextWatcherTest {

    ValidationErrorCleanerTextWatcher objectUnderTest;
    @Mock
    TextInputLayout textInputLayout;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new ValidationErrorCleanerTextWatcher(textInputLayout);
    }

    @Test
    public void shouldResetErrorStateIfTextHasChanged() {
        // when
        objectUnderTest.onTextChanged("test", 0, 0, 0);

        // then
        verify(textInputLayout).setError(isNull(CharSequence.class));
    }
}
