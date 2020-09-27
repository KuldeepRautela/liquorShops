package com.example.liquorshops.jetpack.repo;

import androidx.lifecycle.LiveData;

import com.example.liquorshops.jetpack.dao.LiquorShopDao;
import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.jetpack.room.LiquorShopsDatabase;

import java.util.List;

public class LiquorShopsRepo  extends Exception{
   private LiquorShopDao liquorShopDao = LiquorShopsDatabase.getDatabaseInstance().getLiquorShopsDao();
    public LiveData<List<FavoriteShopsModel>> getShopsData(){
       return liquorShopDao.getLiqourShops();
    }
    public void insertLiquorShopsData(FavoriteShopsModel favShopModel){
        new Thread(){
            @Override
            public void run() {
                super.run();
                liquorShopDao.insertShopDetails(favShopModel);
            }
        }.start();
    }
    public void deleteLiquorShopData(FavoriteShopsModel favoriteShopsModel){
        new Thread(){
            @Override
            public void run() {
                super.run();
                liquorShopDao.deleteShopDetails(favoriteShopsModel);
            }
        }.start();
    }
}
