package com.slowly.lookup.parser;

import com.slowly.lookup.model.ForecastDayHourInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastDayHourInfoParser {
    public static ForecastDayHourInfo parseForecastDayHourInfoFromJSONObject(JSONObject jsonObject) throws JSONException {
        ForecastDayHourInfo hourInfo = new ForecastDayHourInfo();

        // Extract and set the fields from the JSON object
        hourInfo.setTime_epoch(jsonObject.getLong("time_epoch"));
        hourInfo.setTime(jsonObject.getString("time"));
        hourInfo.setTemp_c(jsonObject.getDouble("temp_c"));
        hourInfo.setTemp_f(jsonObject.getDouble("temp_f"));
        hourInfo.setIs_day(jsonObject.getInt("is_day"));

        // Parse 'condition' object using the ConditionParser (assuming it exists)
        if (jsonObject.has("condition")) {
            JSONObject conditionObject = jsonObject.getJSONObject("condition");
            hourInfo.setCondition(ConditionParser.parseConditionFromJSONObject(conditionObject));
        }

        hourInfo.setWind_mph(jsonObject.getDouble("wind_mph"));
        hourInfo.setWind_kph(jsonObject.getDouble("wind_kph"));
        hourInfo.setWind_degree(jsonObject.getInt("wind_degree"));
        hourInfo.setWind_dir(jsonObject.getString("wind_dir"));
        hourInfo.setPressure_mb(jsonObject.getDouble("pressure_mb"));
        hourInfo.setPressure_in(jsonObject.getDouble("pressure_in"));
        hourInfo.setPrecip_mm(jsonObject.getDouble("precip_mm"));
        hourInfo.setPrecip_in(jsonObject.getDouble("precip_in"));
        hourInfo.setHumidity(jsonObject.getInt("humidity"));
        hourInfo.setCloud(jsonObject.getInt("cloud"));
        hourInfo.setFeelslike_c(jsonObject.getDouble("feelslike_c"));
        hourInfo.setFeelslike_f(jsonObject.getDouble("feelslike_f"));
        hourInfo.setWindchill_c(jsonObject.getDouble("windchill_c"));
        hourInfo.setWindchill_f(jsonObject.getDouble("windchill_f"));
        hourInfo.setHeatindex_c(jsonObject.getDouble("heatindex_c"));
        hourInfo.setHeatindex_f(jsonObject.getDouble("heatindex_f"));
        hourInfo.setDewpoint_c(jsonObject.getDouble("dewpoint_c"));
        hourInfo.setDewpoint_f(jsonObject.getDouble("dewpoint_f"));
        hourInfo.setWill_it_rain(jsonObject.getInt("will_it_rain"));
        hourInfo.setChance_of_rain(jsonObject.getInt("chance_of_rain"));
        hourInfo.setWill_it_snow(jsonObject.getInt("will_it_snow"));
        hourInfo.setChance_of_snow(jsonObject.getInt("chance_of_snow"));
        hourInfo.setVis_km(jsonObject.getDouble("vis_km"));
        hourInfo.setVis_miles(jsonObject.getDouble("vis_miles"));
        hourInfo.setGust_mph(jsonObject.getDouble("gust_mph"));
        hourInfo.setGust_kph(jsonObject.getDouble("gust_kph"));
        hourInfo.setUv(jsonObject.getDouble("uv"));

        return hourInfo;
    }
}
