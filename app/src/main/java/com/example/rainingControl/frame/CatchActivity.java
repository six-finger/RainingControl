package com.example.rainingControl.frame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.CatchAdapter;
import com.example.rainingControl.DBHelper.CatchTypeDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.Adapter.CatchRemoveAdapter.deletedTypeList;
import static com.example.rainingControl.frame.CatchAddActivity.itemAdd;

public class CatchActivity extends AppCompatActivity {
    private ListView listView;
    private Button btAdd, btRemove, btBack, btNext;
    private Cursor cursor;
    private CatchTypeDBHelper dbHelper;
    private SQLiteDatabase db;
    private List<CatchItem> itemList;
    private CatchItem item;
    private CatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    //添加一条记录后，返回到本页面所需的刷新，即：把itemAdd添加到itemList之后，重新声明adapter，并将itemAdd设为空值
    //删除记录保存后，返回到本页面所需的刷新，即：把deletedTypeList中的数据在itemList中删掉之后，重新声明adapter，并将deletedTypeLis设为空值
    @Override
    protected void onResume() {
        super.onResume();
        if (itemAdd != null) {
            itemList.add(itemAdd);
            adapter = new CatchAdapter(this, itemList);
            Log.i("TAG","已添加记录:"+itemAdd);
            itemAdd = null;
        }
        if (deletedTypeList.size()!=0){
            for (String type: deletedTypeList) {
                itemList.remove(type);
            }
            adapter = new CatchAdapter(this,itemList);
            Log.i("TAG","已删除记录:"+deletedTypeList.toString());
            deletedTypeList = new ArrayList<>();
        }
    }

    void initView(){
        listView = findViewById(R.id.listView);
        btAdd = findViewById(R.id.btAdd);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);
    }

    void initData(){
        //通过cursor将数据放到itemList中
        dbHelper = new CatchTypeDBHelper(CatchActivity.this,"Rain_DB",null,1);
        db = dbHelper.getReadableDatabase();
        cursor =db.query("Catchment",null,null,null,null,null,null);
        itemList = new ArrayList<>();
        while (cursor.moveToNext()){
            item = new CatchItem(cursor.getString(0),cursor.getString(1));
            itemList.add(item);
        }
        cursor.close();

        //将itemList放到本页面的adapter中
        adapter = new CatchAdapter(this, itemList);
        listView.setAdapter(adapter);
        db.close();

        //设置Button监听
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatchActivity.this, CatchAddActivity.class);
                startActivity(intent);
            }
        });
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CatchActivity.this, CatchRemoveActivity.class);
                startActivity(intent);
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchActivity.this.finish();
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatchActivity.this, LidActivity.class);
                startActivity(intent);
            }
        });
    }
}
