package com.example.rainingControl.util;

import android.widget.EditText;
import android.widget.TextView;

public class CatchItem {
    private String type;
    private float coefficient;

    public CatchItem(String type, float coefficient) {
        this.type = type;
        this.coefficient = coefficient;
    }

    public String getType() {
        return type;
    }
    public float getCoefficient() {
        return coefficient;
    }

}
