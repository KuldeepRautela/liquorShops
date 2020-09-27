package com.example.liquorshops.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.jetpack.viewmodels.LiquorShopsViewModel;
import com.example.liquorshops.ui.adapters.FavShopAdapter;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.List;

public class FavoriteListFrag extends Fragment {
    FavShopAdapter favShopAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView favShopRecyclerView = view.findViewById(R.id.id_recycler_fav_shop_list);
        ImageView imageView = view.findViewById(R.id.id_image_no_data);

        try {
            LiquorShopsViewModel liquorShopsViewModel = ViewModelProviders.of(this).get(LiquorShopsViewModel.class);
            liquorShopsViewModel.getShopsData().observe(this, new Observer<List<FavoriteShopsModel>>() {
                @Override
                public void onChanged(List<FavoriteShopsModel> favoriteShopsModels) {
                    if (favoriteShopsModels.size() > 0) {
                        Log.e("fav shops ", favoriteShopsModels.get(0).getShopAddress());
                        OnEventOccurListeners onEventOccurListener = new OnEventOccurListeners() {
                            @Override
                            public void getEventData(Object object) {
                                super.getEventData(object);
                                if (object instanceof FavoriteShopsModel) {
                                    Double lat = Double.valueOf(((FavoriteShopsModel) object).getShopLatitude());
                                    Double lng = Double.valueOf(((FavoriteShopsModel) object).getShopLongitude());
                                    ShopListFrag.makePolyline(lat, lng);
                                } else {
                                    int position = (int) object;
                                    liquorShopsViewModel.deleteLiquorShopsData(favoriteShopsModels.get(position));
                                    favoriteShopsModels.remove(position);
                                    if (favShopAdapter.getItemCount() == 0) {
                                        favShopRecyclerView.setVisibility(View.GONE);
                                    }
                                }
                            }
                        };
                        favShopAdapter = new FavShopAdapter(favoriteShopsModels, onEventOccurListener);
                        favShopRecyclerView.setAdapter(favShopAdapter);
                        favShopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception exception) {
            Log.e("exception", exception.getLocalizedMessage());
        }
    }

    void updateAdapter(int position) {
        favShopAdapter.notifyItemRemoved(position);
        favShopAdapter.notifyDataSetChanged();
    }
}
