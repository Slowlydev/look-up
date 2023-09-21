package com.slowly.lookup;

import static org.junit.Assert.assertEquals;

import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetailedWeatherLocationActivityInstrumentTest {
    @Test
    public void locationNameVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView locationName = activity.findViewById(R.id.location_detail);
            assertEquals(View.VISIBLE, locationName.getVisibility());
        });
        scenario.close();
    }

    @Test
    public void feelsLikeVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView feelsLike = activity.findViewById(R.id.feels_like_detail);
            assertEquals(View.VISIBLE, feelsLike.getVisibility());
        });
        scenario.close();
    }

    @Test
    public void temperatureVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView temperature = activity.findViewById(R.id.temperature_detail);
            assertEquals(View.VISIBLE, temperature.getVisibility());
        });
        scenario.close();
    }

    @Test
    public void localTimeVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView localTime = activity.findViewById(R.id.time_stamp_detail);
            assertEquals(View.VISIBLE, localTime.getVisibility());
        });
        scenario.close();
    }

    @Test
    public void windDirectionVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView windDirection = activity.findViewById(R.id.direction_detail);
            assertEquals(View.VISIBLE, windDirection.getVisibility());
        });
        scenario.close();
    }

    @Test
    public void windSpeedVisible() {
        ActivityScenario<DetailedWeatherLocationActivity> scenario = ActivityScenario.launch(DetailedWeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView windSpeed = activity.findViewById(R.id.speed_detail);
            assertEquals(View.VISIBLE, windSpeed.getVisibility());
        });
        scenario.close();
    }
}
