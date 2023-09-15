package com.slowly.lookup.parser;

import com.slowly.lookup.model.Location;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationParser {
    public static Location parseLocationFromJSONObject(JSONObject jsonObject) throws JSONException {
        Location location = new Location();

        location.setName(jsonObject.getString("name"));
        location.setCountry(jsonObject.getString("country"));
        location.setRegion(jsonObject.getString("region"));
        location.setLocaltime(jsonObject.getString("localtime"));

        location.setLat(jsonObject.getDouble("lat"));
        location.setLon(jsonObject.getDouble("lon"));

        location.setTz_id(jsonObject.getString("tz_id"));

        location.setLocaltime_epoch(jsonObject.getLong("localtime_epoch"));

        return location;
    }
}
