package com.drones.repository;

import com.drones.entity.DroneMedication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DroneMedicationRepository extends CrudRepository<DroneMedication, Long> {

    /**
     * Get sum of total weight (milligrams) loaded to a drone with drone id
     *
     * @param droneId Drone primary key
     * @return Long Total weight (milligrams) of already loaded medication
     */
    @Query(value = "SELECT sum(m.weight * dm.quantity) AS total_weight FROM medication m " +
            "JOIN drone_medication dm ON m.id = dm.medication_id WHERE dm.drone_id = :droneId", nativeQuery = true)
    Double getTotalLoadedWeight(@Param("droneId") Long droneId);
}
