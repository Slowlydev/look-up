package com.slowly.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.slowly.lookup.adapter.ListAdapter;
import com.slowly.lookup.adapter.LocationItem;
import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.NetworkUtils;
import com.slowly.lookup.services.Service;
import com.slowly.lookup.services.ServiceCallback;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocationWeather();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private void loadLocationWeather() {
        ListView locations = findViewById(R.id.locations);
        TextView emptyState = findViewById(R.id.emptyState);
        TextView errorState = findViewById(R.id.errorState);
        List<LocationItem> locationItems = new ArrayList<>();

        ArrayAdapter<LocationItem> locationsAdapter = new ListAdapter(this, R.layout.activity_main_list_item, locationItems);
        locations.setAdapter(locationsAdapter);

        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Set<String> savedLocations = preferences.getStringSet("locations", null);

        if (savedLocations == null || savedLocations.isEmpty()) {
            emptyState.setText(R.string.no_locations);
        } else {
            emptyState.setVisibility(View.GONE);
        }

        locations.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), WeatherLocationActivity.class);
            LocationItem selected = (LocationItem) parent.getItemAtPosition(position);
            intent.putExtra("locationName", selected.getName());
            startActivity(intent);
        });

        if (savedLocations != null) {
            for (String location : savedLocations) {
                ServiceCallback serviceCallback = new ServiceCallback() {
                    @Override
                    public void onRequest(String response) {
                        try {
                            Weather weather = WeatherParser.parseWeatherFromString(response);
                            locationItems.add(new LocationItem(location, weather, weather.getCurrent().getCondition().getText()));
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

                new WeatherService().getWeather(getApplicationContext(), location, serviceCallback);
            }
        }

        if (!NetworkUtils.connected(this)) {
            errorState.setText(R.string.no_network);
        } else {
            errorState.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private SharedPreferences.OnSharedPreferenceChangeListener listener =
            (prefs, key) -> {
                if (key.equals("locations")) {
                    loadLocationWeather();
                }
            };

}
