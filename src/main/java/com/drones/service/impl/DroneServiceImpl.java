package com.drones.service.impl;

import com.drones.bean.drone.DroneVo;
import com.drones.entity.Drone;
import com.drones.exception.BaseException;
import com.drones.repository.DroneRepository;
import com.drones.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 */

@Service
public class DroneServiceImpl implements DroneService {

    Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);

    private DroneRepository droneRepository;

    DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    /**
     * Register a new drone
     *
     * @param droneVo
     */
    @Transactional
    public Drone registerDrone(DroneVo droneVo) {
        var drone = new Drone(droneVo);
        //TODO Add drone serial duplicate validator
        try {
            return droneRepository.save(drone);
        } catch (Exception exception) {
            throw new BaseException("Error in registering a drone", exception);
        }
    }
}
