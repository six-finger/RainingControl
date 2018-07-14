package com.example.rainingControl.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ListDataSave;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.example.rainingControl.frame.MainActivity.resultList;

public class RecordDialog {
    private Context context;
    private ListDataSave resultSave;

    public RecordDialog(Context context, ListDataSave resultSave) {
        this.context = context;
        this.resultSave = resultSave;
    }

    public void show(){
        //时间戳time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String time = dateFormat.format(new java.util.Date());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.dialog_record, null);
        dialog.setView(dialogView);
        dialog.show();
        dialog.setTitle("请输入名称：");
        final EditText tvTitle = dialogView.findViewById(R.id.tvTitle);
        Button btOk = dialogView.findViewById(R.id.btOk);
        Button btCancel = dialogView.findViewById(R.id.btCancel);
        tvTitle.setText(time);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultSave.setDataList(tvTitle.getText().toString(), resultList);
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
