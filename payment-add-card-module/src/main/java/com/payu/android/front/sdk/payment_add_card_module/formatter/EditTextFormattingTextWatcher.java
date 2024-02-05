package com.payu.android.front.sdk.payment_add_card_module.formatter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextFormattingTextWatcher implements TextWatcher {

    private EditText targetTextView;
    private FormattingStrategy formatter;

    public EditTextFormattingTextWatcher(EditText targetTextView, FormattingStrategy formatter) {
        this.targetTextView = targetTextView;
        this.formatter = formatter;
    }

    @Override
    public void afterTextChanged(Editable s) {
        targetTextView.removeTextChangedListener(this);
        formatText(preFormatting(s.toString()));
        targetTextView.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // unused
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // unused
    }

    protected String preFormatting(String input) {
        return input;
    }

    private void formatText(String input) {
        int selectionStart = targetTextView.getSelectionStart();
        String formatted = formatter.format(input);
        targetTextView.setText(formatted);

        if (formatted.length() > input.length()) {
            selectionStart += (formatted.length() - input.length());
        }

        int len = formatted.length();

        if (selectionStart >= len) {
            selectionStart = len;
        }

        if (len > 0) {
            int currentLength = targetTextView.getText().length();
          if(selectionStart > currentLength){
              selectionStart = currentLength;
          }
            targetTextView.setSelection(selectionStart);
        }
    }

}
