package com.example.rainingControl.frame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.CatchRemoveAdapter;
import com.example.rainingControl.DBHelper.CatchTypeDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;

import java.util.ArrayList;

import static com.example.rainingControl.Adapter.CatchRemoveAdapter.deletedList;
import static com.example.rainingControl.Adapter.CatchRemoveAdapter.itemListTemp;
import static com.example.rainingControl.frame.CatchActivity.itemList;

public class CatchRemoveActivity extends AppCompatActivity {
    private Button btSave, btReturn;
    private ListView listView;
    private CatchTypeDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
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
        cursor.moveToPosition(14);
        itemListTemp = new ArrayList<>();
        while (cursor.moveToNext()){
            item = new CatchItem(cursor.getString(0),cursor.getString(1));
            itemListTemp.add(item);
        }

        removeAdapter = new CatchRemoveAdapter(this, itemListTemp);
        listView.setAdapter(removeAdapter);

        //点击保存（保存删除操作）：
        // 1. 第一个for循环：在数据库中删除该记录
        // 2. 第二个for循环：在itemList中删除该记录
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CatchItem item: deletedList) {
                    db.execSQL("delete from Catchment where type='"+ item.getType()+"'");
                }
                cursor.close();
                for (CatchItem item1:deletedList) {
                    for (int i=itemList.size()-1;i>=0;i--){
                        CatchItem item2 = itemList.get(i);
                        if (item1.getType().equals(item2.getType()) && item1.getCoefficient().equals(item2.getCoefficient())) {
                            itemList.remove(item2);
                            break;
                        }
                    }
                }
                CatchRemoveActivity.this.finish();
            }
        });
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedList.clear();
                itemListTemp.clear();
                cursor.close();
                CatchRemoveActivity.this.finish();
            }
        });
    }
}
