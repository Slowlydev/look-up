package com.slowly.lookup.parser;

import com.slowly.lookup.model.ForecastDayAstroInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastDayAstroInfoParser {
    public static ForecastDayAstroInfo parseForecastDayAstroInfoFromJSONObject(JSONObject jsonObject) throws JSONException {
        ForecastDayAstroInfo astroInfo = new ForecastDayAstroInfo();

        // Extract and set the fields from the JSON object
        astroInfo.setSunrise(jsonObject.getString("sunrise"));
        astroInfo.setSunset(jsonObject.getString("sunset"));
        astroInfo.setMoonrise(jsonObject.getString("moonrise"));
        astroInfo.setMoonset(jsonObject.getString("moonset"));
        astroInfo.setMoon_phase(jsonObject.getString("moon_phase"));
        astroInfo.setMoon_illumination(jsonObject.getString("moon_illumination"));
        astroInfo.setIs_moon_up(jsonObject.getInt("is_moon_up"));
        astroInfo.setIs_sun_up(jsonObject.getInt("is_sun_up"));

        return astroInfo;
    }
}
