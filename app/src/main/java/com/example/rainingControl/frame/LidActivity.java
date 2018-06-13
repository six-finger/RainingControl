package com.example.rainingControl.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.LidAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.dialog.LidAddDialog;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.LidItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.frame.MainActivity.resultList;

public class LidActivity extends AppCompatActivity {

    private Button btAdd, btRemove, btBack, btNext;
    private ListView listView;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public static List<LidItem> lidList = new ArrayList<>();
    private LidAdapter adapter;
    private LidAddDialog dialog = new LidAddDialog(LidActivity.this);

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
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btResult);
        listView = findViewById(R.id.listView);
    }

    private void initData() {
        adapter = new LidAdapter(LidActivity.this);
        listView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LidActivity.this, LidRemoveActivity.class);
                startActivity(intent);
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LidActivity.this.finish();
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                resultList.add(4,);
//                resultList.add(5,);
                Intent intent = new Intent(LidActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
