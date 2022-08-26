package com.drones.controller;

import com.drones.bean.drone.DroneVo;
import com.drones.service.DroneService;
import com.drones.service.impl.DroneServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/drone")
@Validated
public class DroneController {

    private DroneService droneService;

    DroneController(DroneServiceImpl droneService) {
        this.droneService = droneService;
    }

    /**
     * Register a drone
     *
     * @param drone
     */
    @PostMapping(path = "/register")
    void register(@Valid @RequestBody DroneVo drone) {
        droneService.registerDrone(drone);
    }


}
