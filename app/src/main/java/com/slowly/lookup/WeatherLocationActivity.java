package com.slowly.lookup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.Service;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.util.Locale;

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

        loadWeather();

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
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadWeather() {
        Service service = new Service() {
            @Override
            public void onRequest(String response) {
                try {
                    Weather weather = WeatherParser.parseWeatherFromString(response);

                    TextView temperature = findViewById(R.id.temperature);
                    temperature.setText(String.format(Locale.getDefault(),"%.0fº", weather.getCurrent().getTemp_c()));

                    TextView description = findViewById(R.id.description);
                    description.setText(weather.getCurrent().getCondition().getText());
                } catch (JSONException e) {
                    // TODO add handling for failed parsing
                }
            }

            @Override
            public void onError() {
                // TODO add handling for missing location
            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(getApplicationContext(), locationName, service);
    }
}