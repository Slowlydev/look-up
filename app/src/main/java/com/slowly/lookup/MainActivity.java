package com.slowly.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.slowly.lookup.adapter.ListAdapter;
import com.slowly.lookup.model.LocationItem;
import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.Service;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView locations = findViewById(R.id.locations);
        TextView emptyState = findViewById(R.id.emptyState);
        List<LocationItem> locationItems = new ArrayList<>();

        ArrayAdapter<LocationItem> locationsAdapter = new ListAdapter(this, R.layout.activity_main_list_item, locationItems);
        locations.setAdapter(locationsAdapter);

        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("locations", new HashSet<>(Arrays.asList("Fiesch", "Brig", "Zermatt", "Engelberg", "Chur", "Fruttigen", "Chamonix")));
        editor.apply();

        Set<String> savedLocations = preferences.getStringSet("locations", null);
        System.out.println(savedLocations);

        if (savedLocations == null || savedLocations.isEmpty()) {
            emptyState.setText("no locations saved :(");
        }

        AdapterView.OnItemClickListener onItemClick = (parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), WeatherLocationActivity.class);
            LocationItem selected = (LocationItem)parent.getItemAtPosition(position);
            intent.putExtra("locationName", selected.getName());
            startActivity(intent);
        };
        locations.setOnItemClickListener(onItemClick);

        if (savedLocations != null) {
            for (String location : savedLocations) {
                Service service = new Service() {
                    @Override
                    public void onRequest(String response) {
                        try {
                            Weather weather = WeatherParser.parseWeatherFromString(response);
                            Double temperature = weather.getCurrent().getTemp_c();
                            String condition = weather.getCurrent().getCondition().getText();
                            locationItems.add(new LocationItem(location, temperature, condition));
                            locationsAdapter.notifyDataSetChanged();
                        } catch (JSONException err) {
                            String error = "failed to fetch weather :(";
                            System.err.println(error);
                            locationItems.add(new LocationItem(location, null, error));
                            locationsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError() {
                        String error = "failed to fetch weather :(";
                        System.err.println(error);
                        locationItems.add(new LocationItem(location, null, error));
                        locationsAdapter.notifyDataSetChanged();
                    }
                };

                new WeatherService().getWeather(getApplicationContext(), location, service);
            }
        }
    }
}
