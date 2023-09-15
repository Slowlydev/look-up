package com.slowly.lookup.services;

public interface Service {
    public void onRequest(String response);
    public void onError();
}
