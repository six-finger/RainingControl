package com.example.rainingControl.util;

import android.widget.EditText;
import android.widget.TextView;

public class CatchItem {
    private String type;
    private float coefficient;
    private float area;

    public CatchItem(String type, float coefficient, float area) {
        this.type = type;
        this.coefficient = coefficient;
    }

    public String getType() {
        return type;
    }
    public float getCoefficient() {
        return coefficient;
    }
    public float getArea() { return area; }
    public void setArea(float area) {
        this.area = area;
    }

}
