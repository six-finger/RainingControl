package com.example.rainingcontrol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private TableLayout tbLayout;
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
            public View getView(int position, View convertView, ViewGroup parent) {
                if (flag){
                    LinearLayout ll = new LinearLayout(Activity2.this);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    TextView tv01 = new TextView(Activity2.this);
                    TextView tv02 = new TextView(Activity2.this);
                    TextView tv03 = new TextView(Activity2.this);
                    TextView tv04 = new TextView(Activity2.this);
                    tv02.setText("汇水面种类");
                    tv03.setText("雨量径流系数");
                    tv04.setText("面积");
                    tv01.setPaddingRelative(5,0,3,0);
                    tv01.setWidth(60);
                    tv02.setWidth(width*2/3);
                    tv03.setWidth(150);
                    tv04.setPaddingRelative(0,0,0,0);
                    tv04.setWidth(100);
                    ll.addView(tv01);
                    ll.addView(tv02);
                    ll.addView(tv03);
                    ll.addView(tv04);
                    flag = false;
                    return ll;
                }
                cursor.moveToNext();
                LinearLayout ll = new LinearLayout(Activity2.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                TextView tv01 = new TextView(Activity2.this);
                TextView tv02 = new TextView(Activity2.this);
                TextView tv03 = new TextView(Activity2.this);
                EditText et0 = new EditText(Activity2.this);
                tv01.setText(cursor.getString(0));
                tv02.setText(cursor.getString(1));
                tv03.setText(cursor.getString(2));
                et0.setText("");
                tv01.setWidth(60);
                tv02.setWidth(width*2/3);
                tv03.setWidth(150);
                et0.setPaddingRelative(50,0,0,0);
                et0.setWidth(100);
                ll.addView(tv01);
                ll.addView(tv02);
                ll.addView(tv03);
                ll.addView(et0);

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
