package com.example.rainingControl.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.LidRemoveAdapter;
import com.example.rainingControl.R;

import static com.example.rainingControl.Adapter.LidRemoveAdapter.lidListTemp;
import static com.example.rainingControl.frame.LidActivity.lidList;

public class LidRemoveActivity extends AppCompatActivity {
    private ListView listView;
    private Button btSave, btCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lid_remove);
        initView();
        initData();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
    }

    private void initData() {
        LidRemoveAdapter adapter = new LidRemoveAdapter(LidRemoveActivity.this);
        listView.setAdapter(adapter);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lidList = lidListTemp;
                finish();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
