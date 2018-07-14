package com.example.rainingControl.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rainingControl.R;
import com.example.rainingControl.util.LidItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.rainingControl.frame.LidActivity.lidList;

public class LidRemoveAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<LidItem> lidListTemp;

    public LidRemoveAdapter(Context context, List<LidItem> lidListTemp) {
        this.inflater = LayoutInflater.from(context);
        this.lidListTemp = lidListTemp;
    }
    @Override
    public int getCount() {
        return lidListTemp.size();
    }

    @Override
    public Object getItem(int position) {
        return lidListTemp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_lid_remove,parent,false);
            holder = new ViewHolder();
            holder.lidType = convertView.findViewById(R.id.showType);
            holder.lidName = convertView.findViewById(R.id.showName);
            holder.area = convertView.findViewById(R.id.showArea);
            holder.depth = convertView.findViewById(R.id.showDepth);
            holder.ratio = convertView.findViewById(R.id.showRatio);
            holder.btDelete = convertView.findViewById(R.id.btDelete);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        notifyDataSetChanged();
        LidItem item = lidListTemp.get(position);
        holder.lidType.setText(item.getLidType());
        holder.lidName.setText(item.getLidName());
        holder.area.setText(String.valueOf(item.getArea()));
        holder.depth.setText(String.valueOf(item.getDepth()));
        holder.ratio.setText(String.valueOf(item.getRatio()));
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lidListTemp.remove(position);
                notifyDataSetChanged();
                Log.e("sf", "onClick: "+lidListTemp);
                Log.e("sg", "onClick: "+lidList);
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView lidType, lidName, area, depth, ratio;
        Button btDelete;
    }
}

