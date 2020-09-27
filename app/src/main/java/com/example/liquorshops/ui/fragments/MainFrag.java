package com.example.liquorshops.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.LocationModel;
import com.example.liquorshops.jetpack.entities.ResultModel;
import com.example.liquorshops.jetpack.entities.ShopModel;
import com.example.liquorshops.utils.UtilityCheckPermission;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;
import com.example.liquorshops.utils.constants.Constants;
import com.example.liquorshops.utils.elements.BaseFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tk.jamun.volley.classes.VolleyJsonObjectRequest;
import tk.jamun.volley.helpers.VolleyNeeds;
import tk.jamun.volley.variables.VolleyResponses;

import static com.example.liquorshops.utils.constants.Constants.urlApiEnd;
import static com.example.liquorshops.utils.constants.Constants.urlApiStart;

public class MainFrag extends BaseFragment implements OnMapReadyCallback {
    private MarkerOptions markerOptions;
    private MapView mapView;
    private GoogleMap mMap;
    private ArrayList<LatLng> locationModelArrayList;
    public static double lat, lng;
    private ArrayList<ResultModel> resultModelArrayList;
    private View view;
    private Button nearByButton;
    private OnEventOccurListeners listener;
    private String latlng;
    private LocationManager locationManager;

    public MainFrag(OnEventOccurListeners listener) {
        this.listener = listener;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) throws NullPointerException {

            lat = location.getLatitude();
            lng = location.getLongitude();
            latlng = lat + "," + lng;
            Log.e("lat long ", latlng);
            if (ShopListFrag.locationModels != null)
                ShopListFrag.locationModels.add(0, new LatLng(lat, lng));
            getNearestShops();
            makePolyline(new LatLng(lat, lng));
            LatLng yourLocation = new LatLng(Double.valueOf(latlng.split(",")[0]), Double.valueOf(latlng.split(",")[1]));
            mMap.addMarker(markerOptions.position(yourLocation).title("Your Location ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourLocation, 17f));
            mMap.setBuildingsEnabled(true);


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("jamun ", "onStatus changed");

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e("jamun ", "provider enabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e("jamun ", "provider disabled");
            Toast.makeText(getContext(), "Make Location Enable ", Toast.LENGTH_SHORT).show();
        }
    };

    private void makePolyline(LatLng latLng) throws NullPointerException {
        if (ShopListFrag.locationModels != null) {

            PolylineOptions polylineOptions = new PolylineOptions();
            ShopListFrag.locationModels.add(0, latLng);
            polylineOptions.addAll(ShopListFrag.locationModels);
            mMap.addPolyline(polylineOptions.width(4f).geodesic(true)
                    .color(Color.RED));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.id_mapview);
        mapView.onCreate(savedInstanceState);
        onResume();
        initializeView();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("map ", "onPause");
    }

    @Override
    public void initializeView() throws NullPointerException {
        super.initializeView();
        nearByButton = view.findViewById(R.id.id_button_shop_nearby);
        nearByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getEventData(resultModelArrayList);
            }
        });
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (!Places.isInitialized())
            Places.initialize(getContext(), getString(R.string.googlegradle_maps_key));
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                mMap.addMarker(markerOptions.position(place.getLatLng()).title(place.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17f));
                latlng = place.getLatLng().latitude + "," + place.getLatLng().longitude;
                getNearestShops();
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });

        if (UtilityCheckPermission.checkPermission(getContext(), UtilityCheckPermission.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION))
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, locationListener);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        markerOptions = new MarkerOptions();
        initializeMapSetting();
    }

    @Override
    public void onResume() {
        Log.e("map", "on resume");
        super.onResume();
        mapView.onResume();
        mapView.getMapAsync(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    private void initializeMapSetting() {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    private void getNearestShops() throws NullPointerException {
        nearByButton.setVisibility(View.VISIBLE);
        String urlApi = urlApiStart + latlng + "&radius=1500" + urlApiEnd;
        Log.e("uriApi ", urlApi);
        VolleyJsonObjectRequest jsonObjectRequest = new VolleyJsonObjectRequest(urlApi, ShopModel.class, new VolleyResponses() {
            @Override
            public void onStatusCodeResponse(Integer statusCode) {
                super.onStatusCodeResponse(statusCode);
            }

            @Override
            public void onResponse(Object response, String body) {
                super.onResponse(response, body);
                ShopModel shopModel = null;
                if (response instanceof ShopModel)
                    shopModel = (ShopModel) response;
                if (shopModel.getStatus().equals("OK")) {
                    resultModelArrayList = (ArrayList<ResultModel>) shopModel.getResults();
                    setMarkerOnShops(shopModel.getResults());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                Log.e("error ", error.getLocalizedMessage());
            }
        });

        VolleyNeeds.get().addCalls(jsonObjectRequest);

    }

    public void setLocationArrayList(ArrayList<LatLng> locationArrayList) {
        this.locationModelArrayList = locationArrayList;
    }

    private void setMarkerOnShops(List<ResultModel> resultModelList) {
        for (ResultModel resultModel : resultModelList) {
            LatLng shopLocation = new LatLng(resultModel.getGeometryModel().getLocationModel().getLat(), resultModel.getGeometryModel().getLocationModel().getLng());
            mMap.addMarker(markerOptions.position(shopLocation).title(resultModel.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopLocation, 17f));
        }
    }
}
