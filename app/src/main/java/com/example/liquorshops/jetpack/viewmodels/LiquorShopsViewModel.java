package com.example.liquorshops.jetpack.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.jetpack.repo.LiquorShopsRepo;

import java.util.List;

public class LiquorShopsViewModel extends ViewModel {
    private LiquorShopsRepo liquorShopsRepo = new LiquorShopsRepo();

    public LiveData<List<FavoriteShopsModel>> getShopsData() {
        return liquorShopsRepo.getShopsData();
    }

    public void insertLiquorShopsData(FavoriteShopsModel favShopModel) {
        liquorShopsRepo.insertLiquorShopsData(favShopModel);
    }
    public void deleteLiquorShopsData(FavoriteShopsModel favShopModel) {
        liquorShopsRepo.deleteLiquorShopData(favShopModel);
    }
}
