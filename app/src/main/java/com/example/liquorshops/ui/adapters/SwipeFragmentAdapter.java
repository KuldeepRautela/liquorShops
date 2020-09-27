package com.example.liquorshops.ui.adapters;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.liquorshops.ui.fragments.FavoriteListFrag;
import com.example.liquorshops.ui.fragments.FeatureFrag;
import com.example.liquorshops.ui.fragments.MainFrag;
import com.example.liquorshops.ui.fragments.ShopListFrag;
import com.example.liquorshops.ui.fragments.UserProfileFrag;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

public class SwipeFragmentAdapter extends FragmentStatePagerAdapter {
    private OnEventOccurListeners listener;
    public ShopListFrag shopListFrag;
    public SwipeFragmentAdapter(@NonNull FragmentManager fm, int behavior, OnEventOccurListeners listener) {
        super(fm, behavior);
        this.listener = listener;
        shopListFrag=new ShopListFrag(listener);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("position ", position + "");
        switch (position) {
            case 0:
                return new MainFrag(listener);
            case 1:
                return new FeatureFrag(listener);
            case 2:
                return new FavoriteListFrag();
            case 3:
                return new UserProfileFrag();
            case 4:
                return shopListFrag;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

}
