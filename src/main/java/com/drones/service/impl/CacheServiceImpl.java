package com.drones.service.impl;

import com.drones.entity.Drone;
import com.drones.service.CacheService;
import com.drones.service.DroneService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    private DroneService droneService;

    public CacheServiceImpl(DroneService droneService) {
        this.droneService = droneService;
    }

    /**
     * Get cached drone list
     * @return
     */
    @Cacheable("drones")
    @Override
    public List<Drone> getDroneList(){
        return droneService.getAllDrones();
    }
}
