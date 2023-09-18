package com.slowly.lookup.model;

import java.util.ArrayList;

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

}
