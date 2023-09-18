package com.slowly.lookup.services;

public interface ServiceCallback {
    void onRequest(String response);
    void onError();
}
