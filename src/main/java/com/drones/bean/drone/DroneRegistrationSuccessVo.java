package com.drones.bean.drone;

import com.drones.bean.RestResponseVo;
import com.drones.entity.Drone;

public class DroneRegistrationSuccessVo extends RestResponseVo {

    private Long id;

    public DroneRegistrationSuccessVo(Drone drone, String message) {
        super(message);
        this.id = drone.getId();
    }

    public Long getId() {
        return id;
    }
}
