package com.slowly.lookup.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.slowly.lookup.R;
import com.slowly.lookup.util.TempFormat;
import com.squareup.picasso.Picasso;

import java.util.List;

//@Override
//public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//        convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
//        }
//
//        HourItem item = getItem(position);
//
//        ImageView hourIcon = convertView.findViewById(R.id.hourIcon);
//        TextView hourTime = convertView.findViewById(R.id.hourTime);
//        TextView hourTemp = convertView.findViewById(R.id.hourTemp);
//
//        Picasso.get().load("https:" + item.getIcon()).into(hourIcon);
//
//        hourTime.setText(item.getHour());
//        hourTemp.setText(TempFormat.format(item.getHourInfo().getTemp_c()));
//
//        return convertView;
//        }

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {
    private List<HourItem> dataList; // Replace with your data type

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hourIcon;
        TextView hourTime;
        TextView hourTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            hourIcon = itemView.findViewById(R.id.hourIcon);
            hourTime = itemView.findViewById(R.id.hourTime);
            hourTemp = itemView.findViewById(R.id.hourTemp);

            System.out.println("ViewHolder constructor");
        }
    }

    public HourAdapter(List<HourItem> dataList) {
        this.dataList = dataList;
        System.out.println("HourAdapter constructor");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_weather_item, parent, false);

        System.out.println("onCreateViewHolder");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HourItem item = dataList.get(position);

        Picasso.get().load("https:" + item.getIcon()).into(holder.hourIcon);

        holder.hourTime.setText(item.getHour());
        holder.hourTemp.setText(TempFormat.format(item.getHourInfo().getTemp_c()));

        System.out.println("onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount");
        return dataList.size();
    }

    public void setDataList(List<HourItem> dataList) {
        this.dataList = dataList;
    }
}
