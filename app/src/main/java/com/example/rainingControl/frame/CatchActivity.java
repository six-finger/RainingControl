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

public class CatchActivity extends AppCompatActivity {
    private CatchTypeDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView listView;
    private Button btAdd, btRemove, btBack, btNext;
    public int width;
    private List<CatchItem> dataList ;
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

    void initView(){
        listView = findViewById(R.id.listView);
        btAdd = findViewById(R.id.btAdd);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);
    }

    void initData(){
        //databaseHelper
        dbHelper = new CatchTypeDBHelper(CatchActivity.this,"Rain_DB",null,1);
        db = dbHelper.getReadableDatabase();
        cursor =db.query("Catchment",null,null,null,null,null,null);
        dataList = new ArrayList<>();
        while (cursor.moveToNext()){
            item = new CatchItem();
            item.setText(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataList.add(item);
        }
        cursor.close();
        adapter = new CatchAdapter(this, dataList);
        listView.setAdapter(adapter);

        /*//得到手机屏幕宽度width
        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels;

        final BaseAdapter adapter = new BaseAdapter() {
            boolean flag = true;
            @Override
            public int getCount() {
                return 16;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                //表格第一行
                if (flag){
                    LinearLayout ll = new LinearLayout(CatchActivity.this);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    TextView tv00 = new TextView(CatchActivity.this);
                    TextView tv01 = new TextView(CatchActivity.this);
                    TextView tv02 = new TextView(CatchActivity.this);
                    TextView tv03 = new TextView(CatchActivity.this);
                    tv01.setText("汇水面种类");
                    tv02.setText("雨量径流系数");
                    tv03.setText("面积");
                    tv00.setPaddingRelative(5,0,3,0);
                    tv00.setWidth(width/15);
                    tv01.setWidth(width*2/3);
                    tv02.setWidth(width/6);
                    tv03.setPaddingRelative(0,0,0,0);
                    tv03.setWidth(width/9);
                    ll.addView(tv00);
                    ll.addView(tv01);
                    ll.addView(tv02);
                    ll.addView(tv03);
                    flag = false;
                    return ll;
                }

                //表格第二行开始
                cursor.moveToNext();
                LinearLayout ll = new LinearLayout(CatchActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                TextView tv0 = new TextView(CatchActivity.this);
                TextView tv1 = new TextView(CatchActivity.this);
                TextView tv2 = new TextView(CatchActivity.this);
                final EditText et3 = new EditText(CatchActivity.this);
//                tv0.setText(cursor.getString(0));
//                tv1.setText(cursor.getString(1));
//                tv2.setText(cursor.getString(2));
//                et3.setText(cursor.getString(3));
                tv0.setText("1");
                tv1.setText("2");
                tv2.setText("3");
                et3.setText("4");
                tv0.setWidth(width/15);
                tv1.setWidth(width*2/3);
                tv2.setWidth(width/6);
                et3.setWidth(width/9);
                ll.addView(tv0);
                ll.addView(tv1);
                ll.addView(tv2);
                ll.addView(et3);


                return ll;
            }
        };
        listView.setAdapter(adapter);
*/
        //为editText设置TextChangedListener，每次改变的值设置到hashMap
        //我们要拿到里面的值根据position拿值
        /*
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count,int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                float area = Float.parseFloat(et3.getText().toString());
                db.execSQL("update Catchment set area = "+area+" where ");
            }
        });
        */
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
                Intent intent = new Intent(CatchActivity.this, CityActivity.class);
                startActivity(intent);
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

    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight){
        int result;
        if(view==null)return 0;
        if(isHeight){
            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(h,0);
            result =view.getMeasuredHeight();
        }else{
            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            view.measure(0,w);
            result =view.getMeasuredWidth();
        }
        return result;
    }
}
