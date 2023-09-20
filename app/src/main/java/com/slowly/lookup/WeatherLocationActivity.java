package com.slowly.lookup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.slowly.lookup.adapter.HourAdapter;
import com.slowly.lookup.adapter.HourItem;
import com.slowly.lookup.model.ForecastDayHourInfo;
import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.BackgroundService;
import com.slowly.lookup.services.ServiceCallback;
import com.slowly.lookup.services.WeatherService;
import com.slowly.lookup.util.TempFormat;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WeatherLocationActivity extends AppCompatActivity {

    private String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_location);

        Intent intent = getIntent();
        locationName = intent.getStringExtra("locationName");
        setTitle(locationName);

        // TODO add handling for missing location

//        loadWeather();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button showMoreButton = findViewById(R.id.show_more);
        showMoreButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), DetailedWeatherLocationActivity.class);
            intent1.putExtra("locationName", locationName);
            startActivity(intent1);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWeather();
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

    private void loadWeather() {
        ServiceCallback serviceCallback = new ServiceCallback() {
            @Override
            public void onRequest(String response) {
                try {
                    Weather weather = WeatherParser.parseWeatherFromString(response);

                    BackgroundService.setBackground(findViewById(R.id.backgroundImage), weather.getLocation());

                    TextView temperature = findViewById(R.id.temperature);
                    temperature.setText(TempFormat.format(weather.getCurrent().getTemp_c()));

                    TextView description = findViewById(R.id.description);
                    description.setText(weather.getCurrent().getCondition().getText());


                    List<HourItem> hours = weather.getForecast().getForecastday().get(0).getHour().stream().map(ForecastDayHourInfo::toHourItem).collect(Collectors.toList());

                    // This is the biggest Joke, this adapter for the RecycleView doesn't update on array changes, you need to pass the final array.
                    // And all this time they teach you the other adapters that do react...
                    HourAdapter adapter = new HourAdapter(hours);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

                    RecyclerView hoursRecyclerView = findViewById(R.id.hoursRecycler);
                    hoursRecyclerView.setLayoutManager(layoutManager);
                    hoursRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    // TODO add handling for failed parsing
                    System.out.println("JSON parse error");
                }
            }

            @Override
            public void onError() {
                // TODO add handling for missing location
            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(getApplicationContext(), locationName, serviceCallback);
    }
}