package com.example.liquorshops.jetpack.entities;

import com.google.gson.annotations.SerializedName;

public class PhotoModel {
    @SerializedName("photo_reference")
    private String photo_reference;

    public String getPhoto_reference() {
        return photo_reference;
    }
}