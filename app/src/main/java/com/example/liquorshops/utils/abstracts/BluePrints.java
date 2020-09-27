package com.example.liquorshops.utils.abstracts;

public interface BluePrints {
    interface ofActivity {
        void setToolbar();
    }

    interface ofSheet {
        void initializeSheet();
    }

    interface ofView {
        void initializeView();

        void initializeListeners();

        void initializeData();

        void initializePicker();

        void initializeViewModel();

        void initializeTabView();

        void closeEverything();
    }

    interface ofRecycler {
        void initializeRecyclerView();

        void initializeEmptyView(boolean isEmpty);
    }

    interface ofFrag {
        void initializeFragsView();
    }
    interface ofMap {
        void initializeMapView();

        void initializeMapThings();

        void initializeMapListeners();

        void goToMyLocation();

//        void goToMapLocation(LatLng latLng);

        void initializeMarker();

        void initializeMarkers(Object object);

        void initializeMapData();
    }
}
