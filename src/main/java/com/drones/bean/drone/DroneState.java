package com.drones.bean.drone;

public enum DroneState {

    IDLE(0),
    LOADING(1),
    LOADED(2),
    DELIVERING(3),
    DELIVERED(4),
    RETURNING(5);

    private int order;

    DroneState(int order){
        this.order = order;
    }

    public int getOrder(){
        return this.order;
    }

}
