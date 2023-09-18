package com.slowly.lookup.model;

public class LocationItem {
    private String name;
    private Double temperature;
    private String condition;

    public LocationItem(String name, Double temperature, String condition) {
        this.name = name;
        this.temperature = temperature;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
