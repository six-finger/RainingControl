package com.example.rainingControl.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

public class LidActivity extends AppCompatActivity {

    private Button btAdd, btBack, btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lid);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        btAdd = findViewById(R.id.btAdd);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btResult);
    }

    private void initData() {
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LidActivity.this, CatchActivity.class);
                startActivity(intent);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LidActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
