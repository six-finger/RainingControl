package com.example.rainingControl.frame;

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

import com.example.rainingControl.R;
import com.example.rainingControl.util.ExitActivityUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.frame.MainActivity.resultList;

public class CityActivity extends AppCompatActivity {
    private TextView textRainfall;
    private Spinner spinnerCity, spinnerRatio;
    private Button btBack, btNext;
    private String city;
    private String[] cities = new String[]{"苏州"};
    private String[] ratios1 = new String[]{"60%","65%","70%","75%","80%","85%"};
    public float rainfall;
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ExitActivityUtil.getInstance().addActivity(this);
        initView();
        initData();
    }

    private void initView() {
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerRatio = findViewById(R.id.spinnerRatio);
        textRainfall = findViewById(R.id.tvRainfall);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);
    }

    private void initData() {
        //设置 城市为苏州时 的控制率和对应设计降水量值
        final SharedPreferences SuzhouData = getSharedPreferences("苏州", MODE_PRIVATE);
        SharedPreferences.Editor editor = SuzhouData.edit();
        editor.putFloat("60%", 12.7f);
        editor.putFloat("65%", 14.9f);
        editor.putFloat("70%", 17.5f);
        editor.putFloat("75%", 20.8f);
        editor.putFloat("80%", 25.1f);
        editor.putFloat("85%", 30.9f);
        editor.apply();

        //城市、控制率数据存储在ArrayAdapter<>中
        ArrayAdapter<String> adapterCities = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        final ArrayAdapter<String> adapterRatios1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratios1);

        spinnerCity.setAdapter(adapterCities);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) spinnerCity.getSelectedItem();
                if(city.equals("苏州")){
                    spinnerRatio.setAdapter(adapterRatios1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerRatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                key = (String) spinnerRatio.getSelectedItem();
                rainfall = SuzhouData.getFloat(key, 0);
                textRainfall.setText(String.valueOf(rainfall));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultList.add(0,key);
                resultList.add(1,String.valueOf(rainfall));
                Intent intent = new Intent(CityActivity.this, CatchActivity.class);
                startActivity(intent);
            }
        });

    }

}

