package com.drones.repository;

import com.drones.entity.DroneMedication;
import com.drones.repository.projection.MedLoadedProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    /**
     * Get loaded medication with given drone serial
     *
     * @param droneSerial
     * @return List<DroneMedication>
     */
    @Query("SELECT dm.id AS id, dm.quantity AS quantity, dm.medication.code AS code, dm.medication.name AS name, " +
            "dm.medication.weight AS weight, dm.medication.image AS image " +
            "FROM DroneMedication dm JOIN dm.drone d WHERE d.serialNumber = :droneSerial")
    List<MedLoadedProjection> getLoadedMedication(@Param("droneSerial") String droneSerial);
}
