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

import static com.example.rainingControl.frame.CatchActivity.catchList;

public class CatchAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private CatchItem catchItem;

    public CatchAdapter(Context context, List<CatchItem> list) {
        this.inflater = LayoutInflater.from(context);
        catchList = list;
    }

    @Override
    public int getCount() {
        return catchList.size();
    }

    @Override
    public Object getItem(int position) {
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
        catchItem = catchList.get(position);
        holder.tvType.setText(catchItem.getType());
        holder.tvCoefficient.setText(catchItem.getCoefficient());
        return convertView;
    }

    private class ViewHolder{
        TextView tvType;
        TextView tvCoefficient;
    }
}
