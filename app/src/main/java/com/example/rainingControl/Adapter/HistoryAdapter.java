package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rainingControl.R;

import static com.example.rainingControl.frame.MainActivity.resultSave;

public class HistoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    public HistoryAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (resultSave ==null)
            return 0;
        return resultSave.getTagList().size();
    }

    @Override
    public Object getItem(int position) {
        if (resultSave ==null)
            return null;
        return resultSave.getTagList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_history_all, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        notifyDataSetChanged();
        holder.tvTitle.setText(resultSave.getTagList().get(position));
        holder.tvTitle.setTextSize(20);
        return convertView;
    }

    private class ViewHolder{
        TextView tvTitle;
    }
}
