package com.slowly.lookup.parser;

import com.slowly.lookup.model.Location;
import com.slowly.lookup.model.Weather;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {
    public static Weather parseWeatherFromString(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);

        Weather weather = new Weather();

        JSONObject locationObject = jsonObject.getJSONObject("location");
        weather.setLocation(LocationParser.parseLocationFromJSONObject(locationObject));

        JSONObject currentObject = jsonObject.getJSONObject("current");
        weather.setCurrent(CurrentParser.parseCurrentFromJSONObject(currentObject));

        JSONObject forecastObject = jsonObject.getJSONObject("forecast");
//        weather.setForecast();

        return weather;
    }
}
