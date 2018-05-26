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
    private List<CatchItem> itemList;
    public static List<String> deletedTypeList = new ArrayList<>();            //将所有 待删除的类型 放入该List中

    public CatchRemoveAdapter(Context context, List<CatchItem> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }

    //得到position项的Type值（数据库中该条记录的主键）
    public String getItemKey(int position) {
        return itemList.get(position).getType();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
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
        CatchItem item = itemList.get(position);
        holder.tvType.setText(item.getType());
        holder.tvCoefficient.setText(item.getCoefficient());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedTypeList.add(getItemKey(position));
                itemList.remove(position);
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
