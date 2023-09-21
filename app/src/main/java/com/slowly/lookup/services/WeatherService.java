package com.slowly.lookup.services;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WeatherService {

    String blocked_key = "3ba11f09c22b42a184d125133231109";
    String key = "786876c11c65481aa84120549231509";
    String baseUrl = "https://api.weatherapi.com/v1/";

    public void getWeather(Context context, String query, ServiceCallback serviceCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Uri.parse(baseUrl + "forecast.json")
                .buildUpon()
                .appendQueryParameter("q", query)
                .appendQueryParameter("key", key)
                .appendQueryParameter("aqi", "no")
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                serviceCallback::onRequest,
                error -> serviceCallback.onError()
        );

        queue.add(request);
    }

    public void getLocation(Context context, String query, ServiceCallback serviceCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Uri.parse(baseUrl + "search.json")
                .buildUpon()
                .appendQueryParameter("q", query)
                .appendQueryParameter("key", key)
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                serviceCallback::onRequest,
                error -> serviceCallback.onError()
        );

        queue.add(request);
    }

    public void getForecastWithDays(Context context, String query, ServiceCallback serviceCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = Uri.parse(baseUrl + "forecast.json")
                .buildUpon()
                .appendQueryParameter("q", query)
                .appendQueryParameter("key", key)
                .appendQueryParameter("days", "10")
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                serviceCallback::onRequest,
                error -> serviceCallback.onError()
        );

        queue.add(request);
    }
}
