package com.example.rainingControl.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rainingControl.Adapter.HistoryAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Button btReturn;
    private ListView listView;
    private HistoryAdapter adapter;
    private ListDataSave resultSave;
    public static List<String> historyDataList;

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
        resultSave = new ListDataSave(HistoryActivity.this, "history");
        adapter = new HistoryAdapter(HistoryActivity.this, resultSave);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object tag = adapter.getItem(position);
                historyDataList = resultSave.getDataList(tag.toString());
                Intent intent = new Intent(HistoryActivity.this, ResultShowActivity.class);
                startActivity(intent);
            }
        });
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryActivity.this.finish();
            }
        });
    }
}
