package com.slowly.lookup.parser;

import com.slowly.lookup.model.ForecastDay;
import com.slowly.lookup.model.ForecastDayHourInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastDayParser {
    public static ForecastDay parseForecastDayFromJSONObject(JSONObject jsonObject) throws JSONException {
        ForecastDay forecastDay = new ForecastDay();

        // Extract and set the fields from the JSON object
        forecastDay.setDate(jsonObject.getString("date"));
        forecastDay.setDate_epoch(jsonObject.getLong("date_epoch"));

        // Parse 'day' object using another parser (assuming ForecastDayInfoParser exists)
        if (jsonObject.has("day")) {
            JSONObject dayObject = jsonObject.getJSONObject("day");
            forecastDay.setDay(ForecastDayInfoParser.parseForecastDayInfoFromJSONObject(dayObject));
        }

        // Parse 'astro' object using another parser (assuming ForecastDayAstroInfoParser exists)
        if (jsonObject.has("astro")) {
            JSONObject astroObject = jsonObject.getJSONObject("astro");
            forecastDay.setAstro(ForecastDayAstroInfoParser.parseForecastDayAstroInfoFromJSONObject(astroObject));
        }

        // Parse 'hour' array using another parser (assuming ForecastDayHourInfoParser exists)
        if (jsonObject.has("hour")) {
            JSONArray hourArray = jsonObject.getJSONArray("hour");
            ArrayList<ForecastDayHourInfo> hourInfoArray = new ArrayList<>();
            for (int i = 0; i < hourArray.length(); i++) {
                JSONObject hourObject = hourArray.getJSONObject(i);
                hourInfoArray.add(ForecastDayHourInfoParser.parseForecastDayHourInfoFromJSONObject(hourObject));
            }
            forecastDay.setHour(hourInfoArray);
        }

        return forecastDay;
    }
}
