package com.example.liquorshops.utils.abstracts;


import com.example.liquorshops.utils.OnClick;
import com.example.liquorshops.utils.enums.ActionType;
import com.example.liquorshops.utils.enums.ModuleType;

public abstract class OnEventOccurListeners implements OnClick.OnDataListener {

    @Override
    public void getEventData(Object object, ActionType actionType) {

    }

    @Override
    public void getEventData(Object object, int comeFor) {

    }

    @Override
    public void onErrorResponse(Object object, String errorMessage) {

    }

    @Override
    public void getEventData(Object object) {

    }

    @Override
    public void getEventData(Object object, int adapterPosition, ModuleType moduleType) {

    }

    @Override
    public void getEventData(Object object, ActionType actionType, int adapterPosition) {

    }

    @Override
    public void getEventData(Object object, ActionType actionType, ModuleType moduleType) {

    }

    @Override
    public void getEventData(Object object, ActionType actionType, Object variable) {

    }
}
