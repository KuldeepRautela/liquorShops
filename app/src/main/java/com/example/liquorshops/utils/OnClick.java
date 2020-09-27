package com.example.liquorshops.utils;

import com.example.liquorshops.utils.enums.ActionType;
import com.example.liquorshops.utils.enums.ModuleType;

public interface OnClick {
    interface OnDataListener {
//        void onErrorResponse(Object object, String errorMessage);
//
//        void getEventData(Object object, ActionType actionType);
//
//        void getEventData(Object object, int comeFor);
//
//        void getEventData(Object object, ActionType actionType);
//
//        void getEventData(Object object);
//
//        void getEventData(Object object, int adapterPosition, ModuleType moduleType);
//
//        void getEventData(Object object, ActionType actionType, int adapterPosition);
//
//        void getEventData(Object object, ActionType actionType, ModuleType moduleType);
//
//        void getEventData(Object object, int actionType, ModuleType moduleType);

        void getEventData(Object object, ActionType actionType);

        void getEventData(Object object, int comeFor);

        void onErrorResponse(Object object, String errorMessage);

        void getEventData(Object object);

        void getEventData(Object object, int adapterPosition, ModuleType moduleType);

        void getEventData(Object object, ActionType actionType, int adapterPosition);

        void getEventData(Object object, ActionType actionType, ModuleType moduleType);

        void getEventData(Object object, ActionType actionType, Object variable);
    }
}
