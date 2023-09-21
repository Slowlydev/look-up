package com.slowly.lookup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.slowly.lookup.services.BackgroundService;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;
import com.slowly.lookup.services.ServiceCallback;
import com.slowly.lookup.services.WeatherService;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.Locale;

import javax.security.auth.callback.Callback;

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

                    TextView locationName = findViewById(R.id.location_detail);
                    TextView feelsLike = findViewById(R.id.feels_like_detail);
                    TextView temperature = findViewById(R.id.temprature_detail);
                    TextView localTime = findViewById(R.id.time_stamp_detail);
                    TextView windDirection = findViewById(R.id.direction_detail);
                    TextView windSpeed = findViewById(R.id.speed_detail);

                    String[] time = weather.getLocation().getLocaltime().split(" ");

                    locationName.setText(weather.getLocation().getName());
                    localTime.setText(time[1]);
                    feelsLike.setText("Feels Like " + String.format(Locale.getDefault(),"%.0fº", weather.getCurrent().getFeelslike_c()));
                    temperature.setText(formatTemp(weather.getCurrent().getTemp_c()));
                    windDirection.setText(weather.getCurrent().getWind_dir());
                    windSpeed.setText(df.format(weather.getCurrent().getWind_kph()));

                    //Hourly Image
                    ImageView weatherConditionEight = findViewById(R.id.image_eight_detail);
                    ImageView weatherConditionNine = findViewById(R.id.image_nine_detail);
                    ImageView weatherConditionTen = findViewById(R.id.image_ten_detail);
                    ImageView weatherConditionEleven = findViewById(R.id.image_eleven_detail);
                    ImageView weatherConditionOne = findViewById(R.id.image_one_detail);
                    ImageView weatherConditionTwo = findViewById(R.id.image_two_detail);
                    ImageView weatherConditionThree = findViewById(R.id.image_three_detail);
                    ImageView weatherConditionFour = findViewById(R.id.image_four_detail);

                    String iconUrlEight = weather.getForecast().getForecastday().get(0).getHour().get(8).getCondition().getIcon();
                    String iconUrlNine = weather.getForecast().getForecastday().get(0).getHour().get(9).getCondition().getIcon();
                    String iconUrlTen = weather.getForecast().getForecastday().get(0).getHour().get(10).getCondition().getIcon();
                    String iconUrlEleven = weather.getForecast().getForecastday().get(0).getHour().get(11).getCondition().getIcon();
                    String iconUrlOne = weather.getForecast().getForecastday().get(0).getHour().get(13).getCondition().getIcon();
                    String iconUrlTwo = weather.getForecast().getForecastday().get(0).getHour().get(14).getCondition().getIcon();
                    String iconUrlThree = weather.getForecast().getForecastday().get(0).getHour().get(15).getCondition().getIcon();
                    String iconUrlFour = weather.getForecast().getForecastday().get(0).getHour().get(16).getCondition().getIcon();


                    Picasso.get().load("https:" + iconUrlEight).into(weatherConditionEight);
                    Picasso.get().load("https:" + iconUrlNine).into(weatherConditionNine);
                    Picasso.get().load("https:" + iconUrlTen).into(weatherConditionTen);
                    Picasso.get().load("https:" + iconUrlEleven).into(weatherConditionEleven);
                    Picasso.get().load("https:" + iconUrlOne).into(weatherConditionOne);
                    Picasso.get().load("https:" + iconUrlTwo).into(weatherConditionTwo);
                    Picasso.get().load("https:" + iconUrlThree).into(weatherConditionThree);
                    Picasso.get().load("https:" + iconUrlFour).into(weatherConditionFour);


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

                    localTemperatureEight.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(8).getTemp_c()));
                    localTemperatureNine.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(9).getTemp_c()));
                    localTemperatureTen.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(10).getTemp_c()));
                    localTemperatureEleven.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(11).getTemp_c()));
                    localTemperatureOne.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(13).getTemp_c()));
                    localTemperatureTwo.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(14).getTemp_c()));
                    localTemperatureThree.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(15).getTemp_c()));
                    localTemperatureFour.setText(formatTemp(weather.getForecast().getForecastday().get(0).getHour().get(16).getTemp_c()));

                } catch (JSONException e) {
//                    System.out.println(e);
                }
            }

            @Override
            public void onError() {

            }
        };

        WeatherService weatherService = new WeatherService();
        weatherService.getWeather(getApplicationContext(), locationName, serviceCallback);
    }

    private String formatTemp(Double temperature) {
        return String.format(Locale.getDefault(),"%.0fº", temperature);
    }
}