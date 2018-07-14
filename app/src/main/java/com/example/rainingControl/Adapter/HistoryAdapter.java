package com.example.rainingControl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.ListDataSave;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ListDataSave resultSave;
    private Context context;

    public HistoryAdapter(Context context, ListDataSave resultSave) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.resultSave = resultSave;
    }

    @Override
    public int getCount() {
        return resultSave.getTagList().size();
    }

    @Override
    public Object getItem(int position) {
        return resultSave.getTagList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_history_all, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        notifyDataSetChanged();
        holder.tvTitle.setText(resultSave.getTagList().get(position));
        holder.tvTitle.setHeight(80);
        holder.tvTitle.setGravity(Gravity.CENTER_VERTICAL);
        return convertView;
    }

    private class ViewHolder{
        TextView tvTitle;
    }
}
