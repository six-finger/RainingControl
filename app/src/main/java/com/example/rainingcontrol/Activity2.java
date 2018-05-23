package com.example.rainingcontrol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private CatchmentTypeDBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init(){
        //databaseHelper
        dbHelper = new CatchmentTypeDBHelper(Activity2.this,"Rain_DB",null,1);
        db = dbHelper.getReadableDatabase();
        final Cursor cursor =db.query("Catchment",null,null,null,null,null,null);

        //得到手机屏幕宽度width
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int width = dm.widthPixels;

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
                    LinearLayout ll = new LinearLayout(Activity2.this);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    TextView tv00 = new TextView(Activity2.this);
                    TextView tv01 = new TextView(Activity2.this);
                    TextView tv02 = new TextView(Activity2.this);
                    TextView tv03 = new TextView(Activity2.this);
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
                LinearLayout ll = new LinearLayout(Activity2.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                TextView tv0 = new TextView(Activity2.this);
                TextView tv1 = new TextView(Activity2.this);
                TextView tv2 = new TextView(Activity2.this);
                final EditText et3 = new EditText(Activity2.this);
                tv0.setText(cursor.getString(0));
                tv1.setText(cursor.getString(1));
                tv2.setText(cursor.getString(2));
                et3.setText(cursor.getString(3));
                tv0.setWidth(width/15);
                tv1.setWidth(width*2/3);
                tv2.setWidth(width/6);
                et3.setWidth(width/9);
                ll.addView(tv0);
                ll.addView(tv1);
                ll.addView(tv2);
                ll.addView(et3);

                //为editText设置TextChangedListener，每次改变的值设置到hashMap
                //我们要拿到里面的值根据position拿值
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

                return ll;
            }
        };
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

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
