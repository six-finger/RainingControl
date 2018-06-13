package com.example.rainingControl.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rainingControl.R;
import com.example.rainingControl.util.LidItem;
import com.example.rainingControl.util.TextChange;

import static com.example.rainingControl.frame.LidActivity.lidList;

public class LidAddDialog {
    private Context context;
    private String lidType, lidName;
    private float area, depth, ratio, volumeControl;

    private String[] lidTypeList = new String[]{"下垫面改造","雨水花园"};
    private String[] lidNameList1 = new String[]{"屋顶绿化","透水铺装"};
    private String[] lidNameList2 = new String[]{"植草沟","生物滞留池","雨水塘","储水装置"};
    private ArrayAdapter<String> lidTypeArray;
    private ArrayAdapter<String> lidNameArray1;
    private ArrayAdapter<String> lidNameArray2;

    public LidAddDialog(Context context){
        this.context = context;
    }

    public void show() {
        lidTypeArray = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lidTypeList);
        lidNameArray1 = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lidNameList1);
        lidNameArray2 = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lidNameList2);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.dialog_lid_add, null);
        //设置对话框布局
        dialog.setTitle("请输入各项数据：");
        dialog.setView(dialogView);
        dialog.show();
        //控件调用
        final Spinner spLidType = dialogView.findViewById(R.id.spLidType);
        final Spinner spLidName = dialogView.findViewById(R.id.spLidName);
        final EditText etArea = dialogView.findViewById(R.id.etArea);
        final EditText etDepth = dialogView.findViewById(R.id.etDepth);
        final EditText etRatio = dialogView.findViewById(R.id.etRatio);
        Button btOk = dialogView.findViewById(R.id.btOk);
        Button btCancel = dialogView.findViewById(R.id.btCancel);

        spLidType.setAdapter(lidTypeArray);
        spLidType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lidType = (String) spLidType.getSelectedItem();
                if (spLidType.getSelectedItem().equals("下垫面改造")) {
                    spLidName.setAdapter(lidNameArray1);
                }else if (spLidType.getSelectedItem().equals("雨水花园")) {
                    spLidName.setAdapter(lidNameArray2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLidName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lidName = (String) spLidName.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final TextChange areaChange = new TextChange(etArea);
        etArea.addTextChangedListener(areaChange);
        final TextChange depthChange = new TextChange(etDepth);
        etDepth.addTextChangedListener(depthChange);
        final TextChange ratioChange = new TextChange(etRatio);
        etRatio.addTextChangedListener(ratioChange);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(String.valueOf(area))) {
                    Toast.makeText(context, "面积不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(String.valueOf(depth))){
                    Toast.makeText(context, "深度不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(String.valueOf(ratio))) {
                    Toast.makeText(context, "孔隙率不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                area = areaChange.get();
                depth = depthChange.get();
                ratio = ratioChange.get();
                volumeControl = area*depth*ratio/100;
                lidList.add(new LidItem(lidType, lidName, area, depth, ratio, volumeControl));
                dialog.dismiss();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
