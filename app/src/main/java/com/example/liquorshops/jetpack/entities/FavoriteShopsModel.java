package com.example.liquorshops.jetpack.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.liquorshops.utils.interfaces.DatabaseKey;

import static com.example.liquorshops.utils.interfaces.DatabaseKey.PLACEID_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPADDRESS_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPIMG_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPLATITUDE_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPLONGITUDE_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPNAME_COLUMN;
import static com.example.liquorshops.utils.interfaces.DatabaseKey.SHOPRATING_COLUMN;

@Entity(tableName = DatabaseKey.FAVSHOPS_TABLE)
public class FavoriteShopsModel {
    @PrimaryKey
    @ColumnInfo(name = PLACEID_COLUMN)
    @NonNull
    private String placeId;
    @ColumnInfo(name = SHOPLATITUDE_COLUMN)
    private String shopLatitude;
    @ColumnInfo(name = SHOPLONGITUDE_COLUMN)
    private String shopLongitude;
    @ColumnInfo(name = SHOPNAME_COLUMN)
    private String shopName;
    @ColumnInfo(name = SHOPIMG_COLUMN)
    private String shopImg;
    @ColumnInfo(name = SHOPADDRESS_COLUMN)
    private String shopAddress;
    @ColumnInfo(name = SHOPRATING_COLUMN)
    private String shopRating;

    public FavoriteShopsModel(String placeId, String shopLatitude, String shopLongitude, String shopName, String shopImg, String shopAddress, String shopRating) {
        this.shopLatitude = shopLatitude;
        this.placeId=placeId;
        this.shopLongitude = shopLongitude;
        this.shopName = shopName;
        this.shopImg = shopImg;
        this.shopAddress = shopAddress;
        this.shopRating = shopRating;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopRating() {
        return shopRating;
    }

    public void setShopRating(String shopRating) {
        this.shopRating = shopRating;
    }

}
