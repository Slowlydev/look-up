package com.slowly.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.Service;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

public class DetailedWeatherLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather_loaction);

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

                    TextView locationName = findViewById(R.id.location_detail);
                    TextView temperature = findViewById(R.id.temprature_detail);
                    TextView localTime = findViewById(R.id.time_stamp_detail);
                    TextView windDirection = findViewById(R.id.direction_detail);
                    TextView windSpeed = findViewById(R.id.speed_detail);

                    String[] time = weather.getLocation().getLocaltime().split(" ");

                    locationName.setText(weather.getLocation().getName());
                    localTime.setText(time[1]);
                    temperature.setText(Double.toString(weather.getCurrent().getTemp_c()));
                    windDirection.setText(weather.getCurrent().getWind_dir());
                    windSpeed.setText(Double.toString(weather.getCurrent().getWind_kph()));

                    //Hourly Time
                    TextView localTimeEight = findViewById(R.id.time_eight_detail);
                    TextView localTimeNine = findViewById(R.id.time_nine_detail);
                    TextView localTimeTen = findViewById(R.id.time_ten_detail);
                    TextView localTimeEleven = findViewById(R.id.time_eleven_detail);
                    TextView localTimeOne = findViewById(R.id.time_one_detail);
                    TextView localTimeTwo = findViewById(R.id.time_two_detail);
                    TextView localTimeThree = findViewById(R.id.time_three_detail);
                    TextView localTimeFour = findViewById(R.id.time_four_detail);

                    String[] timeEight = weather.getForecast().getForecastday().get(0).getHour().get(8).getTime().split(" ");
                    String[] timeNine = weather.getForecast().getForecastday().get(0).getHour().get(9).getTime().split(" ");
                    String[] timeTen = weather.getForecast().getForecastday().get(0).getHour().get(10).getTime().split(" ");
                    String[] timeEleven = weather.getForecast().getForecastday().get(0).getHour().get(11).getTime().split(" ");
                    String[] timeOne = weather.getForecast().getForecastday().get(0).getHour().get(13).getTime().split(" ");
                    String[] timeTwo = weather.getForecast().getForecastday().get(0).getHour().get(14).getTime().split(" ");
                    String[] timeThree = weather.getForecast().getForecastday().get(0).getHour().get(15).getTime().split(" ");
                    String[] timeFour = weather.getForecast().getForecastday().get(0).getHour().get(16).getTime().split(" ");

                    localTimeEight.setText(timeEight[1]);
                    localTimeNine.setText(timeNine[1]);
                    localTimeTen.setText(timeTen[1]);
                    localTimeEleven.setText(timeEleven[1]);
                    localTimeOne.setText(timeOne[1]);
                    localTimeTwo.setText(timeTwo[1]);
                    localTimeThree.setText(timeThree[1]);
                    localTimeFour.setText(timeFour[1]);

                    //Hourly Temperature
                    TextView localTemperatureEight = findViewById(R.id.temperature_eight_detail);
                    TextView localTemperatureNine = findViewById(R.id.temperature_nine_detail);
                    TextView localTemperatureTen = findViewById(R.id.temperature_ten_detail);
                    TextView localTemperatureEleven = findViewById(R.id.temperature_eleven_detail);
                    TextView localTemperatureOne = findViewById(R.id.temperature_one_detail);
                    TextView localTemperatureTwo = findViewById(R.id.temperature_two_detail);
                    TextView localTemperatureThree = findViewById(R.id.temperature_three_detail);
                    TextView localTemperatureFour = findViewById(R.id.temperature_four_detail);

                    localTemperatureEight.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(8).getTemp_c()));
                    localTemperatureNine.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(9).getTemp_c()));
                    localTemperatureTen.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(10).getTemp_c()));
                    localTemperatureEleven.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(11).getTemp_c()));
                    localTemperatureOne.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(13).getTemp_c()));
                    localTemperatureTwo.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(14).getTemp_c()));
                    localTemperatureThree.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(15).getTemp_c()));
                    localTemperatureFour.setText(Double.toString(weather.getForecast().getForecastday().get(0).getHour().get(16).getTemp_c()));

                } catch (JSONException e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onError() {

            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(getApplicationContext(), "Muri bei Bern", service);
    }
}