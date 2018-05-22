package com.example.rainingcontrol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {
    private TextView tv4;
    private Spinner sp1;
    private Spinner sp2;
    private Button btBack;
    private Button btNext;
    private String city;
    private String[] cities = new String[]{"苏州"};
    private String[] ratios = new String[]{"60%","65%","70%","75%","80%","85%"};
    public float amount;
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        tv4 = findViewById(R.id.tv4);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);

        //设置 城市为苏州时 的控制率和对应设计降水量值
        final SharedPreferences SuzhouSharedPreferences = getSharedPreferences("苏州", MODE_PRIVATE);
        SharedPreferences.Editor editor = SuzhouSharedPreferences.edit();
        editor.putFloat("60%", 12.7f);
        editor.putFloat("65%", 14.9f);
        editor.putFloat("70%", 17.5f);
        editor.putFloat("75%", 20.8f);
        editor.putFloat("80%", 25.1f);
        editor.putFloat("85%", 30.9f);
        editor.commit();

        //显示
        //ArrayAdapter<>的形式存储城市数据
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratios);

        sp1.setAdapter(adapter1);
        sp1.setSelection(0,false);   //???test
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) sp1.getSelectedItem();
                if(city.equals("苏州")){
                    sp2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                key = (String) sp2.getSelectedItem();
                amount = SuzhouSharedPreferences.getFloat(key, 0);
                tv4.setText(String.valueOf(amount));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this, Activity2.class);
                startActivity(intent);
            }
        });

    }

}

