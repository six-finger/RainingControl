package com.example.rainingControl.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

public class LidDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lid_dialog);
        ExitActivityUtil.getInstance().addActivity(this);
    }
}
