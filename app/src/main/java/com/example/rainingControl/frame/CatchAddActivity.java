package com.example.rainingControl.frame;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rainingControl.DBHelper.CatchDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;

import static com.example.rainingControl.frame.CatchActivity.catchList;

public class CatchAddActivity extends AppCompatActivity {
    private Button btSave, btReturn;
    private EditText etType, etCoefficient;
    private CatchDBHelper dbHelper;
    private SQLiteDatabase db;
    public static CatchItem catchAdd;            //itemAdd为添加的CatchItem

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
                catchAdd = new CatchItem(String.valueOf(etType.getText()), String.valueOf(etCoefficient.getText()));
                dbHelper = new CatchDBHelper(CatchAddActivity.this,"Rain_DB",null,1);
                db = dbHelper.getReadableDatabase();
                db.execSQL("insert into Catchment(type,coefficient) values(?, ?);", new Object[]{catchAdd.getType(), catchAdd.getCoefficient()});
                catchList.add(catchAdd);
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
