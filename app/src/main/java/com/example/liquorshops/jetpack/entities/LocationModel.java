package com.example.liquorshops.jetpack.entities;

import com.google.gson.annotations.SerializedName;

public class LocationModel
{
    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public LocationModel(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}