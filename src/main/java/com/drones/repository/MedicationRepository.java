package com.drones.repository;

import com.drones.entity.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

    Optional<Medication> findByCode(String code);

    /**
     * Get unique list of Medication records using medication_code
     * @param codes
     * @return Set<Medication>
     */
    Set<Medication> findByCodeIn(@Param("code") Set<String> codes);
}
