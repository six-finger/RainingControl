package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rainingControl.R;

import static com.example.rainingControl.frame.MainActivity.resultList;

public class ResultAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private String valueItem;
    private String[] indexArray = new String[]{"年径流总量控制率","设计降雨量"};
    //private String[] indexArray = new String[]{"年径流总量控制率","设计降雨量","综合径流系数φ","项目占地面积(m²)","下垫面改造率","雨水花园率","控制容积(m²)","设计降雨控制量(m³)","单位面积控制容量(m³)","当前设计降雨控制量(m³)","当前设计降雨量(mm)","当前年径流总量控制率","达到目标还需调蓄容积(m³)"};

    public ResultAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return indexArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_result, parent, false);
            holder = new ViewHolder();
            holder.tvIndex = convertView.findViewById(R.id.tvIndex);
            holder.tvValue = convertView.findViewById(R.id.tvValue);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        notifyDataSetChanged();
        holder.tvIndex.setText(indexArray[position]);
        holder.tvValue.setText(resultList.get(position));
        return convertView;
    }

    private class ViewHolder{
        TextView tvIndex;
        TextView tvValue;
    }
}