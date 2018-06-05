package com.example.rainingControl.frame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.CatchRemoveAdapter;
import com.example.rainingControl.DBHelper.CatchDBHelper;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.Adapter.CatchRemoveAdapter.deletedList;
import static com.example.rainingControl.frame.CatchActivity.catchList;
import static com.example.rainingControl.frame.CatchActivity.catchSave;

public class CatchRemoveActivity extends AppCompatActivity {
    private Button btSave, btReturn;
    private ListView listView;
    private CatchDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private CatchItem item;
    private CatchRemoveAdapter removeAdapter;
    private List<CatchItem> catchListTemp;

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
        catchListTemp = new ArrayList<>();
        for (int i=15;i<catchList.size();i++) {
            catchListTemp.add(catchList.get(i));
        }
        removeAdapter = new CatchRemoveAdapter(this, catchListTemp);
        listView.setAdapter(removeAdapter);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CatchItem item1:deletedList) {
                    for (int i = catchList.size()-1; i>=0; i--){
                        CatchItem item2 = catchList.get(i);
                        if (item1.getType().equals(item2.getType()) && item1.getCoefficient()==(item2.getCoefficient())) {
                            catchList.remove(item2);
                            break;
                        }
                    }
                }
                catchSave.setDataList("catchment", catchList);
                CatchRemoveActivity.this.finish();
            }
        });
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedList.clear();
                catchListTemp.clear();
                CatchRemoveActivity.this.finish();
            }
        });
    }
}
