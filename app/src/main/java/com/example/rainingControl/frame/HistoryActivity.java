package com.example.rainingControl.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.HistoryAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

public class HistoryActivity extends AppCompatActivity {
    private Button btReturn;
    private ListView listView;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        btReturn = findViewById(R.id.btReturn);
    }

    private void initData() {
        adapter = new HistoryAdapter(HistoryActivity.this);
        listView.setAdapter(adapter);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HistoryActivity.this.finish();
            }
        });
    }
}
