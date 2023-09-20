package com.slowly.lookup.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slowly.lookup.DetailedWeatherLocationActivity;
import com.slowly.lookup.R;
import com.slowly.lookup.model.LocationItem;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ImageView locationIcon = convertView.findViewById(R.id.locationIcon);
        ImageView locationDelete = convertView.findViewById(R.id.loactionDelete);

        locationName.setText(item.getName());
        String temperature = item.getWeather() != null ? item.getWeather().getCurrent().getTemp_c() + ", " : "";
        locationDetails.setText(MessageFormat.format("{0}{1}", temperature, item.getDescription()));

        Picasso.get().load("https:" + item.getWeather().getCurrent().getCondition().getIcon()).into(locationIcon);

        locationDelete.setOnClickListener(v -> {
            SharedPreferences preferences = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            Set<String> savedLocations = preferences.getStringSet("locations", null);
            Set<String> newLocations = new HashSet<>();

            if (savedLocations != null) {
                for (String savedLocation : savedLocations) {
                    if (item.getName() != savedLocation) {
                        newLocations.add(savedLocation);
                    }
                }
            }

            editor.putStringSet("locations", newLocations);
            editor.apply();
        });

        return convertView;
    }
}
