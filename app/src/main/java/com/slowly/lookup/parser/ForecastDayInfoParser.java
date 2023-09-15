package com.slowly.lookup.parser;

import com.slowly.lookup.model.ForecastDayInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastDayInfoParser {
    public static ForecastDayInfo parseForecastDayInfoFromJSONObject(JSONObject jsonObject) throws JSONException {
        ForecastDayInfo dayInfo = new ForecastDayInfo();

        // Extract and set the fields from the JSON object
        dayInfo.setMaxtemp_c(jsonObject.getDouble("maxtemp_c"));
        dayInfo.setMaxtemp_f(jsonObject.getDouble("maxtemp_f"));
        dayInfo.setMintemp_c(jsonObject.getDouble("mintemp_c"));
        dayInfo.setMintemp_f(jsonObject.getDouble("mintemp_f"));
        dayInfo.setAvgtemp_c(jsonObject.getDouble("avgtemp_c"));
        dayInfo.setAvgtemp_f(jsonObject.getDouble("avgtemp_f"));
        dayInfo.setMaxwind_mph(jsonObject.getDouble("maxwind_mph"));
        dayInfo.setMaxwind_kph(jsonObject.getDouble("maxwind_kph"));
        dayInfo.setTotalprecip_mm(jsonObject.getDouble("totalprecip_mm"));
        dayInfo.setTotalprecip_in(jsonObject.getDouble("totalprecip_in"));
        dayInfo.setTotalsnow_cm(jsonObject.getDouble("totalsnow_cm"));
        dayInfo.setAvgvis_km(jsonObject.getDouble("avgvis_km"));
        dayInfo.setAvgvis_miles(jsonObject.getDouble("avgvis_miles"));
        dayInfo.setAvghumidity(jsonObject.getDouble("avghumidity"));
        dayInfo.setDaily_will_it_rain(jsonObject.getInt("daily_will_it_rain"));
        dayInfo.setDaily_chance_of_rain(jsonObject.getInt("daily_chance_of_rain"));
        dayInfo.setDaily_will_it_snow(jsonObject.getInt("daily_will_it_snow"));
        dayInfo.setDaily_chance_of_snow(jsonObject.getInt("daily_chance_of_snow"));

        // Parse 'condition' object using the ConditionParser (assuming it exists)
        if (jsonObject.has("condition")) {
            JSONObject conditionObject = jsonObject.getJSONObject("condition");
            dayInfo.setCondition(ConditionParser.parseConditionFromJSONObject(conditionObject));
        }

        dayInfo.setUv(jsonObject.getDouble("uv"));

        return dayInfo;
    }
}
