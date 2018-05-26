package com.example.rainingControl.frame;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rainingControl.DBHelper.CatchTypeDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;

public class CatchAddActivity extends AppCompatActivity {
    private Button btSave, btReturn;
    private EditText etType, etCoefficient;
    private CatchTypeDBHelper dbHelper;
    private SQLiteDatabase db;
    public static CatchItem itemAdd;            //itemAdd将会传到CatchActivit.class中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_add);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    void initView(){
        etType = findViewById(R.id.etType);
        etCoefficient = findViewById(R.id.etCoefficient);
        btSave = findViewById(R.id.btSave);
        btReturn = findViewById(R.id.btReturn);
    }

    void initData(){
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdd = new CatchItem(String.valueOf(etType.getText()), String.valueOf(etCoefficient.getText()));
                dbHelper = new CatchTypeDBHelper(CatchAddActivity.this,"Rain_DB",null,1);
                db = dbHelper.getReadableDatabase();
                db.execSQL("insert into Catchment(type,coefficient) values(?, ?);", new Object[]{itemAdd.getType(), itemAdd.getCoefficient()});
                CatchAddActivity.this.finish();
            }
        });
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchAddActivity.this.finish();
            }
        });
    }
}