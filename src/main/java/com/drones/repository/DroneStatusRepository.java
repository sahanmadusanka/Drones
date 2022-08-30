package com.drones.repository;

import com.drones.entity.DroneStatus;
import org.springframework.data.repository.CrudRepository;

public interface DroneStatusRepository extends CrudRepository<DroneStatus, Long> {
}
