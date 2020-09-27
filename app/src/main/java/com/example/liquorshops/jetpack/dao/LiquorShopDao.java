package com.example.liquorshops.jetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.utils.interfaces.DatabaseKey;

import java.util.List;

@Dao
public interface LiquorShopDao {
    @Query("Select * from "+ DatabaseKey.FAVSHOPS_TABLE)
     LiveData<List<FavoriteShopsModel>> getLiqourShops();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShopDetails(FavoriteShopsModel shopModel);
    @Update
    void updateShopDetails(FavoriteShopsModel shopModel);
    @Delete
    void  deleteShopDetails(FavoriteShopsModel shopModel);
}
