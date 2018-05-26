package com.example.rainingControl.frame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.CatchRemoveAdapter;
import com.example.rainingControl.DBHelper.CatchTypeDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.Adapter.CatchRemoveAdapter.deletedTypeList;

public class CatchRemoveActivity extends AppCompatActivity {
    private Button btSave, btReturn;
    private ListView listView;
    private CatchTypeDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private List<CatchItem> itemList;
    private CatchItem item;
    private CatchRemoveAdapter removeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_remove);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    void initView(){
        listView = findViewById(R.id.listView);
        btSave = findViewById(R.id.btSave);
        btReturn = findViewById(R.id.btReturn);
    }

    void initData(){
        dbHelper = new CatchTypeDBHelper(CatchRemoveActivity.this,"Rain_DB",null,1);
        db = dbHelper.getReadableDatabase();
        cursor =db.query("Catchment",null,null,null,null,null,null);
        itemList = new ArrayList<>();
        while (cursor.moveToNext()){
            item = new CatchItem(cursor.getString(0),cursor.getString(1));
            itemList.add(item);
        }
        cursor.close();

        removeAdapter = new CatchRemoveAdapter(this, itemList);
        listView.setAdapter(removeAdapter);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG","待删除记录:"+deletedTypeList.toString());
                cursor =db.query("Catchment",null,null,null,null,null,null);
                for (String deletedType: deletedTypeList) {
                    db.execSQL("delete from Catchment where type='"+ deletedType+"'");
                }
                CatchRemoveActivity.this.finish();
            }
        });
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedTypeList = new ArrayList<>();
                CatchRemoveActivity.this.finish();
            }
        });
    }
}
