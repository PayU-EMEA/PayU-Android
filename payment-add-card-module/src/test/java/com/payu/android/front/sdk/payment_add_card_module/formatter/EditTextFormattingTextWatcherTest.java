package com.payu.android.front.sdk.payment_add_card_module.formatter;

import android.text.Editable;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class EditTextFormattingTextWatcherTest {

    EditTextFormattingTextWatcher objectUnderTest;

    @Mock
    EditText mockEditText;

    @Mock
    Editable mockEditable;
    @Mock
    FormattingStrategy mockFormattingStrategy;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new EditTextFormattingTextWatcher(mockEditText, mockFormattingStrategy);
    }

    @Test
    public void shouldAddTextWatcherAfterFormatting() {
        // given
        when(mockFormattingStrategy.format(anyString())).thenReturn("");

        // when
        objectUnderTest.afterTextChanged(Editable.Factory.getInstance().newEditable(""));

        // then
        InOrder inOrder = inOrder(mockEditText, mockFormattingStrategy);
        inOrder.verify(mockFormattingStrategy).format(eq(""));
        inOrder.verify(mockEditText).addTextChangedListener(eq(objectUnderTest));
    }

    @Test
    public void shouldDoNothingBeforeTextChanged() {
        // when
        objectUnderTest.beforeTextChanged("", 0, 0, 0);

        // then
        verifyNoInteractions(mockEditText);
    }

    @Test
    public void shouldDoNothingOnTextChanged() {
        // when
        objectUnderTest.onTextChanged("", 0, 0, 0);

        // then
        verifyNoInteractions(mockEditText);
    }

    @Test
    public void shouldExtendSelectionToTheEndOfString() {
        // given
        String inputText = "input";
        String expectedText = "expected";
        when(mockFormattingStrategy.format(eq(inputText))).thenReturn(expectedText);
        when(mockEditText.getSelectionStart()).thenReturn(inputText.length());
        when(mockEditText.getText()).thenReturn(mockEditable);
        when(mockEditable.length()).thenReturn(expectedText.length());
        // when
        objectUnderTest.afterTextChanged(Editable.Factory.getInstance().newEditable(inputText));

        // then
        verify(mockEditText).setSelection(eq(expectedText.length()));
    }

    @Test
    public void shouldRemoveTextWatcherBeforeFormatting() {
        // given
        when(mockFormattingStrategy.format(anyString())).thenReturn("");

        // when
        objectUnderTest.afterTextChanged(Editable.Factory.getInstance().newEditable(""));

        // then
        InOrder inOrder = inOrder(mockEditText, mockFormattingStrategy);
        inOrder.verify(mockEditText).removeTextChangedListener(eq(objectUnderTest));
        inOrder.verify(mockFormattingStrategy).format(eq(""));
    }

    @Test
    public void shouldSetFormattedText() {
        // given
        String inputText = "input";
        String expectedText = "expected";
        when(mockFormattingStrategy.format(eq(inputText))).thenReturn(expectedText);
        when(mockEditText.getText()).thenReturn(mockEditable);
        when(mockEditable.length()).thenReturn(expectedText.length());

        // when
        objectUnderTest.afterTextChanged(Editable.Factory.getInstance().newEditable(inputText));

        // then
        verify(mockEditText).setText(eq(expectedText));
    }
}