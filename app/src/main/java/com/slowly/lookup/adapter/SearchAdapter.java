package com.slowly.lookup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.slowly.lookup.R;
import com.slowly.lookup.model.SearchLocation;

import java.text.MessageFormat;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<SearchLocation> {

    private final int layoutResource;

    public SearchAdapter(Context context, int resource, List<SearchLocation> objects) {
        super(context, resource, objects);
        layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        SearchLocation item = getItem(position);

        TextView locationName = convertView.findViewById(R.id.locationName);
        TextView locationRegion = convertView.findViewById(R.id.locationRegion);

        locationName.setText(item.getName());
        locationRegion.setText(item.getRegion());

        return convertView;
    }
}
