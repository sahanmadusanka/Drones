package com.drones.bean.drone;

import com.drones.exception.InvalidInputException;

public enum DroneModel {

    LIGHT_WEIGHT("Lightweight"),
    MIDDLE_WEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruiserweight"),
    HEAVY_WEIGHT("Heavyweight");

    private String model;

    DroneModel(String model){
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public static DroneModel findByModel(String modelName){
        for(var model : values()){
            if(model.getModel().equalsIgnoreCase(modelName)){
                return model;
            }
        }
        throw new InvalidInputException("Invalid drone model");
    }
}
