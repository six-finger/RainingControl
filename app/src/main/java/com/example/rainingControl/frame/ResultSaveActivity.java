package com.example.rainingControl.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.ResultAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.dialog.RecordDialog;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import static com.example.rainingControl.frame.MainActivity.resultList;

public class ResultSaveActivity extends AppCompatActivity {
    private ListView listView;
    private Button btSave, btBack, btMain;
    private ResultAdapter adapter;
    private RecordDialog dialog;
    private ListDataSave resultSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_save);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        btSave = findViewById(R.id.btSave);
        btBack = findViewById(R.id.btBack);
        btMain = findViewById(R.id.btMain);
    }

    private void initData() {
        adapter = new ResultAdapter(ResultSaveActivity.this, resultList);
        listView.setAdapter(adapter);
        resultSave = new ListDataSave(ResultSaveActivity.this, "history");
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new RecordDialog(ResultSaveActivity.this, resultSave);
                dialog.show();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultSaveActivity.this.finish();
            }
        });
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultSaveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
