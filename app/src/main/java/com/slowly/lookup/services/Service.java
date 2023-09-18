package com.slowly.lookup.services;

public interface Service {
    void onRequest(String response);
    void onError();
}
