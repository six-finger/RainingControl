package com.example.rainingControl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.LidItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.frame.LidActivity.lidList;

public class LidAdapter extends BaseAdapter{
    private LayoutInflater inflater;

    public LidAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lidList.size();
    }

    @Override
    public Object getItem(int position) {
        return lidList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_lid,parent,false);
            holder = new ViewHolder();
            holder.lidType = convertView.findViewById(R.id.showType);
            holder.lidName = convertView.findViewById(R.id.showName);
            holder.area = convertView.findViewById(R.id.showArea);
            holder.depth = convertView.findViewById(R.id.showDepth);
            holder.ratio = convertView.findViewById(R.id.showRatio);
            holder.volumeControl = convertView.findViewById(R.id.showVolumeControl);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        notifyDataSetChanged();
        LidItem item = lidList.get(position);
        holder.lidType.setText(item.getLidType());
        holder.lidName.setText(item.getLidName());
        holder.area.setText(String.valueOf(item.getArea()));
        holder.depth.setText(String.valueOf(item.getDepth()));
        holder.ratio.setText(String.valueOf(item.getRatio()));
        holder.volumeControl.setText(String.valueOf(item.getVolume()));
        return convertView;
    }

    private class ViewHolder{
        TextView lidType, lidName, area, depth, ratio, volumeControl;
    }
}
