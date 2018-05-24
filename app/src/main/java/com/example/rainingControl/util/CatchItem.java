package com.example.rainingControl.util;

import android.widget.EditText;
import android.widget.TextView;

public class CatchItem {
    private String order, type, coefficient, area;

    public void setText(String order, String type, String coefficient, String area) {
        this.order = order;
        this.type = type;
        this.coefficient = coefficient;
        this.area = area;
    }

    public String getOrder() {
        return order;
    }
    public String getType() {
        return type;
    }
    public String getCoefficient() {
        return coefficient;
    }
    public String getArea() {
        return area;
    }

}
