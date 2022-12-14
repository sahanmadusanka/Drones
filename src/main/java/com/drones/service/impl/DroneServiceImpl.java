package com.drones.service.impl;

import com.drones.bean.drone.DroneState;
import com.drones.bean.drone.DroneVo;
import com.drones.entity.Drone;
import com.drones.entity.DroneStatus;
import com.drones.exception.BaseException;
import com.drones.exception.DataNotFoundException;
import com.drones.repository.DroneRepository;
import com.drones.repository.DroneStatusRepository;
import com.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;
    private DroneStatusRepository droneStatusRepository;

    @Value("${drone.battery.loadingMinLevel:25}")
    private int loadingMinCapacity;

    public DroneServiceImpl(DroneRepository droneRepository, DroneStatusRepository droneStatusRepository) {
        this.droneRepository = droneRepository;
        this.droneStatusRepository = droneStatusRepository;
    }

    /**
     * Register a new drone
     *
     * @param droneVo
     */
    @Transactional
    @CacheEvict(value = "drones", allEntries = true) // Clear cache each time new drone get registered
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

    /**
     * Check drone in ready to loading state with "state"
     *
     * @param drone
     * @return boolean if true drone is ready to loading
     */
    @Override
    public boolean isDroneReadyToLoadWithState(Drone drone) {
        return drone.getDroneStatus().getState() == DroneState.IDLE;
    }

    /**
     * Check drone is ready to loading with battery level
     *
     * @param drone
     * @return boolean if true drone is ready to loading
     */
    @Override
    public boolean isDroneReadyToLoadWithBatteryLevel(Drone drone) {
        return drone.getDroneStatus().getBatteryCapacity() > loadingMinCapacity;
    }

    /**
     * Get all available drone which can be ready for loading
     *
     * @return
     */
    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.getAvailableDroneForLoading(DroneState.IDLE, loadingMinCapacity);
    }

    /**
     * Get battery level for given drone serial no
     *
     * @param serialNo
     * @return
     */
    @Override
    public int getBatteryLevel(String serialNo) {
        return droneRepository.findBySerialNumber(serialNo)
                .orElseThrow(() -> new DataNotFoundException("Cannot find drone with serial:" + serialNo))
                .getDroneStatus()
                .getBatteryCapacity();
    }

    @Override
    public List<Drone> getAllDrones() {
        List<Drone> drones = new ArrayList<>();
        droneRepository.findAll().forEach(drones::add);
        return drones;
    }

    /**
     * Update drone battery level
     *
     * @param droneStatus
     * @param batteryLevel
     */
    public void updateBatteryLevel(DroneStatus droneStatus, int batteryLevel) {
        droneStatus.setBatteryCapacity(batteryLevel);
        droneStatusRepository.save(droneStatus);
    }

}
