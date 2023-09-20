package com.slowly.lookup.adapter;

import com.slowly.lookup.model.ForecastDayHourInfo;

public class HourItem {
    private String icon;
    private ForecastDayHourInfo hourInfo;
    private String hour;

    public HourItem(String icon, ForecastDayHourInfo hourInfo, String hour) {
        this.icon = icon;
        this.hourInfo = hourInfo;
        this.hour = hour;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ForecastDayHourInfo getHourInfo() {
        return hourInfo;
    }

    public void setHourInfo(ForecastDayHourInfo hourInfo) {
        this.hourInfo = hourInfo;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
