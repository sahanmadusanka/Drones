package com.drones.repository;

import com.drones.entity.DroneBatteryHistory;
import org.springframework.data.repository.CrudRepository;

public interface DroneBatteryHistoryRepository extends CrudRepository<DroneBatteryHistory, Long> {
}
