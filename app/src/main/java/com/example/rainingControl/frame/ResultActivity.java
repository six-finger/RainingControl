package com.example.rainingControl.frame;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rainingControl.Adapter.ResultAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import java.text.SimpleDateFormat;

import static com.example.rainingControl.frame.MainActivity.resultSave;
import static com.example.rainingControl.frame.MainActivity.resultList;

public class ResultActivity extends AppCompatActivity {
    private ListView listView;
    private Button btSave, btBack, btMain;
    private ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        btSave = findViewById(R.id.btSave);
        btBack = findViewById(R.id.btBack);
        btMain = findViewById(R.id.btMain);
    }

    private void initData() {
        adapter = new ResultAdapter(ResultActivity.this);
        listView.setAdapter(adapter);
        resultSave = new ListDataSave(ResultActivity.this, "history");
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //时间戳time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = dateFormat.format(new java.util.Date());

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(ResultActivity.this, R.layout.dialog_record, null);
                dialog.setView(dialogView);
                dialog.show();
                dialog.setTitle("请输入名称：");
                final EditText tvTitle = dialogView.findViewById(R.id.tvTitle);
                Button btOk = dialogView.findViewById(R.id.btOk);
                Button btCancel = dialogView.findViewById(R.id.btCancel);
                tvTitle.setText(time);
                btOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultSave.setDataList(tvTitle.getText().toString(), resultList);
                        dialog.dismiss();
                    }
                });
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitActivityUtil.getInstance().initMain();
            }
        });

    }
}
