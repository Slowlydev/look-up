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
import com.slowly.lookup.services.ServiceCallback;
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
        ServiceCallback serviceCallback = new ServiceCallback() {
            @Override
            public void onRequest(String response) {
                try {
                    Weather weather = WeatherParser.parseWeatherFromString(response);

                    TextView temperature = findViewById(R.id.temperature);
                    temperature.setText(String.format(Locale.getDefault(),"%.0fº", weather.getCurrent().getTemp_c()));

                    TextView description = findViewById(R.id.description);
                    description.setText(weather.getCurrent().getCondition().getText());

                    // More detailed info at bottom
                    TextView localTimeEight = findViewById(R.id.wheater_hour_eight);
                    TextView localTimeTen = findViewById(R.id.wheater_hour_ten);
                    TextView localTimeTwo = findViewById(R.id.wheater_hour_two);
                    TextView localTimeFour = findViewById(R.id.wheater_hour_four);

                    String[] timeEight = weather.getForecast().getForecastday().get(0).getHour().get(8).getTime().split(" ");
                    String[] timeTen = weather.getForecast().getForecastday().get(0).getHour().get(10).getTime().split(" ");
                    String[] timeTwo = weather.getForecast().getForecastday().get(0).getHour().get(14).getTime().split(" ");
                    String[] timeFour = weather.getForecast().getForecastday().get(0).getHour().get(16).getTime().split(" ");

                    localTimeEight.setText(timeEight[1]);
                    localTimeTen.setText(timeTen[1]);
                    localTimeTwo.setText(timeTwo[1]);
                    localTimeFour.setText(timeFour[1]);

                    // More detailed info at bottom temp
                    TextView localTempEight = findViewById(R.id.wheater_temp_eight);
                    TextView localTempTen = findViewById(R.id.wheater_temp_ten);
                    TextView localTempTwo = findViewById(R.id.wheater_temp_two);
                    TextView localTempFour = findViewById(R.id.wheater_temp_four);

                    localTempEight.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(8).getTemp_c()));
                    localTempTen.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(10).getTemp_c()));
                    localTempTwo.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(14).getTemp_c()));
                    localTempFour.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(16).getTemp_c()));


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
        weatherService.getWeather(getApplicationContext(), locationName, serviceCallback);
    }

    private String formatTemp(Double temperature) {
        return String.format(Locale.getDefault(),"%.0fº", temperature);
    }
}