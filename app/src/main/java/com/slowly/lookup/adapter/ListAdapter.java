package com.slowly.lookup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.slowly.lookup.R;
import com.slowly.lookup.model.LocationItem;

import java.text.MessageFormat;
import java.util.List;

public class ListAdapter extends ArrayAdapter<LocationItem> {

    private final int layoutResource;

    public ListAdapter(Context context, int resource, List<LocationItem> objects) {
        super(context, resource, objects);
        layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        LocationItem item = getItem(position);

        TextView locationName = convertView.findViewById(R.id.locationName);
        TextView locationDetails = convertView.findViewById(R.id.locationDetails);

        locationName.setText(item.getName());
        String temperature = item.getTemperature() != null ? item.getTemperature().toString() + ", " : "";
        locationDetails.setText(MessageFormat.format("{0}{1}", temperature, item.getCondition()));

        return convertView;
    }
}
