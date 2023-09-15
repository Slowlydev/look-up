package com.slowly.lookup.services;

import android.content.ContextWrapper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.slowly.lookup.model.Weather;
import com.slowly.lookup.parser.WeatherParser;

import org.json.JSONException;

public class WeatherService {

    String key = "3ba11f09c22b42a184d125133231109";
    String baseUrl = "https://api.weatherapi.com/v1/";

    public void getWeather(ContextWrapper context, String query) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = baseUrl + "forecast.json?key=" + key;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        queue.add(request);
    }
}
