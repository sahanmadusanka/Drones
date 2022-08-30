package com.drones.repository;

import com.drones.entity.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);
}
