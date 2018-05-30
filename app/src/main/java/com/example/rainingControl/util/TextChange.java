package com.example.rainingControl.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextChange implements TextWatcher {
    private float text;
    private EditText editText;

    public TextChange(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        text = Float.parseFloat(editText.getText().toString());
    }

    public float get() {
        return text;
    }
}
