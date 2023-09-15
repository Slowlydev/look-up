package com.slowly.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.Service;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

public class WeatherLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_location);

        loadWeather();
    }

    private void loadWeather() {
        Service service = new Service() {
            @Override
            public void onRequest(String response) {
                try {
                    System.out.println(response);

                    Weather weather = WeatherParser.parseWeatherFromString(response);

                    System.out.println(weather);

                    TextView temperature = findViewById(R.id.temperature);
                    temperature.setText(Double.toString(weather.getCurrent().getTemp_c()) +"°");

                    TextView description = findViewById(R.id.description);
                    description.setText(weather.getCurrent().getCondition().getText());

                } catch (JSONException e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onError() {

            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(getApplicationContext(), "Bern", service);
    }
}