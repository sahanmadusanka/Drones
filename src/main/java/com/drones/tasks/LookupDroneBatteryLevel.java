package com.drones.tasks;

import com.drones.entity.Drone;
import com.drones.entity.DroneBatteryHistory;
import com.drones.repository.DroneBatteryHistoryRepository;
import com.drones.service.CacheService;
import com.drones.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class LookupDroneBatteryLevel {

    private Logger logger = LoggerFactory.getLogger(LookupDroneBatteryLevel.class);

    private CacheService cacheService;
    private DroneService droneService;

    private DroneBatteryHistoryRepository droneBatteryHistoryRepository;

    public LookupDroneBatteryLevel(CacheService cacheService, DroneService droneService,
                                   DroneBatteryHistoryRepository droneBatteryHistoryRepository) {
        this.cacheService = cacheService;
        this.droneService = droneService;
        this.droneBatteryHistoryRepository = droneBatteryHistoryRepository;
    }

    @Async
    @Scheduled(cron = "${drone.battery.level.update.crone:* * * * *}")
    public void getBatteryLevel() {
        var uuid = UUID.randomUUID().toString();
        logger.info("Drone Battery lookup task {} starting", uuid);

        var drones = cacheService.getDroneList();

        for (var drone : drones) {
            var batteryLevel = getDroneBatteryLevel(drone);
            var batteryHistory = new DroneBatteryHistory(drone, batteryLevel);

            droneBatteryHistoryRepository.save(batteryHistory); // Add audit/history trail

            //Update Drone battery level
            var droneStatus = drone.getDroneStatus();
            if (droneStatus.getBatteryCapacity() != batteryLevel) {
                droneService.updateBatteryLevel(droneStatus, batteryLevel);
            }
        }
        logger.info("Drone Battery lookup task {} complete", uuid);
    }

    private int getDroneBatteryLevel(Drone drone) {
        var currentBatteryLevel = 60;
        /**
         *Assuming the drone can have an endpoint which this service can access and get battery level.
         * Or else need to have different setup for if drone sending battery level as message (JMS,etc...)
         **/

        return currentBatteryLevel;
    }
}
