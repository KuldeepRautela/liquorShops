package com.example.liquorshops.jetpack.entities;

public class LiquorModel {
    private String liquorName;
    private int liquorImg;
    private String liquorDetails;
    private int viewType;
    public String getLiquorName() {
        return liquorName;
    }

    public int getLiquorImg() {
        return liquorImg;
    }

    public String getLiquorDetails() {
        return liquorDetails;
    }

    public int getViewType() {
        return viewType;
    }

    public LiquorModel(String liquorName, int liquorImg, String liquorDetails) {
        this.liquorName = liquorName;
        this.liquorImg = liquorImg;
        this.liquorDetails = liquorDetails;
        viewType=1;
    }

    public LiquorModel(String liquorName, String liquorDetails) {
        this.liquorName = liquorName;
        this.liquorDetails = liquorDetails;
        viewType=0;
    }
}
