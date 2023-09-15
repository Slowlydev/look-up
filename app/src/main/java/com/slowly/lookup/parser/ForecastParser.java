package com.slowly.lookup.parser;

import com.slowly.lookup.model.Forecast;
import com.slowly.lookup.model.ForecastDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastParser {
    public static Forecast parseForecastFromJSONObject(JSONObject jsonObject) throws JSONException {
        Forecast forecast = new Forecast();

        JSONArray forecastDayArray = jsonObject.getJSONArray("forecastday");

        ArrayList<ForecastDay> forecastDays = new ArrayList();

        for (int i = 0; i < forecastDayArray.length(); i++) {
            JSONObject forecastDayObject = forecastDayArray.getJSONObject(i);
            forecastDays.add(ForecastDayParser.parseForecastDayFromJSONObject(forecastDayObject));
        }

        forecast.setForecastday(forecastDays);

        return forecast;
    }
}
