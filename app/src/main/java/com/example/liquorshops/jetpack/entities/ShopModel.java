package com.example.liquorshops.jetpack.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopModel {
    @SerializedName("results")
    private List<ResultModel> results;
    @SerializedName("status")
    private String status;
    @SerializedName("place_id")
    private String place_id;
    public String getStatus() {
        return status;
    }

    public List<ResultModel> getResults() {
        return results;
    }
}



