package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.CatchItem;

import java.util.List;

import static com.example.rainingControl.frame.CatchActivity.itemList;

public class CatchAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    public CatchAdapter(Context context, List<CatchItem> list) {
        this.inflater = LayoutInflater.from(context);
        itemList = list;
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
            holder.tvType = convertView.findViewById(R.id.tvType);
            holder.tvCoefficient = convertView.findViewById(R.id.tvCoefficient);
            convertView.setTag(holder);
        }
        //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        notifyDataSetChanged();
        CatchItem item = itemList.get(position);
        holder.tvType.setText(item.getType());
        holder.tvCoefficient.setText(item.getCoefficient());
        return convertView;
    }

    private class ViewHolder{
        TextView tvType;
        TextView tvCoefficient;
    }
}
