package com.example.liquorshops.jetpack.entities;

import com.google.gson.annotations.SerializedName;
public class GeometryModel {

    @SerializedName("location")
    private LocationModel locationModel;

    public LocationModel getLocationModel() {
        return locationModel;
    }
}