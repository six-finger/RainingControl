package com.example.rainingControl.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.rainingControl.util.ExitActivityUtil;

public class ExitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitActivityUtil.getInstance().addActivity(this);
    }
}
