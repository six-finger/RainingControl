package com.example.rainingControl.frame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rainingControl.Adapter.CatchAdapter;
import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;
import com.example.rainingControl.util.ExitActivityUtil;
import com.example.rainingControl.util.ListDataSave;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.Adapter.CatchRemoveAdapter.deletedList;
import static com.example.rainingControl.frame.CatchAddActivity.catchAdd;
import static com.example.rainingControl.frame.MainActivity.resultList;

public class CatchActivity extends AppCompatActivity {
    private ListView listView;
    private Button btAdd, btRemove, btBack, btNext;
    private CatchAdapter adapter;
    public static ListDataSave catchSave;
    public static List<CatchItem> catchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    void initView(){
        listView = findViewById(R.id.listView);
        btAdd = findViewById(R.id.btAdd);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);
    }

    void initData(){
        catchList = new ArrayList<CatchItem>(){{
            add(new CatchItem("绿化屋面(绿色屋顶,基质层厚度≥300mm)", 0.35f));
            add(new CatchItem("硬屋面、未铺石子的平屋面、沥青屋面", 0.85f));
            add(new CatchItem("铺石子的平屋面", 0.65f));
            add(new CatchItem("混凝土或沥青路面及广场", 0.85f));
            add(new CatchItem("大块石等铺砌路面及广场", 0.55f));
            add(new CatchItem("沥青表面处理的碎石路面及广场", 0.50f));
            add(new CatchItem("级配碎石路面及广场", 0.40f));
            add(new CatchItem("干砌砖石或碎石路面及广场", 0.40f));
            add(new CatchItem("非铺砌土路面", 0.30f));
            add(new CatchItem("绿地", 0.15f));
            add(new CatchItem("下沉式绿地", 0.15f));
            add(new CatchItem("水面", 1.00f));
            add(new CatchItem("地下建筑覆土绿地（覆土≥500mm）", 0.15f));
            add(new CatchItem("地下建筑覆土绿地（覆土<500mm）", 0.35f));
            add(new CatchItem("透水铺装地面", 0.20f));
        }};
        catchSave = new ListDataSave(CatchActivity.this, "catchment");
        catchSave.setDataList("catchment", catchList);

        adapter = new CatchAdapter(CatchActivity.this);
        listView.setAdapter(adapter);

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
//                resultList.add(2,);
//                resultList.add(3,);
                Intent intent = new Intent(CatchActivity.this, LidActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAdd();
        refreshRemove();
    }

    //添加一条记录后，返回到本页面所需的刷新操作和变量重置
    void refreshAdd() {
        if (catchAdd != null) {
            adapter.notifyDataSetChanged();
            catchAdd = null;
        }
    }

    //保存删除记录后，返回到本页面所需的刷新操作和变量重置
    void refreshRemove() {
        if (deletedList.size()!=0){
            adapter.notifyDataSetChanged();
            deletedList.clear();
        }
    }
}
