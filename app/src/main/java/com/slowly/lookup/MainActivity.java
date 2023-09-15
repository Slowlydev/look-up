package com.slowly.lookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        // TODO do more with progress bar and do the list population wit the locations
    }

    private void addLocations() {
        ListView locations = findViewById(R.id.locations);
        // ArrayAdapter<>
    }
}