package com.slowly.lookup.adapter;

import com.slowly.lookup.model.ForecastDay;
import com.slowly.lookup.model.ForecastDayHourInfo;

public class DayItem {
    private String icon;
    private ForecastDay forecastDay;
    private String day;

    public DayItem(String icon, ForecastDay forecastDay, String day) {
        this.icon = icon;
        this.forecastDay = forecastDay;
        this.day = day;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ForecastDay getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(ForecastDay forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
