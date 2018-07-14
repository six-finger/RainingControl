package com.example.rainingControl.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btStart;
    private Button btHistory;
    private Button btExit;
    public static List<String> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        btStart = findViewById(R.id.btStart);
        btHistory = findViewById(R.id.btHistory);
        btExit = findViewById(R.id.btExit);
    }

    private void initData() {
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultList = new ArrayList<>();
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                startActivity(intent);
            }
        });
        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitActivityUtil.getInstance().exit();
            }
        });
    }


}
