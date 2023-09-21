package com.slowly.lookup;

import static org.junit.Assert.assertEquals;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WeatherLocationActivityInstrumentTest {
    @Test
    public void temperatureVisible() {
        // TODO: figure out how to mock the fetching of data
        ActivityScenario<WeatherLocationActivity> scenario = ActivityScenario.launch(WeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView temperature = activity.findViewById(R.id.temperature);
            assertEquals(View.VISIBLE, temperature.getVisibility());
            assertEquals(42, Integer.parseInt(temperature.getText().toString()));
        });
        scenario.close();
    }

    @Test
    public void descriptionVisible() {
        // TODO: figure out how to mock the fetching of data
        ActivityScenario<WeatherLocationActivity> scenario = ActivityScenario.launch(WeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            TextView description = activity.findViewById(R.id.description);
            assertEquals(View.VISIBLE, description.getVisibility());
            assertEquals("Partly cloudy", description.getText().toString());
        });
        scenario.close();
    }

    @Test
    public void showMoreVisible() {
        ActivityScenario<WeatherLocationActivity> scenario = ActivityScenario.launch(WeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            Button showMore = activity.findViewById(R.id.show_more);
            assertEquals(View.VISIBLE, showMore.getVisibility());
            assertEquals("Show more", showMore.getText().toString());
        });
        scenario.close();
    }

    @Test
    public void hoursVisible() {
        ActivityScenario<WeatherLocationActivity> scenario = ActivityScenario.launch(WeatherLocationActivity.class);

        scenario.onActivity(activity -> {
            RecyclerView hoursRecycler = activity.findViewById(R.id.hoursRecycler);
            assertEquals(View.VISIBLE, hoursRecycler.getVisibility());
            assertEquals(24, hoursRecycler.getChildCount());
        });
        scenario.close();
    }
}
