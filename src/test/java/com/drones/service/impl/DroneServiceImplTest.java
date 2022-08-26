package com.drones.service.impl;

import com.drones.bean.drone.DroneVo;
import com.drones.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @InjectMocks
    DroneServiceImpl droneService;

    @Mock
    DroneRepository droneRepository;


    @Test
    void registerDrone() {
        var droneVo = new DroneVo();
        droneVo.setSerialNumber(UUID.randomUUID().toString()); //UUID as a serial number
        droneVo.setModel("Lightweight");
        droneVo.setWeightLimit(100);
        droneService.registerDrone(droneVo);
    }

}