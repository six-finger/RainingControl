package com.example.rainingControl.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.ResultAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

import java.util.List;

import static com.example.rainingControl.frame.HistoryActivity.historyDataList;
import static com.example.rainingControl.frame.MainActivity.resultList;

public class ResultShowActivity extends AppCompatActivity {
    private ResultAdapter adapter;
    private Button btBack;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        btBack = findViewById(R.id.btBack);
        listView = findViewById(R.id.listView);
    }

    private void initData() {
        adapter = new ResultAdapter(ResultShowActivity.this, historyDataList);
        listView.setAdapter(adapter);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultShowActivity.this.finish();
            }
        });

    }
}
