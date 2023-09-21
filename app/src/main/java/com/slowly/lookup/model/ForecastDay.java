package com.slowly.lookup.model;

import com.slowly.lookup.adapter.DayItem;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class ForecastDay {
    String date;
    long date_epoch;
    ForecastDayInfo day;
    ForecastDayAstroInfo astro;

    public ArrayList<ForecastDayHourInfo> getHour() {
        return hour;
    }

    public void setHour(ArrayList<ForecastDayHourInfo> hour) {
        this.hour = hour;
    }

    ArrayList<ForecastDayHourInfo> hour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(long date_epoch) {
        this.date_epoch = date_epoch;
    }

    public ForecastDayInfo getDay() {
        return day;
    }

    public void setDay(ForecastDayInfo day) {
        this.day = day;
    }

    public ForecastDayAstroInfo getAstro() {
        return astro;
    }

    public void setAstro(ForecastDayAstroInfo astro) {
        this.astro = astro;
    }

    public DayItem toDayItem() {

        String dayString = Instant.ofEpochSecond(date_epoch)
                .atZone(ZoneId.systemDefault())
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL , Locale.getDefault());

        return new DayItem(getDay().getCondition().getIcon(), this, dayString);
    }
}
