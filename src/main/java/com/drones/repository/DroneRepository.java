package com.drones.repository;

import com.drones.bean.drone.DroneState;
import com.drones.entity.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);


    @Query("SELECT d FROM Drone d JOIN d.droneStatus ds WHERE ds.state = :state AND ds.batteryCapacity >= :batteryLevel")
    List<Drone> getAvailableDroneForLoading(@Param("state") DroneState state, @Param("batteryLevel") Integer batteryLevel);
}
