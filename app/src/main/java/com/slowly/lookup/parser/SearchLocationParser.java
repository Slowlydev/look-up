package com.slowly.lookup.parser;

import com.slowly.lookup.model.SearchLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchLocationParser {
    public static ArrayList<SearchLocation> parseSearchLocationFromString(String jsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonString);

        ArrayList<SearchLocation> locations = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            SearchLocation searchLocation = new SearchLocation();

            searchLocation.setId(jsonObject.getString("id"));
            searchLocation.setName(jsonObject.getString("name"));
            searchLocation.setRegion(jsonObject.getString("region"));
            searchLocation.setCountry(jsonObject.getString("country"));
            searchLocation.setUrl(jsonObject.getString("url"));
            searchLocation.setLat(jsonObject.getDouble("lat"));
            searchLocation.setLon(jsonObject.getDouble("lon"));

            locations.add(searchLocation);
        }

        return locations;
    }
}
