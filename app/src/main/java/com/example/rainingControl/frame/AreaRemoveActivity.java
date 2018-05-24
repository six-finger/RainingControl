package com.example.rainingControl.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

public class AreaRemoveActivity extends AppCompatActivity {

    private Button btDelete, btBackArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_remove);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    void initView(){
        btDelete = findViewById(R.id.btRemove);
        btBackArea = findViewById(R.id.btBack);
    }

    void initData(){
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaRemoveActivity.this, AreaActivity.class);
                startActivity(intent);
            }
        });
        btBackArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaRemoveActivity.this, AreaActivity.class);
                startActivity(intent);
            }
        });
    }
}
