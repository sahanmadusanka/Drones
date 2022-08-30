package com.drones.bean.drone;

import java.util.List;

public record DroneAvailableVo(int totalDrones, List<DroneVo> drones) {

    public DroneAvailableVo(List<DroneVo> drones) {
        this(drones.size(), drones);
    }
}
