package com.slowly.lookup;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentTest {

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor editor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void emptyStateVisible() {
        Set<String> savedLocations = new HashSet<>();
        editor.putStringSet("locations", savedLocations);
        editor.apply();

        System.out.println("should be visible");
        System.out.println(sharedPreferences.getStringSet("locations", null));

        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> {
            TextView emptyState = activity.findViewById(R.id.emptyState);
            assertEquals(View.VISIBLE, emptyState.getVisibility());
            assertEquals("no locations saved :(", emptyState.getText().toString());
        });
        scenario.close();
    }

    @Test
    public void emptyStateGone() {
        Set<String> savedLocations = new HashSet<>(Arrays.asList("Fiesch", "Brig"));
        editor.putStringSet("locations", savedLocations);
        editor.apply();

        System.out.println("should be gone");
        System.out.println(sharedPreferences.getStringSet("locations", null));

        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> {
            TextView emptyState = activity.findViewById(R.id.emptyState);
            assertEquals(View.GONE, emptyState.getVisibility());
        });

        scenario.close();
    }
}
