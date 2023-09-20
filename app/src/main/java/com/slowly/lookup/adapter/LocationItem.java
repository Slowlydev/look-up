package com.slowly.lookup.adapter;

import com.slowly.lookup.model.Weather;

public class LocationItem {
    private String name;

    private Weather weather;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public LocationItem(String name, Weather weather, String description) {
        this.name = name;
        this.weather = weather;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
