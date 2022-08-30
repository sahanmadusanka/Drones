package com.drones.service.impl;

import com.drones.bean.drone.DroneState;
import com.drones.bean.drone.DroneVo;
import com.drones.entity.Drone;
import com.drones.exception.BaseException;
import com.drones.exception.DataNotFoundException;
import com.drones.repository.DroneRepository;
import com.drones.service.DroneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DroneServiceImpl implements DroneService {

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

    /**
     * Find drone with the serial number
     *
     * @param serialNo
     * @return Drone
     */
    @Override
    public Drone findBySerialNo(String serialNo) {
        return droneRepository.findBySerialNumber(serialNo)
                .orElseThrow(() -> new DataNotFoundException("Drone serial number not found"));
    }

    /**
     * Set/Change drone state
     *
     * @param drone
     * @param droneStatus
     */
    @Transactional
    @Override
    public void setDroneState(Drone drone, DroneState droneStatus) {
        if (droneStatus == null) {
            throw new BaseException("Drone status cannot be null");
        }
        drone.getDroneStatus().setState(droneStatus);
        droneRepository.save(drone);
    }

}
