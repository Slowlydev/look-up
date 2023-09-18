package com.slowly.lookup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.slowly.lookup.adapter.ListAdapter;
import com.slowly.lookup.adapter.SearchAdapter;
import com.slowly.lookup.model.LocationItem;
import com.slowly.lookup.model.SearchLocation;
import com.slowly.lookup.parser.SearchLocationParser;
import com.slowly.lookup.services.ServiceCallback;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        setTitle("Search Results");

        handleIntent(getIntent());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        super.onNewIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchLocation(query);
        }
    }

    private void searchLocation(String query) {
        ServiceCallback serviceCallback = new ServiceCallback() {
            @Override
            public void onRequest(String response) {
                try {
                    ArrayList<SearchLocation> searchLocations = SearchLocationParser.parseSearchLocationFromString(response);

                    ListView searchResults = findViewById(R.id.search_results);
                    ArrayAdapter<SearchLocation> searchAdapter = new SearchAdapter(getApplicationContext(), R.layout.activity_search_result_item, searchLocations);
                    searchResults.setAdapter(searchAdapter);

                    searchResults.setOnItemClickListener((parent, view, position, id) -> {
                        SearchLocation selected = (SearchLocation)parent.getItemAtPosition(position);

                        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        Set<String> savedLocations = preferences.getStringSet("locations", null);
                        Set<String> newLocations = new HashSet<>();

                        for (String location : savedLocations) {
                            newLocations.add(location);
                        }

                        newLocations.add(selected.getName());

                        editor.putStringSet("locations", newLocations);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), WeatherLocationActivity.class);
                        intent.putExtra("locationName", selected.getName());
                        startActivity(intent);
                    });

                    System.out.println(response);
                } catch (JSONException e) {
                    System.out.println("Error!");

                }
            }

            @Override
            public void onError() {
                System.out.println("Error");
            }
        };

        new WeatherService().getLocation(getApplicationContext(), query, serviceCallback);
    }
}