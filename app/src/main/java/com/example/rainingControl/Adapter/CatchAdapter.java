package com.example.rainingControl.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.frame.CatchActivity.catchList;

public class CatchAdapter extends BaseAdapter {
    private  Context context;
    private LayoutInflater inflater;
    private CatchItem catchItem;
    private int focusedPosition = -1;
    String TAG = "debug";

    public CatchAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return catchList.size();
    }

    @Override
    public CatchItem getItem(int position) {
        return catchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_catch, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        refreshValue(position, holder);
        return convertView;
    }

    private void refreshValue(final int position, final ViewHolder holder){
        catchItem = catchList.get(position);
        holder.tvType.setText(catchItem.getType());
        holder.tvCoefficient.setText(String.valueOf(catchItem.getCoefficient()));
        holder.etArea.setTag(position);
        holder.etArea.setText(String.valueOf(catchItem.getArea()));
//        holder.etArea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    v.setTag(position);
//                }
//            }
//        });
        holder.etArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                catchItem = catchList.get(position);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.requestFocus();               //请求焦点
                    keyboardShow(context,v);        //显示软键盘
                    ((EditText) v).selectAll();     //文字全选
                    focusedPosition = (Integer) v.getTag();
//                    Log.e(TAG, "onTouch:  start "+v.getTag()+"  "+position);
                }
                return false;
            }
        });
        holder.etArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (focusedPosition != -1 && !TextUtils.isEmpty(s)) {
//                    Log.e(TAG, "onTextChanged: "+focusedPosition+"  "+s );
//                    Log.e(TAG, "显示的是 "+catchItem.getType()+catchItem );
                    try {
                        catchList.get(focusedPosition).setArea(Float.parseFloat(String.valueOf(s+"")));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
//                    Log.e(TAG, String.valueOf(catchList.get(0).getArea())+"  "+String.valueOf(catchList.get(1).getArea())+"  "+String.valueOf(catchList.get(2).getArea())+"  "+String.valueOf(catchList.get(3).getArea())+"  "+String.valueOf(catchList.get(4).getArea())+"  " );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        holder.etArea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    focusedPosition = -1;
                    keyboardHide(context, v);
//                    Log.e("IME_ACTION_DONE", "onEditorAction"+v.getText());
//                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
//                    holder.etArea.clearFocus();
                }
                return false;
            }
        });

//        if (holder.etArea.getTag()!=null && holder.etArea.getTag().equals(position)) {
//            holder.etArea.requestFocus();
//            Log.e(TAG, "getView: requestFocus");
//        } else {
//            holder.etArea.clearFocus();
//        }
    }

    public float getTotalArea(){
        float totalArea = 0;
        for (CatchItem item: catchList) {
            totalArea += item.getArea();
        }
        return totalArea;
    }

    public float getComplexCoefficient() {
        float sum = 0;
        float totalArea = 0;
        for (CatchItem item: catchList) {
            totalArea += item.getArea();
            sum += item.getArea() * item.getCoefficient();
        }
        float complexCoefficient = sum/totalArea;
        return complexCoefficient;
    }

    private void keyboardShow(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    private void keyboardHide(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    public class ViewHolder{
        TextView tvType;
        TextView tvCoefficient;
        EditText etArea;;

        public ViewHolder(View convertView) {
            tvType = convertView.findViewById(R.id.tvType);
            tvCoefficient = convertView.findViewById(R.id.tvCoefficient);
            etArea = convertView.findViewById(R.id.etArea);
        }
    }
}
