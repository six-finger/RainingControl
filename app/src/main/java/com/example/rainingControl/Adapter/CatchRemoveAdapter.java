package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;

import java.util.ArrayList;
import java.util.List;

public class CatchRemoveAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public static List<CatchItem> catchListTemp = new ArrayList<>();             //为所有在该页面上显示的item建立一个列表
    public static List<CatchItem> deletedList = new ArrayList<>();             //将所有 待删除的item 放入该List中

    public CatchRemoveAdapter(Context context, List<CatchItem> iList) {
        this.inflater = LayoutInflater.from(context);
        catchListTemp = iList;
    }

    @Override
    public int getCount() {
        return catchListTemp.size();
    }

    @Override
    public Object getItem(int position) {
        return catchListTemp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.catch_remove_item, parent, false);
            holder = new ViewHolder();
            holder.tvType = convertView.findViewById(R.id.tvType);
            holder.tvCoefficient = convertView.findViewById(R.id.tvCoefficient);
            holder.btDelete = convertView.findViewById(R.id.btDelete);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        CatchItem item = catchListTemp.get(position);
        holder.tvType.setText(item.getType());
        holder.tvCoefficient.setText(item.getCoefficient());
        //点击删除后：
        // 1. 将该值传入列表deletedList中（为后续在数据库中、在删掉这些记录）
        // 3. 在itemList中删除该值，刷新当前界面
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedList.add((CatchItem) getItem(position));
                Long p = getItemId(position);
                catchListTemp.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView tvType;
        TextView tvCoefficient;
        Button btDelete;
    }
}
