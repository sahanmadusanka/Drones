package com.drones.service;

import com.drones.entity.Medication;

import java.util.Set;

public interface MedicationService {

    /**
     * Get list of Medications with medication coeds
     * @param medicationCodes
     * @return Set<Medication>
     */
    Set<Medication> findMedicationByCode(Set<String> medicationCodes);
}
