package com.example.liquorshops.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.LiquorModel;
import com.example.liquorshops.ui.adapters.LiquorTypesAdapter;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.ArrayList;

public class LiquorTypesFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liquor_types, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.id_recycler_liquor_type);
        ArrayList<LiquorModel> liquorArrayList = new ArrayList<>();
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_beer), getString(R.string.string_text_drink_beer)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_rum), getString(R.string.string_text_drink_rum)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_wine), getString(R.string.string_text_drink_wine)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_whiskey), getString(R.string.string_text_drink_whisky)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_vodka), getString(R.string.string_text_drink_vodka)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_brandy), getString(R.string.string_text_drink_brandy)));
        liquorArrayList.add(new LiquorModel(getString(R.string.string_text_drink_name_tequila), getString(R.string.string_text_drink_tequila)));
        OnEventOccurListeners onEventOccurListeners = new OnEventOccurListeners() {
            @Override
            public void getEventData(Object object) {
                super.getEventData(object);
                int position = (Integer) object;
                Log.e("position", position + "");
                ArrayList<LiquorModel> liquorBrandsArrayList=new ArrayList<>();
                switch (liquorArrayList.get(position).getLiquorName()) {
                    case "BEER": {
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_beer_kingfisher), R.drawable.kingfisher, getString(R.string.string_text_drink_beer_kingfisher_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_beer_tuborg), R.drawable.tuborg, getString(R.string.string_text_drink_beer_tuborg_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_beer_budweiser), R.drawable.budweiser_bottles, getString(R.string.string_text_drink_beer_budweiser_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_beer_carlsberg), R.drawable.carlsbergbeer, getString(R.string.string_text_drink_beer_carlsberg_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_beer_heineken), R.drawable.heineken, getString(R.string.string_text_drink_beer_heineken_detail)));
                        break;
                    }
                    case "WINE": {
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_wine_sula_rasa), 0, getString(R.string.string_text_drink_wine_sula_rasa_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_wine_myra_misfit),0, getString(R.string.string_text_drink_wine_myra_misfit_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_wine_fratelli_sette), 0, getString(R.string.string_text_drink_wine_fratelli_sette_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_wine_grover), 0, getString(R.string.string_text_drink_wine_grover_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_wine_york_arros),0, getString(R.string.string_text_drink_wine_york_arros_detail)));
                        break;
                    }
                    case "WHISKEY": {
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_whisky_macallan), 0, getString(R.string.string_text_drink_whisky_macallan_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_whisky_monkey),0, getString(R.string.string_text_drink_whisky_monkey_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_whisky_talisker),0, getString(R.string.string_text_drink_whisky_talisker_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_whisky_the_glenlivet),0, getString(R.string.string_text_drink_whisky_the_glenlivet_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_whisky_glenfiddich), 0, getString(R.string.string_text_drink_whisky_glenfiddich_detail)));
                        break;
                    }
                    case "VODKA": {

                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_vodka_magic_moments), 0, getString(R.string.string_text_drink_vodka_magic_moments_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_vodka_romanov), 0, getString(R.string.string_text_drink_vodka_romanov_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_vodka_fuel),0, getString(R.string.string_text_drink_vodka_fuel_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_vodka_vladivar), 0, getString(R.string.string_text_drink_vodka_vladivar_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_vodka_white_mischief), 0, getString(R.string.string_text_drink_vodka_white_mischief_detail)));
                        break;
                    }
                    case "BRANDY": {

                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_brandy_honey_bee), 0, getString(R.string.string_text_drink_brandy_honey_bee_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_brandy_janus),0, getString(R.string.string_text_drink_brandy_janus_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_brandy_mansion),0, getString(R.string.string_text_drink_brandy_mansion_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_brandy_constantino),0, getString(R.string.string_text_drink_brandy_constantino_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_brandy_officer_choice), 0, getString(R.string.string_text_drink_brandy_officer_choice_detail)));
                        break;
                    }
                    case "RUM": {

                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_rum_old_monk), 0, getString(R.string.string_text_drink_rum_old_monk_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_rum_bacardi), 0, getString(R.string.string_text_drink_rum_bacardi_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_rum_malibu), 0, getString(R.string.string_text_drink_rum_malibu_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_rum_captian_morgan), 0, getString(R.string.string_text_drink_rum_captian_morgan_detail)));
                        liquorBrandsArrayList.add(new LiquorModel(getString(R.string.string_text_drink_rum_havana_club), 0, getString(R.string.string_text_drink_rum_havana_club_detail)));
                        break;
                    }
                }
                recyclerView.setAdapter(new LiquorTypesAdapter( liquorBrandsArrayList));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }
        };
        recyclerView.setAdapter(new LiquorTypesAdapter(liquorArrayList, onEventOccurListeners));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }
}
