package com.slowly.lookup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.slowly.lookup.adapter.DayAdapter;
import com.slowly.lookup.adapter.DayItem;
import com.slowly.lookup.model.ForecastDay;
import com.slowly.lookup.services.BackgroundService;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.ServiceCallback;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DetailedWeatherLocationActivity extends AppCompatActivity {

    private String locationName;
    private static final DecimalFormat df = new DecimalFormat("0.0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather_loaction);

        Intent intent = getIntent();
        locationName = intent.getStringExtra("locationName");
        setTitle(locationName);

        loadWeather();

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

    private void loadWeather() {
        ServiceCallback serviceCallback = new ServiceCallback() {
            @Override
            public void onRequest(String response) {
                try {
                    Weather weather = WeatherParser.parseWeatherFromString(response);

                    BackgroundService.setBackground(findViewById(R.id.backgroundImage), weather.getLocation());

                    // Find element in xml View
                    TextView locationName = findViewById(R.id.location_detail);
                    TextView feelsLike = findViewById(R.id.feels_like_detail);
                    TextView temperature = findViewById(R.id.temprature_detail);
                    TextView localTime = findViewById(R.id.time_stamp_detail);
                    TextView windDirection = findViewById(R.id.direction_detail);
                    TextView windSpeed = findViewById(R.id.speed_detail);
                    TextView uvLevel = findViewById(R.id.uv_detail);

                    String[] time = weather.getLocation().getLocaltime().split(" ");

                    // Set Text to found xml Views
                    locationName.setText(weather.getLocation().getName());
                    localTime.setText(time[1]);
                    feelsLike.setText("Feels Like " + String.format(Locale.getDefault(),"%.0fº", weather.getCurrent().getFeelslike_c()));
                    temperature.setText(formatTemp(weather.getCurrent().getTemp_c()));
                    windDirection.setText(weather.getCurrent().getWind_dir());
                    windSpeed.setText(df.format(weather.getCurrent().getWind_kph()));
                    uvLevel.setText(String.format("%.0f", weather.getCurrent().getUv()));

                    List<DayItem> days = weather.getForecast().getForecastday().stream().map(ForecastDay::toDayItem).collect(Collectors.toList());

                    DayAdapter adapter = new DayAdapter(days);
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
                    RecyclerView daysRecycleView = findViewById(R.id.dayRecycler);
                    daysRecycleView.setLayoutManager(layoutManager);
                    daysRecycleView.setAdapter(adapter);

                } catch (JSONException e) {
//                    System.out.println(e);
                }
            }

            @Override
            public void onError() {

            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getForecastWithDays(getApplicationContext(), locationName, serviceCallback);
    }

    private String formatTemp(Double temperature) {
        return String.format(Locale.getDefault(),"%.0fº", temperature);
    }
}