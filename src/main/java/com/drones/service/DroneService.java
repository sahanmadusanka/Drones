package com.drones.service;

import com.drones.bean.drone.DroneVo;
import com.drones.entity.Drone;

public interface DroneService {

    /**
     * Register a new drone
     * @param droneVo
     */
    Drone registerDrone(DroneVo droneVo);

}
