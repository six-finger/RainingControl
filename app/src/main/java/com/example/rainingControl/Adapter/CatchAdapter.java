package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;

import java.util.List;

public class CatchAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<CatchItem> itemList;
    public CatchAdapter(Context context, List<CatchItem> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.catch_item, parent, false);
            holder = new ViewHolder();
            holder.tvOrder = convertView.findViewById(R.id.tvOrder);
            holder.tvType = convertView.findViewById(R.id.tvType);
            holder.tvCoefficient = convertView.findViewById(R.id.tvCoefficient);
            holder.etArea = convertView.findViewById(R.id.etArea);
            convertView.setTag(holder);
        }
        //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        CatchItem item = itemList.get(position);
        holder.tvOrder.setText(item.getOrder());
        holder.tvType.setText(item.getType());
        holder.tvCoefficient.setText(item.getCoefficient());
        holder.etArea.setText(item.getArea());
        return convertView;
    }

    private class ViewHolder{
        TextView tvOrder;
        TextView tvType;
        TextView tvCoefficient;
        EditText etArea;
    }
}
