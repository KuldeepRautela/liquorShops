package com.example.liquorshops.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import com.example.liquorshops.jetpack.entities.LocationModel;
import com.example.liquorshops.ui.adapters.SwipeFragmentAdapter;
import com.example.liquorshops.utils.JsonParsing;
import com.example.liquorshops.utils.constants.Constants;

import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.jetpack.entities.ResultModel;
import com.example.liquorshops.jetpack.entities.ShopModel;
import com.example.liquorshops.jetpack.viewmodels.LiquorShopsViewModel;
import com.example.liquorshops.ui.adapters.ShopListAdapter;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;
import com.example.liquorshops.utils.elements.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;

import tk.jamun.volley.classes.VolleyJsonObjectRequest;
import tk.jamun.volley.helpers.VolleyNeeds;
import tk.jamun.volley.variables.VolleyResponses;

import static com.example.liquorshops.utils.constants.Constants.urlApiMid;
import static com.example.liquorshops.utils.constants.Constants.urlRouteEnd;
import static com.example.liquorshops.utils.constants.Constants.urlRouteStart;


public class ShopListFrag extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    public static  ArrayList<LatLng> locationModels;
    private static OnEventOccurListeners listener;
    private ArrayList<ResultModel> resultModelArrayList;
    public ShopListFrag(OnEventOccurListeners listeners){
        this.listener=listeners;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        recyclerView = view.findViewById(R.id.id_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setResultModelArrayList(ArrayList<ResultModel> resultModelArrayList) {
        this.resultModelArrayList = resultModelArrayList;
        OnEventOccurListeners listener = new OnEventOccurListeners() {
            @Override
            public void getEventData(Object object) {
                super.getEventData(object);
                if (object instanceof String) {
                    double lat = resultModelArrayList.get(Integer.valueOf((String) object)).getGeometryModel().getLocationModel().getLat();
                    double lng = resultModelArrayList.get(Integer.valueOf((String) object)).getGeometryModel().getLocationModel().getLng();
                    makePolyline(lat, lng);
                } else {
                    ResultModel resultModel = (ResultModel) object;
                    String lat = resultModel.getGeometryModel().getLocationModel().getLat() + "";
                    String lng = resultModel.getGeometryModel().getLocationModel().getLng() + "";
                    String shopImg = resultModel.getPhotoModels().get(0).getPhoto_reference();
                    LiquorShopsViewModel liquorShopsViewModel = ViewModelProviders.of(ShopListFrag.this).get(LiquorShopsViewModel.class);
                    liquorShopsViewModel.insertLiquorShopsData(new FavoriteShopsModel(resultModel.getPlace_id(), lat, lng, resultModel.getName(), shopImg, resultModel.getAddress(), resultModel.getRating() + ""));

                }
            }
        };
        recyclerView.setAdapter(new ShopListAdapter(getContext(), resultModelArrayList, listener));
    }

    public static void makePolyline(double lat, double lng) {
        String urlApi = urlRouteStart + MainFrag.lat + "," + MainFrag.lng + urlApiMid + lat + "," + lng + urlRouteEnd;
        VolleyJsonObjectRequest jsonObjectRequest = new VolleyJsonObjectRequest(urlApi, new VolleyResponses() {
            @Override
            public void onResponse(Object response, String body) {
                super.onResponse(response, body);
                try {
                     locationModels = new JsonParsing().getLatLngs(body);
                     listener.getEventData("polyLineLatLongs");
                  } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int statusCode, String errorMessage) {
                super.onErrorResponse(error, statusCode, errorMessage);
                Log.e("error", errorMessage);
            }
        });

        VolleyNeeds.get().addCalls(jsonObjectRequest);
    }
}
