package com.drones.service;

import com.drones.bean.drone.DroneState;
import com.drones.bean.drone.DroneVo;
import com.drones.entity.Drone;
import com.drones.exception.DataNotFoundException;

public interface DroneService {

    /**
     * Register a new drone
     * @param droneVo
     * @return Drone
     */
    Drone registerDrone(DroneVo droneVo);

    /**
     * Get a drone by serial number
     * @return Drone
     * @throws DataNotFoundException when drone cannot find with given serial number
     */
    Drone findBySerialNo(String serialNo);

    /**
     * Set/Change drone state
     * @param drone
     * @param droneStatus
     */
    void setDroneState(Drone drone, DroneState droneStatus);

    /**
     * Check drone in ready to loading state with "state"
     * @param drone
     * @return boolean if true drone is ready to loading
     */
    boolean isDroneReadyToLoadWithState(Drone drone);

    /**
     * Check drone is ready to loading with battery level
     * @param drone
     * @return boolean if true drone is ready to loading
     */
    boolean isDroneReadyToLoadWithBatteryLevel(Drone drone);

}
