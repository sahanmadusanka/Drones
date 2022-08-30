package com.drones.service;

import com.drones.entity.Drone;

import java.util.List;

public interface CacheService {

    /**
     * Get cached drone list
     * @return
     */
    List<Drone> getDroneList();
}
