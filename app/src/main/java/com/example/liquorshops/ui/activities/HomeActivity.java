package com.example.liquorshops.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.LocationModel;
import com.example.liquorshops.jetpack.entities.ResultModel;
import com.example.liquorshops.ui.adapters.SwipeFragmentAdapter;
import com.example.liquorshops.ui.fragments.FeatureFrag;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;
import com.example.liquorshops.utils.elements.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private OnEventOccurListeners listener;
    private TextView titleTextView;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private SwipeFragmentAdapter swipeFragmentAdapter;

    @Override
    public void setToolbar() {
        super.setToolbar();
        Toolbar toolbar = findViewById(R.id.id_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        titleTextView = findViewById(R.id.id_text_title);
        bottomNavigationView = findViewById(R.id.id_bottom_navigation);
        viewPager = findViewById(R.id.id_view_pager);
        swipeFragmentAdapter = new SwipeFragmentAdapter(getSupportFragmentManager(), 5, listener);
        viewPager.setAdapter(swipeFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.id_menu_item_map);
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        new FeatureFrag(listener);
                        bottomNavigationView.setSelectedItemId(R.id.id_menu_item_features);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.id_menu_item_favorites);
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.id_menu_item_user_profile);
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_menu_item_map:
                        titleTextView.setText(R.string.string_activity_title_map);
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.id_menu_item_user_profile:
                        titleTextView.setText(R.string.string_activity_title_user_profile);
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.id_menu_item_favorites:
                        titleTextView.setText(R.string.string_activity_title_favorites);
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.id_menu_item_features:
                        titleTextView.setText(R.string.string_activity_title_features);
                        viewPager.setCurrentItem(1);
                        return true;
                }

                return false;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolbar();
        listener = new OnEventOccurListeners() {
            @Override
            public void getEventData(Object object) {
                super.getEventData(object);
                if (object instanceof ArrayList) {
                    titleTextView.setText("ShopList");
                    viewPager.setCurrentItem(4);
                    swipeFragmentAdapter.shopListFrag.setResultModelArrayList((ArrayList<ResultModel>) object);
                } else {
                    String s = (String) object;
                    if (s.equals("polyLineLatLongs")) {
                        viewPager.setCurrentItem(0);
                    } else {
                        titleTextView.setText(s);
                        if (s.equals("Advisor") || s.equals("Liquor Types"))
                            bottomNavigationView.setVisibility(View.VISIBLE);
                        else
                            bottomNavigationView.setVisibility(View.GONE);
                    }
                }
            }
        };

        initializeView();
    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() != 0)
            viewPager.setCurrentItem(0);
        else
            super.onBackPressed();
    }

}
