package com.example.liquorshops.utils.elements;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liquorshops.utils.interfaces.OnBackPressedListener;
import com.example.liquorshops.utils.abstracts.BluePrints;


public class BaseActivity extends AppCompatActivity
        implements BluePrints.ofView, BluePrints.ofMap,
        BluePrints.ofActivity, BluePrints.ofSheet, BluePrints.ofFrag, BluePrints.ofRecycler {


    private OnBackPressedListener mOnBackPressedListener;

    @Override
    public void onBackPressed() {
        if (this.mOnBackPressedListener != null) {
            if (!this.mOnBackPressedListener.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void initializeSheet() {

    }

    public void setOnBackPressed(OnBackPressedListener backListener) {
        this.mOnBackPressedListener = backListener;
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeListeners() {

    }

    @Override
    public void initializePicker() {

    }

    @Override
    public void initializeData() {

    }

    @Override
    public void initializeViewModel() {

    }

    @Override
    public void initializeTabView() {

    }

    @Override
    public void initializeRecyclerView() {

    }

    @Override
    public void initializeEmptyView(boolean isEmpty) {

    }

    @Override
    public void initializeFragsView() {

    }

    @Override
    public void closeEverything() {

    }


    @Override
    public void setToolbar() {

    }

    @Override
    public void initializeMapView() {

    }

    @Override
    public void initializeMapThings() {

    }

    @Override
    public void initializeMapListeners() {

    }

    @Override
    public void goToMyLocation() {

    }


    @Override
    public void initializeMarker() {

    }

    @Override
    public void initializeMarkers(Object object) {

    }

    @Override
    public void initializeMapData() {

    }
}
