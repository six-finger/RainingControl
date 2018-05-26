package com.example.rainingControl.util;

import android.widget.EditText;
import android.widget.TextView;

public class CatchItem {
    private String type, coefficient;

    public CatchItem(String type, String coefficient) {
        this.type = type;
        this.coefficient = coefficient;
    }

    public String getType() {
        return type;
    }
    public String getCoefficient() {
        return coefficient;
    }

}
