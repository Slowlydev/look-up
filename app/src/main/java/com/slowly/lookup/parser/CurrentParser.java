package com.slowly.lookup.parser;

import com.slowly.lookup.model.Current;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrentParser {
    public static Current parseCurrentFromJSONObject(JSONObject jsonObject) throws JSONException {
        Current current = new Current();

        current.setLast_updated_epoch(jsonObject.getLong("last_updated_epoch"));
        current.setLast_updated(jsonObject.getString("last_updated"));
        current.setTemp_c(jsonObject.getDouble("temp_c"));
        current.setTemp_f(jsonObject.getDouble("temp_f"));
        current.setIs_day(jsonObject.getInt("is_day"));
        current.setWind_mph(jsonObject.getDouble("wind_mph"));
        current.setWind_kph(jsonObject.getDouble("wind_kph"));
        current.setWind_degree(jsonObject.getInt("wind_degree"));
        current.setWind_dir(jsonObject.getString("wind_dir"));
        current.setPressure_mb(jsonObject.getDouble("pressure_mb"));
        current.setPressure_in(jsonObject.getDouble("pressure_in"));
        current.setPrecip_mm(jsonObject.getDouble("precip_mm"));
        current.setPrecip_in(jsonObject.getDouble("precip_in"));
        current.setHumidity(jsonObject.getInt("humidity"));
        current.setCloud(jsonObject.getInt("cloud"));
        current.setFeelslike_c(jsonObject.getDouble("feelslike_c"));
        current.setFeelslike_f(jsonObject.getDouble("feelslike_f"));
        current.setVis_km(jsonObject.getDouble("vis_km"));
        current.setVis_miles(jsonObject.getDouble("vis_miles"));
        current.setUv(jsonObject.getDouble("uv"));
        current.setGust_mph(jsonObject.getDouble("gust_mph"));
        current.setGust_kph(jsonObject.getDouble("gust_kph"));

        JSONObject conditionObject = jsonObject.getJSONObject("condition");
        current.setCondition(ConditionParser.parseConditionFromJSONObject(conditionObject));

        return current;
    }
}
