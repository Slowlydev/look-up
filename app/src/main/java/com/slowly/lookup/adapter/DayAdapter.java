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

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private List<DayItem> dataList; // Replace with your data type

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hourIcon;
        TextView hourTime;
        TextView hourTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            hourIcon = itemView.findViewById(R.id.hourIcon);
            hourTime = itemView.findViewById(R.id.hourTime);
            hourTemp = itemView.findViewById(R.id.hourTemp);
        }
    }

    public DayAdapter(List<DayItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_weather_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DayItem item = dataList.get(position);

        Picasso.get().load("https:" + item.getIcon()).into(holder.hourIcon);

        holder.hourTime.setText(item.getDay());
        holder.hourTemp.setText(TempFormat.format(item.getForecastDay().getDay().getAvgtemp_c()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDataList(List<DayItem> dataList) {
        this.dataList = dataList;
    }
}
