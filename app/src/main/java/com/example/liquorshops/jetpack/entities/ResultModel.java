package com.example.liquorshops.jetpack.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {
    @SerializedName("geometry")
    private GeometryModel geometryModel;

    @SerializedName("name")
    private String name;

    @SerializedName("place_id")
    private String place_id;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @SerializedName("rating")
    private float rating;

    @SerializedName("vicinity")
    private String address;

    @SerializedName("photos")
    private List<PhotoModel> photoModels;

    public GeometryModel getGeometryModel() {
        return geometryModel;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public List<PhotoModel> getPhotoModels() {
        return photoModels;
    }
}