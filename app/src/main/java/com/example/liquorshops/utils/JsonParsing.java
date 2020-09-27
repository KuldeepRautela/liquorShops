package com.example.liquorshops.utils;

import com.example.liquorshops.jetpack.entities.LocationModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParsing {

    public ArrayList<LatLng> getLatLngs(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        ArrayList<LatLng> locationModelArrayList = new ArrayList<>();
        if (jsonObject.getString("status").equals("OK")) {
            JSONArray routes = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
            for (int index = 0; index < routes.length(); index++) {
                JSONObject jsonObjectSteps = routes.getJSONObject(index).getJSONObject("end_location");
                locationModelArrayList.add(new LatLng(jsonObjectSteps.getDouble("lat"), jsonObjectSteps.getDouble("lng")));
            }
        }
        return locationModelArrayList;
    }
}
