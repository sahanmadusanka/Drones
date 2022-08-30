package com.drones.controller;

import com.drones.bean.drone.DroneAvailableVo;
import com.drones.bean.drone.DroneRegistrationSuccessVo;
import com.drones.bean.drone.DroneVo;
import com.drones.bean.medication.MedLoadSuccessResponseVo;
import com.drones.bean.medication.MedLoadRequestVo;
import com.drones.bean.medication.MedLoadedResponseVo;
import com.drones.service.DroneService;
import com.drones.service.DroneMedicationService;
import com.drones.service.impl.DroneServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/drone")
@Validated
public class DroneController {

    private DroneService droneService;
    private DroneMedicationService droneMedicationService;

    DroneController(DroneServiceImpl droneService, DroneMedicationService droneMedicationService) {
        this.droneService = droneService;
        this.droneMedicationService = droneMedicationService;

    }

    /**
     * Register a drone
     *
     * @param drone
     */
    @PostMapping(path = "/register")
    ResponseEntity<DroneRegistrationSuccessVo> register(@Valid @RequestBody DroneVo droneVo) {
        var drone = droneService.registerDrone(droneVo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DroneRegistrationSuccessVo(drone, "Drone registration success"));
    }

    /**
     * Load medication to a Drone
     *
     * @param serialNo
     * @param loadRequest
     */
    @PutMapping(path = "/{serialNo}/load-medication")
    ResponseEntity<MedLoadSuccessResponseVo> loadMedication(@PathVariable String serialNo,
                                                            @Valid @RequestBody MedLoadRequestVo loadRequest) {
        droneMedicationService.loadToDrone(serialNo, loadRequest);
        return ResponseEntity.ok(new MedLoadSuccessResponseVo("Successfully loaded medication to the drone"));
    }

    /**
     * Check medication loaded to a drone
     *
     * @param serialNo
     */
    @GetMapping(path = "/{serialNo}/check-medication")
    ResponseEntity<MedLoadedResponseVo> checkMedication(@PathVariable String serialNo) {
        var medications = droneMedicationService.getLoadedMedication(serialNo);
        return ResponseEntity.ok(new MedLoadedResponseVo(medications));
    }

    /**
     * Check available drones for load medication
     */
    @GetMapping(path = "/available-to-load")
    ResponseEntity<DroneAvailableVo> checkAvailableDrones() {
        var drones = droneService.getAvailableDrones();
        var droneVos = drones.stream().map(DroneVo::new).toList();
        return ResponseEntity.ok(new DroneAvailableVo(droneVos));
    }

}
