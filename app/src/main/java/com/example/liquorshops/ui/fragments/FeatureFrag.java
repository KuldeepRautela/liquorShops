package com.example.liquorshops.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.liquorshops.R;
import com.example.liquorshops.utils.OnClick;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;
import com.example.liquorshops.utils.elements.BaseFragment;


public class FeatureFrag extends BaseFragment implements View.OnClickListener {
    private OnEventOccurListeners listener;

    public FeatureFrag(OnEventOccurListeners listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView advisorTextView, cocktailTextView, hangoverTextView, prosAndConsTextView, liquorTypesTextView, planTextView;
        advisorTextView = view.findViewById(R.id.id_text_advisor);
        cocktailTextView = view.findViewById(R.id.id_text_cocktail);
        hangoverTextView = view.findViewById(R.id.id_text_hangover);
        prosAndConsTextView = view.findViewById(R.id.id_text_pros_cons);
        liquorTypesTextView = view.findViewById(R.id.id_text_liquor);
        advisorTextView.setOnClickListener(this);
        cocktailTextView.setOnClickListener(this);
        hangoverTextView.setOnClickListener(this);
        prosAndConsTextView.setOnClickListener(this);
        liquorTypesTextView.setOnClickListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.id_frame_container, new AdvisorFrag()).addToBackStack(null).commit();
    }

    @Override
    public void onClick(View view) {
        Log.e("onclick", "happened");
        FragmentManager fragmentManager = getChildFragmentManager();
        switch (view.getId()) {
            case R.id.id_text_advisor:
                listener.getEventData("Advisor");
                fragmentManager.beginTransaction().replace(R.id.id_frame_container, new AdvisorFrag()).commit();
                break;
            case R.id.id_text_cocktail:
                listener.getEventData("Cocktail");
                fragmentManager.beginTransaction().replace(R.id.id_frame_container, new CocktailFrag()).commit();
                break;
            case R.id.id_text_liquor:
                listener.getEventData("Liquor Types");
                fragmentManager.beginTransaction().replace(R.id.id_frame_container, new LiquorTypesFrag()).commit();
                break;
            case R.id.id_text_pros_cons:
                listener.getEventData("ProsAndCons");
                fragmentManager.beginTransaction().replace(R.id.id_frame_container, new ProsAndConsFrag()).commit();
                break;
            case R.id.id_text_hangover:
                listener.getEventData("Hangover");
                fragmentManager.beginTransaction().replace(R.id.id_frame_container, new HangoverFrag()).commit();
                break;
        }
    }


}
