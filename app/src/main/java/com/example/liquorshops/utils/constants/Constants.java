package com.example.liquorshops.utils.constants;

public interface Constants {
    int TIME_DELAYED = 2000;
    String urlApiStart = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
    String urlApiEnd  ="&types=liquor_store&key=AIzaSyCAjOlth62yJGALq0xWr8R7t7EA_wGtRHU";
    String urlImgReferenceApiStart="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    String urlImgReferenceApiEnd="&key=AIzaSyCAjOlth62yJGALq0xWr8R7t7EA_wGtRHU";
    String urlRouteStart="https://maps.googleapis.com/maps/api/directions/json?origin=";
    String urlApiMid="&destination=";
    String urlRouteEnd="&key=AIzaSyCAjOlth62yJGALq0xWr8R7t7EA_wGtRHU";
}
