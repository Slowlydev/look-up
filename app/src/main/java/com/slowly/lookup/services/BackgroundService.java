package com.slowly.lookup.services;

import android.widget.ImageView;

import com.slowly.lookup.R;
import com.slowly.lookup.model.Location;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// This class is for dynamic background loading
public class BackgroundService {
    public static void setBackground(ImageView backgroundImage, Location location) {

        Long unixSeconds = location.getLocaltime_epoch();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixSeconds), ZoneId.of(location.getTz_id()));
        Integer hour = localDateTime.getHour();

        if (hour >= 6 && hour < 12) {
            backgroundImage.setImageResource(R.drawable.sunrise_background);
        } else if (hour >= 12 && hour < 18) {
            backgroundImage.setImageResource(R.drawable.day_background);
        } else if (hour >= 18 && hour < 21) {
            backgroundImage.setImageResource(R.drawable.sunset_background);
        } else {
            backgroundImage.setImageResource(R.drawable.evening_background);
        }
    }
}
